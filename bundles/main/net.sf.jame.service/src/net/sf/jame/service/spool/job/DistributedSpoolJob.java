/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
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
package net.sf.jame.service.spool.job;

import java.io.IOException;
import java.io.InputStream;

import net.sf.jame.core.util.Worker;
import net.sf.jame.core.xml.XML;
import net.sf.jame.service.LibraryService;
import net.sf.jame.service.io.ChunkedRandomAccessFile;
import net.sf.jame.service.spool.DefaultJobData;
import net.sf.jame.service.spool.DistributedSpoolJobInterface;
import net.sf.jame.service.spool.JobData;
import net.sf.jame.service.spool.JobListener;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.TwisterClipXMLImporter;

import org.w3c.dom.Document;

/**
 * @author Andrea Medeghini
 */
public class DistributedSpoolJob implements DistributedSpoolJobInterface {
	private final JobListener listener;
	private final String jobId;
	private String remoteJobId;
	private volatile long lastUpdate;
	private volatile boolean started;
	private volatile boolean aborted;
	private volatile boolean terminated;
	private volatile JobData jobDataRow;
	private final LibraryService service;
	private int firstFrameNumber;
	private final Worker worker;

	/**
	 * @param service
	 * @param worker
	 * @param jobId
	 * @param listener
	 */
	public DistributedSpoolJob(final LibraryService service, final Worker worker, final String jobId, final JobListener listener) {
		lastUpdate = System.currentTimeMillis();
		this.listener = listener;
		this.service = service;
		this.worker = worker;
		this.jobId = jobId;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#getJobId()
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#getFrameNumber()
	 */
	public synchronized int getFrameNumber() {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		return jobDataRow.getFrameNumber();
	}

	/**
	 * @see net.sf.jame.service.spool.SpoolJobInterface#setFrameNumber(int)
	 */
	public synchronized void setFrameNumber(final int frameNumber) {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		jobDataRow.setFrameNumber(frameNumber);
		lastUpdate = System.currentTimeMillis();
		worker.addTask(new StatusChangedTask(new DefaultJobData(jobDataRow)));
	}

	/**
	 * @see net.sf.jame.service.spool.SpoolJobInterface#setFirstFrameNumber(int)
	 */
	public synchronized void setFirstFrameNumber(final int frameNumber) {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		if (started) {
			throw new IllegalStateException();
		}
		firstFrameNumber = frameNumber;
		jobDataRow.setFrameNumber(frameNumber);
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#getFirstFrameNumber()
	 */
	public synchronized int getFirstFrameNumber() {
		return firstFrameNumber;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#getJobDataRow()
	 */
	public synchronized JobData getJobDataRow() {
		return jobDataRow;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#setJobDataRow(JobData)
	 */
	public synchronized void setJobDataRow(final JobData jobDataRow) {
		if (started) {
			throw new IllegalStateException();
		}
		if (jobDataRow == null) {
			throw new IllegalArgumentException();
		}
		this.jobDataRow = jobDataRow;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#getRAF()
	 */
	public synchronized ChunkedRandomAccessFile getRAF() throws IOException {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		return service.getJobRandomAccessFile(jobDataRow.getJobId());
	}

	/**
	 * @see net.sf.jame.service.spool.SpoolJobInterface#getClip()
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
	 * @see net.sf.jame.service.spool.JobInterface#getLastUpdate()
	 */
	public synchronized long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#reset()
	 */
	public void reset() {
		synchronized (this) {
			started = false;
			aborted = false;
			terminated = false;
			lastUpdate = System.currentTimeMillis();
		}
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#start()
	 */
	public void start() {
		synchronized (this) {
			if (jobDataRow == null) {
				throw new IllegalStateException();
			}
			if (!started) {
				lastUpdate = System.currentTimeMillis();
				started = true;
				aborted = false;
				terminated = false;
				worker.addTask(new StartedTask(new DefaultJobData(jobDataRow)));
			}
		}
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#stop()
	 */
	public void stop() {
		synchronized (this) {
			if (jobDataRow == null) {
				throw new IllegalStateException();
			}
			started = false;
			if (started) {
				worker.addTask(new StoppedTask(new DefaultJobData(jobDataRow)));
			}
		}
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#abort()
	 */
	public synchronized void abort() {
		aborted = true;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#dispose()
	 */
	public synchronized void dispose() {
		if (jobDataRow != null) {
			worker.addTask(new DisposedTask(new DefaultJobData(jobDataRow)));
		}
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#isStarted()
	 */
	public synchronized boolean isStarted() {
		return started;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#isAborted()
	 */
	public synchronized boolean isAborted() {
		return aborted;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#isTerminated()
	 */
	public synchronized boolean isTerminated() {
		return terminated;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#getRemoteJobId()
	 */
	public synchronized String getRemoteJobId() {
		return remoteJobId;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#setRemoteJobId(java.lang.String)
	 */
	public synchronized void setRemoteJobId(final String remoteJobId) {
		this.remoteJobId = remoteJobId;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#getTotalFrames()
	 */
	public synchronized int getTotalFrames() {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		return (jobDataRow.getFrameRate() > 0) ? ((jobDataRow.getStopTime() - jobDataRow.getStartTime()) * jobDataRow.getFrameRate()) : 1;
	}

	/**
	 * 
	 */
	public synchronized void terminate() {
		if (jobDataRow == null) {
			throw new IllegalStateException();
		}
		terminated = true;
		aborted = false;
		worker.addTask(new TerminatedTask(new DefaultJobData(jobDataRow)));
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
