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
import java.util.List;

import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.service.DefaultConnectionFactory;
import net.sf.jame.service.Session;
import net.sf.jame.service.clip.RenderClip;
import net.sf.jame.service.clip.RenderClipDataRow;
import net.sf.jame.service.clip.RenderClipService;

import org.junit.Assert;
import org.junit.Test;

public class TestMovieClipService {
	@Test
	public void testCreate() {
		try {
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			final RenderClipService service = new RenderClipService(new File("workdir"));
			service.deleteAll(session);
			service.create(session, createClip());
			service.create(session, createClip());
			service.create(session, createClip());
			service.create(session, createClip());
			final List<RenderClipDataRow> clips = service.loadAll(session);
			printClips(clips);
			Assert.assertEquals(4, clips.size());
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
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			final RenderClipService service = new RenderClipService(new File("workdir"));
			service.deleteAll(session);
			final RenderClipDataRow clip = createClip();
			service.create(session, clip);
			service.delete(session, clip);
			final List<RenderClipDataRow> clips = service.loadAll(session);
			Assert.assertEquals(0, clips.size());
			session.closeTransaction();
			session.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private RenderClipDataRow createClip() {
		final RenderClipDataRow clip = new RenderClipDataRow(new RenderClip());
		clip.setClipName("Test Name");
		clip.setDescription("Test Description");
		return clip;
	}

	private void printClips(final List<RenderClipDataRow> clips) {
		for (final RenderClipDataRow clip : clips) {
			System.out.println(clip);
		}
	}
}
