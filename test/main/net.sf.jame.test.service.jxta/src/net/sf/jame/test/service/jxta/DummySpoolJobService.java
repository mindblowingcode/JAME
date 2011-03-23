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
package net.sf.jame.test.service.jxta;

import java.io.File;

import net.sf.jame.core.util.DefaultThreadFactory;
import net.sf.jame.core.util.Worker;
import net.sf.jame.service.jxta.JXTADiscoveryService;
import net.sf.jame.service.jxta.JXTANetworkService;
import net.sf.jame.service.jxta.JXTASpoolJobService;
import net.sf.jame.service.network.spool.DistributedServiceProcessor;
import net.sf.jame.service.spool.JobData;
import net.sf.jame.service.spool.JobFactory;
import net.sf.jame.service.spool.JobListener;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class DummySpoolJobService {
	private static final Logger logger = Logger.getLogger(DummySpoolJobService.class);

	/**
	 * @param args
	 */
	public static void main(final String args[]) {
		final File tmpDir = new File("workdir/tmp");
		Worker worker = new Worker(new DefaultThreadFactory("DummySpool Worker", true, Thread.MIN_PRIORITY));
		final DistributedServiceProcessor processor1 = new DistributedServiceProcessor(new DummyJobService<DummyJob>("Dummy JobService", new ProcessorJobFactory(), worker), 10);
		final DistributedServiceProcessor processor2 = new DistributedServiceProcessor(new DummyJobService<DummyJob>("Dummy JobService", new ProcessorJobFactory(), worker), 10);
		final JXTASpoolJobService service = new JXTASpoolJobService(0, new JXTADiscoveryService(new JXTANetworkService(tmpDir, "http://jame.sf.net", "JXTASpool", "Andrea Medeghini", "1.0", processor1), processor2), new SpoolJobFactory(), worker);
		processor1.start();
		processor2.start();
		service.start();
		worker.start();
		try {
			for (int i = 0; i < 50; i++) {
				final String jobId = service.createJob(new TestListener());
				logger.debug("Job " + i + " runned, jobId = " + jobId);
				service.runJob(jobId);
			}
			while (true) {
				Thread.sleep(10000);
			}
		}
		catch (final InterruptedException e) {
		}
		worker.stop();
		service.stop();
		processor1.stop();
		processor2.stop();
	}

	private static class TestListener implements JobListener {
		/**
		 * @see net.sf.jame.service.spool.JobListener#updated(String, JobData)
		 */
		public void updated(final String jobId, final JobData job) {
			logger.debug("Job state changed " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#started(String, JobData)
		 */
		public void started(final String jobId, final JobData job) {
			logger.debug("Job started " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#stopped(String, JobData)
		 */
		public void stopped(final String jobId, final JobData job) {
			logger.debug("Job stopped " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#terminated(String, JobData)
		 */
		public void terminated(final String jobId, final JobData job) {
			logger.debug("Job terminated " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#disposed(String, JobData)
		 */
		public void disposed(final String jobId, final JobData job) {
			logger.debug("Job disposed " + job);
		}
	}

	private static class ProcessorJobFactory implements JobFactory<DummyJob> {
		/**
		 * @see net.sf.jame.service.spool.JobFactory#createJob(java.lang.String, net.sf.jame.service.spool.JobListener)
		 */
		public DummyJob createJob(final String jobId, final JobListener listener) {
			final byte[] dataIn = new byte[128];
			final byte[] dataOut = new byte[128];
			final DummyJob job = new DummyJob(dataIn, dataOut, listener);
			job.setJobId(jobId);
			return job;
		}
	}

	private static class SpoolJobFactory implements JobFactory<DummyRemoteJob> {
		/**
		 * @see net.sf.jame.service.spool.RemoteJobFactory#createJob(java.lang.String, net.sf.jame.service.spool.JobListener)
		 */
		public DummyRemoteJob createJob(final String jobId, final JobListener listener) {
			final byte[] dataIn = new byte[128];
			final byte[] dataOut = new byte[128];
			final DummyRemoteJob job = new DummyRemoteJob(dataIn, dataOut, listener);
			job.setJobId(jobId);
			return job;
		}
	}
}
