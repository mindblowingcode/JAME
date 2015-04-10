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
package net.sf.jame.service.network;

/**
 * @author Andrea Medeghini
 */
public class LocalServiceEndpoint implements ServiceEndpoint {
	private final LocalService localService;
	private boolean invalidated;
	private volatile int jobCount;
	private volatile long lastUpdate;

	/**
	 * @param localService
	 */
	public LocalServiceEndpoint(final LocalService localService) {
		this.localService = localService;
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#createSession(net.sf.jame.service.network.ServiceListener)
	 */
	public ServiceSession createSession(final ServiceListener listener) throws ServiceException {
		ServiceSession session = localService.createSession(listener);
		session.setEndpoint(this);
		return session;
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#invalidate()
	 */
	public void invalidate() {
		invalidated = true;
	}

	/**
	 * @see net.sf.jame.service.network.ServiceEndpoint#isInvalidated()
	 */
	public boolean isInvalidated() {
		return invalidated;
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
