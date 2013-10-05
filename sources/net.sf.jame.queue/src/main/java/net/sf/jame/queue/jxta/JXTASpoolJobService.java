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
package net.sf.jame.queue.jxta;

import net.sf.jame.core.util.Worker;
import net.sf.jame.queue.network.DiscoveryService;
import net.sf.jame.queue.network.spool.AbstractSpoolJobService;
import net.sf.jame.queue.spool.DistributedSpoolJobInterface;
import net.sf.jame.queue.spool.JobFactory;

/**
 * @author Andrea Medeghini
 */
public class JXTASpoolJobService extends AbstractSpoolJobService {
	/**
	 * @param serviceId
	 * @param discoveryService
	 * @param jobFactory
	 * @param worker
	 */
	public JXTASpoolJobService(final int serviceId, final DiscoveryService discoveryService, final JobFactory<? extends DistributedSpoolJobInterface> jobFactory, final Worker worker) {
		super(serviceId, "JXTAProcessor", discoveryService, jobFactory, worker);
	}
}
