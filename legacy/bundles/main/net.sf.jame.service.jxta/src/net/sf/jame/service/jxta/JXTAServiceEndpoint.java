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

import net.jxta.protocol.PipeAdvertisement;
import net.sf.jame.service.network.ServiceEndpoint;
import net.sf.jame.service.network.ServiceException;
import net.sf.jame.service.network.ServiceListener;
import net.sf.jame.service.network.ServiceSession;

/**
 * @author Andrea Medeghini
 */
public class JXTAServiceEndpoint implements ServiceEndpoint {
	private final JXTANetworkService networkService;
	private final String serviceName;
	private final PipeAdvertisement pipeadv;
	private volatile int jobCount;
	private volatile long lastUpdate;

	/**
	 * @param networkService
	 * @param pipeadv
	 */
	public JXTAServiceEndpoint(final JXTANetworkService networkService, final PipeAdvertisement pipeadv) {
		serviceName = "RemoteService (pipeID = " + pipeadv.getPipeID() + ")";
		this.networkService = networkService;
		this.pipeadv = pipeadv;
	}

	/**
	 * @return the name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("serviceName = ");
		builder.append(serviceName);
		return builder.toString();
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#createSession(net.sf.jame.service.network.ServiceListener)
	 */
	public ServiceSession createSession(final ServiceListener listener) throws ServiceException {
		ServiceSession session = networkService.createSession(pipeadv, listener);
		session.setEndpoint(this);
		return session;
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#invalidate()
	 */
	public void invalidate() {
		networkService.invalidate(pipeadv);
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#isInvalidated()
	 */
	public boolean isInvalidated() {
		return networkService.isInvalidated(pipeadv);
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#setJobCount(int)
	 */
	public void setJobCount(final int jobCount) {
		this.jobCount = jobCount;
		lastUpdate = System.currentTimeMillis();
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#getJobCount()
	 */
	public int getJobCount() {
		return jobCount;
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#getJobCountLastUpdate()
	 */
	public long getJobCountLastUpdate() {
		return lastUpdate;
	}
}
