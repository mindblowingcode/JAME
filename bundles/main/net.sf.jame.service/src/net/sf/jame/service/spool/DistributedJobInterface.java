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

import java.io.IOException;

import net.sf.jame.service.io.ChunkedRandomAccessFile;
import net.sf.jame.twister.TwisterClip;

/**
 * @author Andrea Medeghini
 */
public interface DistributedJobInterface extends JobInterface {
	/**
	 * @param jobData
	 */
	public void setJobData(byte[] jobData);

	/**
	 * @return
	 */
	public byte[] getJobData();

	/**
	 * @param job
	 */
	public void setJobDataRow(JobData job);

	/**
	 * @return
	 */
	public JobData getJobDataRow();

	/**
	 * @param job
	 */
	public void setClip(TwisterClip clip);

	/**
	 * @return
	 */
	public TwisterClip getClip();

	/**
	 * @param frameNumber
	 */
	public void setFirstFrameNumber(int frameNumber);

	/**
	 * @return
	 * @throws IOException
	 */
	public ChunkedRandomAccessFile getRAF() throws IOException;
}
