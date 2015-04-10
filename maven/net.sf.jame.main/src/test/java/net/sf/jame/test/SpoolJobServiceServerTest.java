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
package net.sf.jame.test;

import java.io.File;
import java.util.logging.Logger;

import net.sf.jame.core.util.DefaultThreadFactory;
import net.sf.jame.core.util.Surface;
import net.sf.jame.core.util.Worker;
import net.sf.jame.queue.jxta.JXTANetworkService;
import net.sf.jame.queue.network.spool.DistributedServiceProcessor;
import net.sf.jame.queue.spool.DefaultDistributedJobService;
import net.sf.jame.queue.spool.job.DistributedJob;
import net.sf.jame.queue.spool.job.DistributedJobFactory;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class SpoolJobServiceServerTest {
	private static final Logger logger = Logger.getLogger(SpoolJobServiceServerTest.class.getName());

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
		final File tmpDir = new File("workdir/tmp");
		tmpDir.mkdirs();
		deleteFiles(tmpDir);
		Worker worker = new Worker(new DefaultThreadFactory("TestSpool Worker", true, Thread.MIN_PRIORITY));
		final DistributedServiceProcessor processor = new DistributedServiceProcessor(new DefaultDistributedJobService<DistributedJob>(0, "DistributedProcessor", new DistributedJobFactory(tmpDir, worker), worker), 10);
		final JXTANetworkService service = new JXTANetworkService(tmpDir, "http://jame.sf.net", "JXTASpool", "Andrea Medeghini", "1.0", processor);
		processor.start();
		service.start();
		worker.start();
		try {
			while (true) {
				logger.info("Server running...");
				Thread.sleep(10000);
			}
		}
		catch (final InterruptedException e) {
		}
		worker.stop();
		service.stop();
		processor.stop();
	}
}
