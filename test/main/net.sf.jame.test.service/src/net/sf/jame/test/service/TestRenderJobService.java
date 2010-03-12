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
package net.sf.jame.test.service;

import java.io.File;
import java.util.List;

import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.service.DefaultConnectionFactory;
import net.sf.jame.service.Session;
import net.sf.jame.service.job.RenderJob;
import net.sf.jame.service.job.RenderJobDataRow;
import net.sf.jame.service.job.RenderJobService;

import org.junit.Assert;
import org.junit.Test;

public class TestRenderJobService {
	@Test
	public void testCreate() {
		try {
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			final RenderJobService service = new RenderJobService(new File("workdir"));
			service.deleteAll(session);
			service.create(session, createJob());
			service.create(session, createJob());
			service.create(session, createJob());
			service.create(session, createJob());
			final List<RenderJobDataRow> jobs = service.loadAll(session);
			printJobs(jobs);
			Assert.assertEquals(4, jobs.size());
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
			final RenderJobService service = new RenderJobService(new File("workdir"));
			service.deleteAll(session);
			final RenderJobDataRow job = createJob();
			service.create(session, job);
			service.delete(session, job);
			final List<RenderJobDataRow> jobs = service.loadAll(session);
			Assert.assertEquals(0, jobs.size());
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
		job.setProfileName("Test Name");
		return job;
	}

	private void printJobs(final List<RenderJobDataRow> jobs) {
		for (final RenderJobDataRow job : jobs) {
			System.out.println(job);
		}
	}
}
