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
package net.sf.jame.queue.network;

/**
 * @author Andrea Medeghini
 */
public abstract class ServiceSession {
	protected ServiceConsumer consumer;
	protected ServiceProducer producer;
	private ServiceEndpoint endpoint;
	protected String sessionId;
	private boolean invalidated;

	/**
	 * @param sessionId
	 * @param consumer
	 * @param producer
	 */
	public ServiceSession(final String sessionId, final ServiceConsumer consumer, final ServiceProducer producer) {
		this.sessionId = sessionId;
		this.consumer = consumer;
		this.producer = producer;
		if (sessionId == null) {
			throw new IllegalArgumentException("Parameter sessionId is null");
		}
		if (consumer == null) {
			throw new IllegalArgumentException("Parameter consumer is null");
		}
		if (producer == null) {
			throw new IllegalArgumentException("Parameter producer is null");
		}
	}

	/**
	 * @return
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 
	 */
	public void dispose() {
		if (consumer != null) {
			consumer.dispose();
			consumer = null;
		}
		if (producer != null) {
			producer.dispose();
			producer = null;
		}
		endpoint = null;
	}

	/**
	 * @return
	 */
	public boolean isExpired() {
		return invalidated || (producer == null) || (consumer == null) || consumer.isTimeout();
	}

	/**
	 * @param message
	 * @throws Exception
	 */
	public void sendMessage(final ServiceMessage message) throws ServiceException {
		if (isExpired()) {
			throw new ServiceException("Expired session " + sessionId);
		}
		producer.sendMessage(message);
	}

	/**
	 * 
	 */
	public void invalidate() {
		invalidated = true;
	}

	/**
	 * @return
	 */
	public abstract boolean isLocalSession();

	/**
	 * @throws ServiceException
	 */
	public void sendKeepAliveMessage() throws ServiceException {
		if (isExpired()) {
			throw new ServiceException("Expired session " + sessionId);
		}
		producer.sendKeepAliveMessage();
	}

	/**
	 * @throws ServiceException
	 */
	public void sendAckMessage() throws ServiceException {
		if (isExpired()) {
			throw new ServiceException("Expired session " + sessionId);
		}
		producer.sendAckMessage();
	}

	/**
	 * @param endpoint
	 */
	public void setEndpoint(final ServiceEndpoint endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @param jobCount
	 */
	public void setEndpointJobCount(final int jobCount) {
		endpoint.setJobCount(jobCount);
	}
}
