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
import java.io.OutputStream;
import java.util.HashMap;

import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.core.util.DefaultThreadFactory;
import net.sf.jame.core.util.ProgressListener;
import net.sf.jame.core.util.Surface;
import net.sf.jame.core.util.Worker;
import net.sf.jame.core.xml.XML;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.service.DefaultConnectionFactory;
import net.sf.jame.service.LibraryService;
import net.sf.jame.service.LibraryServiceListener;
import net.sf.jame.service.Session;
import net.sf.jame.service.clip.RenderClip;
import net.sf.jame.service.clip.RenderClipDataRow;
import net.sf.jame.service.encoder.extensions.JPEGEncoderConfig;
import net.sf.jame.service.encoder.extensions.JPEGEncoderRuntime;
import net.sf.jame.service.job.RenderJobDataRow;
import net.sf.jame.service.jxta.JXTADiscoveryService;
import net.sf.jame.service.jxta.JXTANetworkService;
import net.sf.jame.service.jxta.JXTASpoolJobService;
import net.sf.jame.service.network.spool.DistributedServiceProcessor;
import net.sf.jame.service.network.spool.LocalServiceProcessor;
import net.sf.jame.service.profile.RenderProfile;
import net.sf.jame.service.profile.RenderProfileDataRow;
import net.sf.jame.service.spool.DefaultDistributedJobService;
import net.sf.jame.service.spool.DefaultJobService;
import net.sf.jame.service.spool.JobData;
import net.sf.jame.service.spool.JobListener;
import net.sf.jame.service.spool.job.DistributedJob;
import net.sf.jame.service.spool.job.DistributedJobFactory;
import net.sf.jame.service.spool.job.DistributedSpoolJobFactory;
import net.sf.jame.service.spool.job.LocalSpoolJob;
import net.sf.jame.service.spool.job.LocalSpoolJobFactory;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.TwisterClipXMLExporter;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.TwisterConfigBuilder;
import net.sf.jame.twister.TwisterSequence;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class SpoolJobServiceTest {
	private static final Logger logger = Logger.getLogger(SpoolJobServiceTest.class);

	private void deleteFiles(final File path) {
		final File[] files = path.listFiles();
		for (final File file : files) {
			if (file.isDirectory()) {
				deleteFiles(file);
			}
			file.delete();
		}
	}

	@Test
	public void testSpool() throws Exception {
		@SuppressWarnings("unused")
		final Surface surface = new Surface(200, 200);
		final File workDir = new File("workdir");
		final File tmpDir = new File(workDir, "tmp");
		tmpDir.mkdirs();
		deleteFiles(tmpDir);
		final ConnectionFactory factory = new DefaultConnectionFactory(workDir);
		final Session session = new Session(factory);
		final LibraryService service = new LibraryService(session, workDir);
		final RenderClipDataRow clipDataRow = new RenderClipDataRow(new RenderClip());
		clipDataRow.setClipName("test clip");
		service.createClip(clipDataRow, null);
		final RenderProfileDataRow renderProfile = new RenderProfileDataRow(new RenderProfile());
		renderProfile.setProfileName("test profile");
		renderProfile.setClipName(clipDataRow.getClipName());
		renderProfile.setClipId(clipDataRow.getClipId());
		renderProfile.setImageWidth(640);
		renderProfile.setImageHeight(480);
		renderProfile.setOffsetX(0);
		renderProfile.setOffsetY(0);
		renderProfile.setStartTime(0);
		renderProfile.setStopTime(0);
		service.createProfile(renderProfile);
		final TwisterClip clip = new TwisterClip();
		final TwisterSequence sequence = new TwisterSequence();
		clip.addSequence(sequence);
		final TwisterConfigBuilder configBuilder = new TwisterConfigBuilder();
		final TwisterConfig config = configBuilder.createDefaultConfig();
		sequence.setFinalConfig(config);
		final TwisterClipXMLExporter exporter = new TwisterClipXMLExporter();
		final Document doc = XML.createDocument();
		final XMLNodeBuilder builder = XML.createDefaultXMLNodeBuilder(doc);
		final Element element = exporter.exportToElement(clip, builder);
		doc.appendChild(element);
		final OutputStream os = service.getClipOutputStream(clipDataRow.getClipId());
		XML.saveDocument(os, "twister-clip.xml", doc);
		os.close();
		Worker worker = new Worker(new DefaultThreadFactory("TestSpool Worker", true, Thread.MIN_PRIORITY));
		final DistributedServiceProcessor processor1 = new DistributedServiceProcessor(new DefaultDistributedJobService<DistributedJob>(0, "DistributedProcessor", new DistributedJobFactory(tmpDir, worker), worker), 10);
		final LocalServiceProcessor processor2 = new LocalServiceProcessor(new DefaultJobService<LocalSpoolJob>(0, "LocalProcessor", new LocalSpoolJobFactory(service, worker), worker), 10);
		final JXTASpoolJobService jobService = new JXTASpoolJobService(0, new JXTADiscoveryService(new JXTANetworkService(tmpDir, "http://jame.sf.net", "JXTASpool", "Andrea Medeghini", "1.0", processor1), processor2), new DistributedSpoolJobFactory(service, worker), worker);
		processor1.start();
		processor2.start();
		jobService.start();
		worker.start();
		final HashMap<Integer, String> jobs = new HashMap<Integer, String>();
		service.addServiceListener(new LibraryServiceListener() {
			public void clipCreated(final RenderClipDataRow clip) {
			}

			public void clipDeleted(final RenderClipDataRow clip) {
			}

			public void clipUpdated(final RenderClipDataRow clip) {
			}

			public void clipLoaded(final RenderClipDataRow clip) {
			}

			public void jobCreated(final RenderJobDataRow job) {
				final String jobId = jobService.createJob(new TestListener());
				jobService.setJobData(jobId, job, job.getFrameNumber());
				jobs.put(job.getJobId(), jobId);
				logger.info("Job " + jobId + " created");
			}

			public void jobDeleted(final RenderJobDataRow job) {
				final String jobId = jobs.get(job.getJobId());
				jobService.deleteJob(jobId);
				logger.info("Job " + jobId + " deleted");
			}

			public void jobStarted(final RenderJobDataRow job) {
				final String jobId = jobs.get(job.getJobId());
				jobService.runJob(jobId);
				logger.info("Job " + jobId + " started");
			}

			public void jobStopped(final RenderJobDataRow job) {
				final String jobId = jobs.get(job.getJobId());
				jobService.stopJob(jobId);
				logger.info("Job " + jobId + " stopped");
			}

			public void jobAborted(final RenderJobDataRow job) {
			}

			public void jobUpdated(final RenderJobDataRow job) {
			}

			public void jobResumed(final RenderJobDataRow job) {
			}

			public void profileCreated(final RenderProfileDataRow profile) {
			}

			public void profileDeleted(final RenderProfileDataRow profile) {
			}

			public void profileUpdated(final RenderProfileDataRow profile) {
			}

			public void profileLoaded(final RenderProfileDataRow profile) {
			}
		});
		ProgressListener listener = new ProgressListener() {
			public void done() {
			}

			public void failed(final Throwable e) {
			}

			public void stateChanged(final String message, final int percentage) {
			}

			public void stateChanged(final String message) {
			}
		};
		service.createJobs(renderProfile.getProfileId(), listener, "Creating jobs", 100f);
		service.startJobs(renderProfile.getProfileId(), listener, "Creating jobs", 100f);
		Thread.sleep(5000);
		try {
			while (true) {
				// boolean terminated = true;
				// for (final DistributedSpoolJob job : jobs.values()) {
				// if (!job.isTerminated()) {
				// terminated = false;
				// }
				// }
				// if (terminated) {
				// break;
				// }
				Thread.sleep(60000);
			}
		}
		catch (final InterruptedException e) {
		}
		service.stopJobs(renderProfile.getProfileId(), listener, "Creating jobs", 100f);
		// for (final DistributedSpoolJob job : jobs.values()) {
		// service.jobCompleted(job.getJobDataRow());
		// }
		final JPEGEncoderRuntime encoder = new JPEGEncoderRuntime();
		encoder.setConfig(new JPEGEncoderConfig());
		service.exportProfile(renderProfile, encoder, listener, new File("test.jpeg"));
		worker.stop();
		jobService.stop();
		processor1.stop();
		processor2.stop();
		session.close();
	}

	private static class TestListener implements JobListener {
		/**
		 * @see net.sf.jame.service.spool.JobListener#updated(String, JobData)
		 */
		public void updated(final String jobId, final JobData job) {
			logger.info("Job state changed " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#started(String, JobData)
		 */
		public void started(final String jobId, final JobData job) {
			logger.info("Job started " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#stopped(String, JobData)
		 */
		public void stopped(final String jobId, final JobData job) {
			logger.info("Job stopped " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#terminated(String, JobData)
		 */
		public void terminated(final String jobId, final JobData job) {
			logger.info("Job terminated " + job);
		}

		/**
		 * @see net.sf.jame.service.spool.JobListener#disposed(String, JobData)
		 */
		public void disposed(final String jobId, final JobData job) {
			logger.info("Job disposed " + job);
		}
	}
}
