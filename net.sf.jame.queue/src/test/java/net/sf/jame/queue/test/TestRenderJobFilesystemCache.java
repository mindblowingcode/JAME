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
package net.sf.jame.queue.test;

import java.io.File;

import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.queue.DefaultConnectionFactory;
import net.sf.jame.queue.Session;
import net.sf.jame.queue.job.RenderJob;
import net.sf.jame.queue.job.RenderJobDataRow;
import net.sf.jame.queue.job.RenderJobFilesystemCache;

import org.junit.Assert;
import org.junit.Test;

public class TestRenderJobFilesystemCache {
	private int lastId = 1;

	@Test
	public void testCreate() {
		try {
			final RenderJobFilesystemCache fileCache = new RenderJobFilesystemCache(new File("workdir/job"));
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			fileCache.deleteAll();
			fileCache.create(createJob().getJobId());
			fileCache.create(createJob().getJobId());
			fileCache.create(createJob().getJobId());
			fileCache.create(createJob().getJobId());
			final File[] jobs = fileCache.list();
			printClips(jobs);
			Assert.assertEquals(4, jobs.length);
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
			final RenderJobFilesystemCache fileCache = new RenderJobFilesystemCache(new File("workdir/job"));
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			fileCache.deleteAll();
			final RenderJobDataRow job = createJob();
			fileCache.create(job.getJobId());
			fileCache.delete(job.getJobId());
			final File[] jobs = fileCache.list();
			Assert.assertEquals(0, jobs.length);
			session.closeTransaction();
			session.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private RenderJobDataRow createJob() {
		final RenderJobDataRow job = new RenderJobDataRow(new RenderJob());
		job.setJobId(lastId);
		job.setProfileName("Test Name");
		lastId += 1;
		return job;
	}

	private void printClips(final File[] jobs) {
		for (final File job : jobs) {
			System.out.println(job);
		}
	}
}
