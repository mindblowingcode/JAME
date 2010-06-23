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
package net.sf.jame.service.spool;

import java.io.Serializable;

/**
 * @author Andrea Medeghini
 */
public class JobStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int frameNumber;
	private final String jobId;
	private final int jobCount;

	/**
	 * @param jobId
	 * @param frameNumber
	 * @param jobCount
	 */
	public JobStatus(final String jobId, final int frameNumber, final int jobCount) {
		this.frameNumber = frameNumber;
		this.jobCount = jobCount;
		this.jobId = jobId;
	}

	/**
	 * @return the status
	 */
	public int getFrameNumber() {
		return frameNumber;
	}

	/**
	 * @return the jobCount
	 */
	public int getJobCount() {
		return jobCount;
	}

	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("jobId = ");
		builder.append(jobId);
		builder.append(", frameNumber = ");
		builder.append(frameNumber);
		builder.append(", jobCount = ");
		builder.append(jobCount);
		return builder.toString();
	}
}
