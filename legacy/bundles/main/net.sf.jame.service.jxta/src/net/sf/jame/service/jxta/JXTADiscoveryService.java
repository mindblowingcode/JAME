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
package net.sf.jame.service.jxta;

import java.util.LinkedList;
import java.util.List;

import net.sf.jame.service.network.DiscoveryService;
import net.sf.jame.service.network.LocalService;
import net.sf.jame.service.network.LocalServiceEndpoint;
import net.sf.jame.service.network.ServiceEndpoint;
import net.sf.jame.service.network.ServiceProcessor;

/**
 * @author Andrea Medeghini
 */
public class JXTADiscoveryService implements DiscoveryService {
	private final List<ServiceEndpoint> endpoints = new LinkedList<ServiceEndpoint>();
	private final JXTANetworkService networkService;
	private final LocalService localService;
	private final LocalServiceEndpoint localEndpoint;

	/**
	 * @param networkService
	 * @param processor
	 */
	public JXTADiscoveryService(final JXTANetworkService networkService, final ServiceProcessor processor) {
		this.networkService = networkService;
		localService = new LocalService("LOCAL", processor);
		localEndpoint = new LocalServiceEndpoint(localService);
	}

	/**
	 * @see net.sf.jame.service.network.DiscoveryService#getEndpoints()
	 */
	public List<ServiceEndpoint> getEndpoints() {
		endpoints.clear();
		for (ServiceEndpoint endpoint : networkService.getEndpoints()) {
			if (!endpoint.isInvalidated()) {
				endpoints.add(endpoint);
			}
		}
		if (!Boolean.getBoolean("excludeLocalEndpoint")) {
			endpoints.add(localEndpoint);
		}
		return endpoints;
	}

	/**
	 * @see net.sf.jame.service.network.DiscoveryService#start()
	 */
	public void start() {
		localService.start();
		networkService.start();
	}

	/**
	 * @see net.sf.jame.service.network.DiscoveryService#stop()
	 */
	public void stop() {
		localService.stop();
		networkService.stop();
	}
}
