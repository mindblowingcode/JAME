/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
import java.util.List;

import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.queue.DefaultConnectionFactory;
import net.sf.jame.queue.Session;
import net.sf.jame.queue.clip.RenderClip;
import net.sf.jame.queue.clip.RenderClipDataRow;
import net.sf.jame.queue.job.RenderJob;
import net.sf.jame.queue.job.RenderJobDataRow;
import net.sf.jame.queue.job.RenderJobDataTable;

import org.junit.Assert;
import org.junit.Test;

public class TestRenderJobDataTable {
	@Test
	public void testCreate() {
		try {
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			final RenderJobDataTable dataTable = new RenderJobDataTable();
			dataTable.deleteAll(session);
			dataTable.create(session, createJob());
			dataTable.create(session, createJob());
			dataTable.create(session, createJob());
			dataTable.create(session, createJob());
			final List<RenderJobDataRow> jobs = dataTable.loadAll(session);
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
	public void testSave() {
		try {
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			final RenderJobDataTable dataTable = new RenderJobDataTable();
			dataTable.deleteAll(session);
			RenderJobDataRow job = createJob();
			dataTable.create(session, job);
			job.setProfileName("Name");
			dataTable.save(session, job);
			job = dataTable.load(session, job.getJobId());
			System.out.println(job);
			Assert.assertEquals("Name", job.getProfileName());
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
			final RenderJobDataTable dataTable = new RenderJobDataTable();
			dataTable.deleteAll(session);
			final RenderJobDataRow job = createJob();
			dataTable.create(session, job);
			dataTable.delete(session, job);
			final List<RenderJobDataRow> jobs = dataTable.loadAll(session);
			Assert.assertEquals(0, jobs.size());
			session.closeTransaction();
			session.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testLoad() {
		try {
			final ConnectionFactory factory = new DefaultConnectionFactory(new File("workdir"));
			final Session session = new Session(factory);
			session.openTransaction();
			final RenderJobDataTable dataTable = new RenderJobDataTable();
			dataTable.deleteAll(session);
			final RenderJobDataRow job = createJob();
			for (int i = 0; i < 4; i++) {
				dataTable.create(session, job);
				job.setProfileName("Name" + i);
				dataTable.save(session, job);
			}
			final RenderClipDataRow clip = new RenderClipDataRow(new RenderClip());
			clip.setClipId(1);
			job.setProfileName("%Na%");
			List<RenderJobDataRow> jobs = dataTable.load(session, job);
			printJobs(jobs);
			Assert.assertEquals(4, jobs.size());
			job.setProfileName("%1");
			jobs = dataTable.load(session, job);
			printJobs(jobs);
			Assert.assertEquals(1, jobs.size());
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
