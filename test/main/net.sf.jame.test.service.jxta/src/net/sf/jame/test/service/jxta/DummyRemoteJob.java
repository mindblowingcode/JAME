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
package net.sf.jame.test.service.jxta;

import java.io.IOException;

import net.sf.jame.service.io.ChunkedRandomAccessFile;
import net.sf.jame.service.spool.DistributedSpoolJobInterface;
import net.sf.jame.service.spool.JobData;
import net.sf.jame.service.spool.JobListener;
import net.sf.jame.twister.TwisterClip;

/**
 * @author Andrea Medeghini
 */
public class DummyRemoteJob implements DistributedSpoolJobInterface {
	private final JobListener listener;
	private String jobId;
	private JobData jobDataRow;
	private String remoteJobId;
	private int frameNumber;
	private byte[] dataIn;
	private final byte[] dataOut;
	private long lastUpdate;
	private volatile boolean started;
	private volatile boolean aborted;
	private volatile boolean terminated;
	private int firstFrameNumber;

	/**
	 * @param dataOut
	 * @param dataIn
	 * @param listener
	 */
	public DummyRemoteJob(final byte[] dataIn, final byte[] dataOut, final JobListener listener) {
		lastUpdate = System.currentTimeMillis();
		this.listener = listener;
		this.dataIn = dataIn;
		this.dataOut = dataOut;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#setJobId(java.lang.String)
	 */
	public void setJobId(final String id) {
		jobId = id;
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
		return frameNumber;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#setFrameNumber(int)
	 */
	public void setFrameNumber(final int frameNumber) {
		synchronized (this) {
			this.frameNumber = frameNumber;
			lastUpdate = System.currentTimeMillis();
		}
		listener.updated(jobId, jobDataRow);
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#setFirstFrameNumber(int)
	 */
	public synchronized void setFirstFrameNumber(final int frameNumber) {
		firstFrameNumber = frameNumber;
		this.frameNumber = frameNumber;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#getFirstFrameNumber()
	 */
	public synchronized int getFirstFrameNumber() {
		return firstFrameNumber;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#writeData(byte[])
	 */
	public synchronized void writeData(final byte[] data) throws IOException {
		dataIn = new byte[data.length];
		System.arraycopy(data, 0, dataIn, 0, data.length);
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#readData()
	 */
	public synchronized byte[] readData() throws IOException {
		final byte[] data = new byte[dataOut.length];
		System.arraycopy(dataOut, 0, data, 0, data.length);
		return data;
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
		builder.append(frameNumber);
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
	public synchronized void reset() {
		started = false;
		aborted = false;
		terminated = false;
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#start()
	 */
	public void start() {
		synchronized (this) {
			lastUpdate = System.currentTimeMillis();
			started = true;
			aborted = false;
			terminated = false;
		}
		listener.started(jobId, jobDataRow);
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#stop()
	 */
	public void stop() {
		synchronized (this) {
			started = false;
			terminated = true;
		}
		listener.stopped(jobId, jobDataRow);
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#abort()
	 */
	public void abort() {
		synchronized (this) {
			started = false;
			aborted = true;
			terminated = true;
		}
		listener.stopped(jobId, jobDataRow);
	}

	/**
	 * @see net.sf.jame.service.spool.JobInterface#dispose()
	 */
	public synchronized void dispose() {
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
	 * @see net.sf.jame.service.spool.SpoolJobInterface#getJobDataRow()
	 */
	public JobData getJobDataRow() {
		return null;
	}

	/**
	 * @see net.sf.jame.service.spool.SpoolJobInterface#setJobDataRow(JobData)
	 */
	public void setJobDataRow(final JobData job) {
	}

	/**
	 * @see net.sf.jame.service.spool.SpoolJobInterface#getClip()
	 */
	public TwisterClip getClip() throws IOException {
		return null;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#getRAF()
	 */
	public ChunkedRandomAccessFile getRAF() throws IOException {
		return null;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#getTotalFrames()
	 */
	public int getTotalFrames() {
		return 1;
	}

	/**
	 * @see net.sf.jame.service.spool.DistributedSpoolJobInterface#setTerminated(boolean)
	 */
	public synchronized void terminate() {
		terminated = true;
	}
}
