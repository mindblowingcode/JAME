/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.service;

import net.sf.jame.service.clip.RenderClipDataRow;
import net.sf.jame.service.job.RenderJobDataRow;
import net.sf.jame.service.profile.RenderProfileDataRow;

/**
 * @author Andrea Medeghini
 */
public interface LibraryServiceListener {
	/**
	 * @param clip
	 */
	public void clipCreated(RenderClipDataRow clip);

	/**
	 * @param clip
	 */
	public void clipDeleted(RenderClipDataRow clip);

	/**
	 * @param clip
	 */
	public void clipUpdated(RenderClipDataRow clip);

	/**
	 * @param clip
	 */
	public void clipLoaded(RenderClipDataRow clip);

	/**
	 * @param profile
	 */
	public void profileCreated(RenderProfileDataRow profile);

	/**
	 * @param profile
	 */
	public void profileDeleted(RenderProfileDataRow profile);

	/**
	 * @param profile
	 */
	public void profileUpdated(RenderProfileDataRow profile);

	/**
	 * @param profile
	 */
	public void profileLoaded(RenderProfileDataRow profile);

	/**
	 * @param job
	 */
	public void jobCreated(RenderJobDataRow job);

	/**
	 * @param job
	 */
	public void jobDeleted(RenderJobDataRow job);

	/**
	 * @param job
	 */
	public void jobUpdated(RenderJobDataRow job);

	/**
	 * @param job
	 */
	public void jobStarted(RenderJobDataRow job);

	/**
	 * @param job
	 */
	public void jobAborted(RenderJobDataRow job);

	/**
	 * @param job
	 */
	public void jobStopped(RenderJobDataRow job);

	/**
	 * @param job
	 */
	public void jobResumed(RenderJobDataRow job);
}
