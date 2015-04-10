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
package net.sf.jame.test.service;

import java.io.File;

import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.service.DefaultConnectionFactory;
import net.sf.jame.service.Session;
import net.sf.jame.service.profile.RenderProfile;
import net.sf.jame.service.profile.RenderProfileDataRow;
import net.sf.jame.service.profile.RenderProfileFilesystemCache;

import org.junit.Assert;
import org.junit.Test;

public class TestRenderProfileFilesystemCache {
	private int lastId = 1;

	@Test
	public void testCreate() {
		try {
			final RenderProfileFilesystemCache fileCache = new RenderProfileFilesystemCache(new File("workdir/profile"));
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			fileCache.deleteAll();
			fileCache.create(createProfile().getProfileId());
			fileCache.create(createProfile().getProfileId());
			fileCache.create(createProfile().getProfileId());
			fileCache.create(createProfile().getProfileId());
			final File[] profiles = fileCache.list();
			printClips(profiles);
			Assert.assertEquals(4, profiles.length);
			session.closeTransaction();
			session.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testDelete() {
		try {
			final RenderProfileFilesystemCache fileCache = new RenderProfileFilesystemCache(new File("workdir/profile"));
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			fileCache.deleteAll();
			final RenderProfileDataRow profile = createProfile();
			fileCache.create(profile.getProfileId());
			fileCache.delete(profile.getProfileId());
			final File[] profiles = fileCache.list();
			Assert.assertEquals(0, profiles.length);
			session.closeTransaction();
			session.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private RenderProfileDataRow createProfile() {
		final RenderProfileDataRow profile = new RenderProfileDataRow(new RenderProfile());
		profile.setProfileId(lastId);
		profile.setProfileName("Test Name");
		profile.setClipId(1);
		profile.setImageWidth(320);
		profile.setImageHeight(200);
		profile.setFrameRate(0);
		profile.setStartTime(0);
		profile.setStopTime(0);
		profile.setQuality(100);
		lastId += 1;
		return profile;
	}

	private void printClips(final File[] profiles) {
		for (final File profile : profiles) {
			System.out.println(profile);
		}
	}
}
