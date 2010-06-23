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
package net.sf.jame.twister.swing;

import net.sf.jame.service.LibraryServiceListener;
import net.sf.jame.service.clip.RenderClipDataRow;
import net.sf.jame.service.job.RenderJobDataRow;
import net.sf.jame.service.profile.RenderProfileDataRow;

/**
 * @author Andrea Medeghini
 */
public class ServiceAdapter implements LibraryServiceListener {
	/**
	 * @see net.sf.jame.service.LibraryServiceListener#clipCreated(net.sf.jame.service.clip.RenderClipDataRow)
	 */
	public void clipCreated(final RenderClipDataRow clip) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#clipDeleted(net.sf.jame.service.clip.RenderClipDataRow)
	 */
	public void clipDeleted(final RenderClipDataRow clip) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#clipUpdated(net.sf.jame.service.clip.RenderClipDataRow)
	 */
	public void clipUpdated(final RenderClipDataRow clip) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#clipLoaded(net.sf.jame.service.clip.RenderClipDataRow)
	 */
	public void clipLoaded(final RenderClipDataRow clip) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#profileCreated(net.sf.jame.service.profile.RenderProfileDataRow)
	 */
	public void profileCreated(final RenderProfileDataRow profile) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#profileDeleted(net.sf.jame.service.profile.RenderProfileDataRow)
	 */
	public void profileDeleted(final RenderProfileDataRow profile) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#profileUpdated(net.sf.jame.service.profile.RenderProfileDataRow)
	 */
	public void profileUpdated(final RenderProfileDataRow profile) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#profileLoaded(net.sf.jame.service.profile.RenderProfileDataRow)
	 */
	public void profileLoaded(final RenderProfileDataRow profile) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobCreated(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobCreated(final RenderJobDataRow job) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobDeleted(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobDeleted(final RenderJobDataRow job) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobStarted(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobStarted(final RenderJobDataRow job) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobAborted(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobAborted(final RenderJobDataRow job) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobStopped(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobStopped(final RenderJobDataRow job) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobUpdated(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobUpdated(final RenderJobDataRow job) {
	}

	/**
	 * @see net.sf.jame.service.LibraryServiceListener#jobResumed(net.sf.jame.service.job.RenderJobDataRow)
	 */
	public void jobResumed(final RenderJobDataRow job) {
	}
}
