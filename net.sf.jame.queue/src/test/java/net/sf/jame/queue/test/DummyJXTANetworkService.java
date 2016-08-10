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
package net.sf.jame.queue.test;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import net.sf.jame.queue.jxta.JXTADiscoveryService;
import net.sf.jame.queue.jxta.JXTANetworkService;
import net.sf.jame.queue.network.RequestIDFactory;
import net.sf.jame.queue.network.RequestMessage;
import net.sf.jame.queue.network.ServiceEndpoint;
import net.sf.jame.queue.network.ServiceException;
import net.sf.jame.queue.network.ServiceListener;
import net.sf.jame.queue.network.ServiceMessage;
import net.sf.jame.queue.network.ServiceProcessor;
import net.sf.jame.queue.network.ServiceSession;
import net.sf.jame.queue.network.SessionHandler;

/**
 * @author Andrea Medeghini
 */
public class DummyJXTANetworkService {
	private static final Logger logger = Logger.getLogger(DummyJXTANetworkService.class.getName());

	/**
	 * @param args
	 */
	public static void main(final String args[]) {
		final File tmpDir = new File("workdir/tmp");
		final JXTADiscoveryService service = new JXTADiscoveryService(new JXTANetworkService(tmpDir, "http://jame.sf.net", "JXTASpool", "Andrea Medeghini", "1.0", new TestProcessor()), new TestProcessor());
		service.start();
		try {
			Thread.sleep(10000);
			while (true) {
				final List<ServiceEndpoint> endpoints = service.getEndpoints();
				for (final ServiceEndpoint endpoint : endpoints) {
					final ServiceSession[] sessions = new ServiceSession[5];
					for (int i = 0; i < 5; i++) {
						try {
							sessions[i] = endpoint.createSession(new TestListener());
							final RequestMessage request = new RequestMessage();
							request.setRequestId(RequestIDFactory.newRequestId());
							request.setRequestType(0);
							request.setUserData(new Integer(i));
							sessions[i].sendMessage(request);
						}
						catch (final Exception e) {
							e.printStackTrace();
						}
						Thread.sleep(1000);
					}
					Thread.sleep(30000);
					for (int i = 0; i < 5; i++) {
						try {
							if (sessions[i] != null) {
								sessions[i].dispose();
							}
						}
						catch (final Exception e) {
							e.printStackTrace();
						}
					}
					Thread.sleep(180000);
				}
			}
		}
		catch (final InterruptedException e) {
		}
		service.stop();
	}

	private static class TestProcessor implements ServiceProcessor {
		public void start() {
		}

		public void stop() {
		}

		public SessionHandler createSessionHandler() {
			return null;
		}
	}

	private static class TestListener implements ServiceListener {
		public void onMessage(final ServiceMessage message) throws ServiceException {
			switch (message.getMessageType()) {
				case ServiceMessage.MESSAGE_TYPE_REQUEST: {
					break;
				}
				case ServiceMessage.MESSAGE_TYPE_RESPONSE: {
					logger.fine(message.toString());
					break;
				}
				case ServiceMessage.MESSAGE_TYPE_EVENT: {
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
