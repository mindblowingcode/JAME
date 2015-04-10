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
package net.sf.jame.service.jxta.extensions;

import java.io.File;

import net.sf.jame.core.util.Worker;
import net.sf.jame.service.LibraryService;
import net.sf.jame.service.jxta.JXTADiscoveryService;
import net.sf.jame.service.jxta.JXTANetworkService;
import net.sf.jame.service.jxta.JXTASpoolJobService;
import net.sf.jame.service.network.spool.DistributedServiceProcessor;
import net.sf.jame.service.network.spool.LocalServiceProcessor;
import net.sf.jame.service.spool.DefaultDistributedJobService;
import net.sf.jame.service.spool.DefaultJobService;
import net.sf.jame.service.spool.JobService;
import net.sf.jame.service.spool.SpoolJobInterface;
import net.sf.jame.service.spool.extension.AbstractSpoolExtensionRuntime;
import net.sf.jame.service.spool.job.DistributedJob;
import net.sf.jame.service.spool.job.DistributedJobFactory;
import net.sf.jame.service.spool.job.DistributedSpoolJobFactory;
import net.sf.jame.service.spool.job.LocalSpoolJob;
import net.sf.jame.service.spool.job.LocalSpoolJobFactory;

/**
 * @author Andrea Medeghini
 */
public class JXTASpoolRuntime extends AbstractSpoolExtensionRuntime<JXTASpoolConfig> {
	/**
	 * @see net.sf.jame.service.spool.extension.SpoolExtensionRuntime#getJobService(net.sf.jame.service.LibraryService)
	 */
	@Override
	public JobService<? extends SpoolJobInterface> getJobService(final int serviceId, final LibraryService service, final Worker worker) {
		final File tmpDir = new File(service.getWorkspace(), "JXTASpool");
		final DistributedServiceProcessor processor1 = new DistributedServiceProcessor(new DefaultDistributedJobService<DistributedJob>(serviceId, "DistributedProcessor", new DistributedJobFactory(new File(tmpDir, "spool"), worker), worker), 10);
		final LocalServiceProcessor processor2 = new LocalServiceProcessor(new DefaultJobService<LocalSpoolJob>(serviceId, "LocalProcessor", new LocalSpoolJobFactory(service, worker), worker), 10);
		final JXTASpoolJobService jobService = new JXTASpoolJobService(serviceId, new JXTADiscoveryService(new JXTANetworkService(tmpDir, "http://jame.sf.net", "JXTASpool", "Andrea Medeghini", "2.0.0", processor1), processor2), new DistributedSpoolJobFactory(service, worker), worker);
		return jobService;
	}
}
