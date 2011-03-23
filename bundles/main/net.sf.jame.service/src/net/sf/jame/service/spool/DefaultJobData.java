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

/**
 * @author Andrea Medeghini
 */
public class DefaultJobData implements JobData {
	private static final long serialVersionUID = 1L;
	private final int jobId;
	private final int profileId;
	private final int clipId;
	private final int imageWidth;
	private final int imageHeight;
	private final int frameRate;
	private final int startTime;
	private final int stopTime;
	private final int quality;
	private final int frameNumber;
	private final int tileWidth;
	private final int tileHeight;
	private final int borderWidth;
	private final int borderHeight;
	private final int tileOffsetX;
	private final int tileOffsetY;
	private final int jobType;

	/**
	 * @param jobData
	 */
	public DefaultJobData(final JobData jobData) {
		jobId = jobData.getJobId();
		profileId = jobData.getProfileId();
		clipId = jobData.getClipId();
		imageWidth = jobData.getImageWidth();
		imageHeight = jobData.getImageHeight();
		frameRate = jobData.getFrameRate();
		startTime = jobData.getStartTime();
		stopTime = jobData.getStopTime();
		quality = jobData.getQuality();
		frameNumber = jobData.getFrameNumber();
		tileWidth = jobData.getTileWidth();
		tileHeight = jobData.getTileHeight();
		borderWidth = jobData.getBorderWidth();
		borderHeight = jobData.getBorderHeight();
		tileOffsetX = jobData.getTileOffsetX();
		tileOffsetY = jobData.getTileOffsetY();
		jobType = jobData.getJobType();
	}

	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}

	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * @return the clipId
	 */
	public int getClipId() {
		return clipId;
	}

	/**
	 * @return the imageWidth
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * @return the imageHeight
	 */
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * @return the frameRate
	 */
	public int getFrameRate() {
		return frameRate;
	}

	/**
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * @return the stopTime
	 */
	public int getStopTime() {
		return stopTime;
	}

	/**
	 * @return the quality
	 */
	public int getQuality() {
		return quality;
	}

	/**
	 * @return the frameNumber
	 */
	public int getFrameNumber() {
		return frameNumber;
	}

	/**
	 * @return the tileWidth
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * @return the tileHeight
	 */
	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * @return the borderWidth
	 */
	public int getBorderWidth() {
		return borderWidth;
	}

	/**
	 * @return the borderHeight
	 */
	public int getBorderHeight() {
		return borderHeight;
	}

	/**
	 * @return the tileOffsetX
	 */
	public int getTileOffsetX() {
		return tileOffsetX;
	}

	/**
	 * @return the tileOffsetY
	 */
	public int getTileOffsetY() {
		return tileOffsetY;
	}

	/**
	 * @see net.sf.jame.service.spool.JobData#setFrameNumber(int)
	 */
	public void setFrameNumber(final int frame) {
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("[jobId = ");
		builder.append(jobId);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @see net.sf.jame.service.spool.JobData#getJobType()
	 */
	public int getJobType() {
		return jobType;
	}
}
