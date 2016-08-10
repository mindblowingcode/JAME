/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.queue.spool.job;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadFactory;

import net.sf.jame.core.util.DefaultThreadFactory;
import net.sf.jame.core.util.Worker;
import net.sf.jame.core.xml.XML;
import net.sf.jame.queue.LibraryService;
import net.sf.jame.queue.io.ChunkedRandomAccessFile;
import net.sf.jame.queue.spool.DefaultJobData;
import net.sf.jame.queue.spool.JobData;
import net.sf.jame.queue.spool.JobListener;
import net.sf.jame.queue.spool.SpoolJobInterface;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.TwisterClipXMLImporter;

import org.w3c.dom.Document;

/**
 * @author Andrea Medeghini
 */
public class CopyProcessSpoolJob implements SpoolJobInterface {
	private static final ThreadFactory factory = new DefaultThreadFactory("CopyProcessSpoolJob Task", true, Thread.MIN_PRIORITY);
	private final JobListener listener;
	private final String jobId;
	private volatile long lastUpdate;
	private volatile boolean started;
	private volatile boolean aborted;
	private volatile boolean terminated;
	private volatile JobData jobDataRow;
	private volatile Thread thread;
	private final LibraryService service;
	private int firstFrameNumber;
	private final Worker worker;

	/**
	 * @param service
	 * @param worker
	 * @param jobId
	 * @param listener
	 */
	public CopyProcessSpoolJob(final LibraryService service, final Worker worker, final String jobId, final JobListener listener) {
		lastUpdate = System.currentTimeMillis();
		this.listener = listener;
		this.service = service;
		this.worker = worker;
		this.jobId = jobId;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#getJobId()
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#getFrameNumber()
	 */
	public synchronized int getFrameNumber() {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		return jobDataRow.getFrameNumber();
	}

	private void setFrameNumber(final int frameNumber) {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		jobDataRow.setFrameNumber(frameNumber);
		lastUpdate = System.currentTimeMillis();
	}

	/**
	 * @see net.sf.jame.queue.spool.SpoolJobInterface#setFirstFrameNumber(int)
	 */
	public synchronized void setFirstFrameNumber(final int frameNumber) {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		if (thread != null) {
			throw new IllegalStateException();
		}
		firstFrameNumber = frameNumber;
		jobDataRow.setFrameNumber(frameNumber);
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#getFirstFrameNumber()
	 */
	public synchronized int getFirstFrameNumber() {
		return firstFrameNumber;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#getJobDataRow()
	 */
	public synchronized JobData getJobDataRow() {
		return jobDataRow;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#setJobDataRow(JobData)
	 */
	public synchronized void setJobDataRow(final JobData jobDataRow) {
		if (thread != null) {
			throw new IllegalStateException();
		}
		if (jobDataRow == null) {
			throw new IllegalArgumentException();
		}
		this.jobDataRow = jobDataRow;
	}

	/**
	 * @see net.sf.jame.queue.spool.SpoolJobInterface#getClip()
	 */
	public synchronized TwisterClip getClip() throws IOException {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		try {
			final TwisterClipXMLImporter importer = new TwisterClipXMLImporter();
			final InputStream is = service.getClipInputStream(jobDataRow.getClipId());
			final Document doc = XML.loadDocument(is, "twister-clip.xml");
			return importer.importFromElement(doc.getDocumentElement());
		}
		catch (final Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public synchronized String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("id = ");
		builder.append(jobId);
		builder.append(", frameNumber = ");
		builder.append(jobDataRow != null ? jobDataRow.getFrameNumber() : "N/A");
		return builder.toString();
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#getLastUpdate()
	 */
	public synchronized long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#reset()
	 */
	public void reset() {
		Thread tmpThread = thread;
		if (tmpThread != null) {
			tmpThread.interrupt();
			try {
				tmpThread.join();
			}
			catch (final InterruptedException e) {
			}
		}
		synchronized (this) {
			started = false;
			aborted = false;
			terminated = false;
			lastUpdate = System.currentTimeMillis();
		}
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#start()
	 */
	public void start() {
		synchronized (this) {
			if (jobDataRow == null) {
				throw new IllegalStateException();
			}
			if (thread == null) {
				lastUpdate = System.currentTimeMillis();
				started = true;
				aborted = false;
				terminated = false;
				worker.addTask(new StartedTask(new DefaultJobData(jobDataRow)));
				thread = factory.newThread(new RenderTask());
				thread.start();
			}
		}
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#stop()
	 */
	public void stop() {
		Thread tmpThread = thread;
		if (tmpThread != null) {
			tmpThread.interrupt();
			try {
				tmpThread.join();
			}
			catch (final InterruptedException e) {
			}
		}
		synchronized (this) {
			if (jobDataRow == null) {
				throw new IllegalStateException();
			}
			started = false;
			thread = null;
			worker.addTask(new StoppedTask(new DefaultJobData(jobDataRow)));
		}
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#abort()
	 */
	public synchronized void abort() {
		aborted = true;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#dispose()
	 */
	public synchronized void dispose() {
		if (jobDataRow != null) {
			worker.addTask(new DisposedTask(new DefaultJobData(jobDataRow)));
		}
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#isStarted()
	 */
	public synchronized boolean isStarted() {
		return started;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#isAborted()
	 */
	public synchronized boolean isAborted() {
		return aborted;
	}

	/**
	 * @see net.sf.jame.queue.spool.JobInterface#isTerminated()
	 */
	public synchronized boolean isTerminated() {
		return terminated;
	}

	private class RenderTask implements Runnable {
		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			ChunkedRandomAccessFile jobRaf = null;
			ChunkedRandomAccessFile profileRaf = null;
			try {
				final int frameCount = (jobDataRow.getStopTime() - jobDataRow.getStartTime()) * jobDataRow.getFrameRate();
				if (frameCount == 0) {
					final int tx = jobDataRow.getTileOffsetX();
					final int ty = jobDataRow.getTileOffsetY();
					final int tw = jobDataRow.getTileWidth();
					final int th = jobDataRow.getTileHeight();
					final int bw = jobDataRow.getBorderWidth();
					final int bh = jobDataRow.getBorderHeight();
					final int iw = jobDataRow.getImageWidth();
					final int sw = tw + 2 * bw;
					final int sh = th + 2 * bh;
					final byte[] data = new byte[sw * sh * 4];
					jobRaf = service.getJobRandomAccessFile(jobDataRow.getJobId());
					jobRaf.readFully(data);
					profileRaf = service.getProfileRandomAccessFile(jobDataRow.getProfileId());
					long pos = (ty * iw + tx) * 4;
					for (int j = sw * bh * 4 + bw * 4, k = 0; k < th; k++) {
						profileRaf.seek(pos);
						profileRaf.write(data, j, tw * 4);
						j += sw * 4;
						pos += iw * 4;
						Thread.yield();
					}
					setFrameNumber(0);
					worker.addTask(new StatusChangedTask(new DefaultJobData(jobDataRow)));
				}
				else if (jobDataRow.getFrameNumber() < frameCount) {
					final int tx = jobDataRow.getTileOffsetX();
					final int ty = jobDataRow.getTileOffsetY();
					final int tw = jobDataRow.getTileWidth();
					final int th = jobDataRow.getTileHeight();
					final int bw = jobDataRow.getBorderWidth();
					final int bh = jobDataRow.getBorderHeight();
					final int iw = jobDataRow.getImageWidth();
					final int ih = jobDataRow.getImageHeight();
					final int sw = tw + 2 * bw;
					final int sh = th + 2 * bh;
					final byte[] data = new byte[sw * sh * 4];
					jobRaf = service.getJobRandomAccessFile(jobDataRow.getJobId());
					profileRaf = service.getProfileRandomAccessFile(jobDataRow.getProfileId());
					int startFrameNumber = 0;
					if (jobDataRow.getFrameNumber() > 0) {
						startFrameNumber = jobDataRow.getFrameNumber() + 1;
					}
					long pos = (startFrameNumber * sw * sh) * 4;
					jobRaf.seek(pos);
					for (int frameNumber = startFrameNumber; frameNumber < frameCount; frameNumber++) {
						jobRaf.readFully(data);
						pos = (frameNumber * iw * ih + ty * iw + tx) * 4;
						for (int j = sw * bh * 4 + bw * 4, k = 0; k < th; k++) {
							profileRaf.seek(pos);
							profileRaf.write(data, j, tw * 4);
							j += sw * 4;
							pos += iw * 4;
							Thread.yield();
						}
						setFrameNumber(frameNumber);
						worker.addTask(new StatusChangedTask(new DefaultJobData(jobDataRow)));
						if (aborted) {
							break;
						}
					}
				}
			}
			catch (final Throwable e) {
				aborted = true;
				e.printStackTrace();
			}
			finally {
				if (profileRaf != null) {
					try {
						profileRaf.close();
					}
					catch (final IOException e) {
					}
				}
				if (jobRaf != null) {
					try {
						jobRaf.close();
					}
					catch (final IOException e) {
					}
				}
			}
			synchronized (CopyProcessSpoolJob.this) {
				if (!aborted) {
					terminated = true;
				}
				if (terminated) {
					worker.addTask(new TerminatedTask(new DefaultJobData(jobDataRow)));
				}
			}
		}
	}

	private class StatusChangedTask implements Runnable {
		private final JobData jobData;

		/**
		 * @param jobData
		 */
		protected StatusChangedTask(final JobData jobData) {
			this.jobData = jobData;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			listener.updated(jobId, jobData);
		}
	}

	private class StartedTask implements Runnable {
		private final JobData jobData;

		/**
		 * @param jobData
		 */
		protected StartedTask(final JobData jobData) {
			this.jobData = jobData;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			listener.started(jobId, jobData);
		}
	}

	private class StoppedTask implements Runnable {
		private final JobData jobData;

		/**
		 * @param jobData
		 */
		protected StoppedTask(final JobData jobData) {
			this.jobData = jobData;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			listener.stopped(jobId, jobData);
		}
	}

	private class TerminatedTask implements Runnable {
		private final JobData jobData;

		/**
		 * @param jobData
		 */
		protected TerminatedTask(final JobData jobData) {
			this.jobData = jobData;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			listener.terminated(jobId, jobData);
		}
	}

	private class DisposedTask implements Runnable {
		private final JobData jobData;

		/**
		 * @param jobData
		 */
		protected DisposedTask(final JobData jobData) {
			this.jobData = jobData;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			listener.disposed(jobId, jobData);
		}
	}
}
