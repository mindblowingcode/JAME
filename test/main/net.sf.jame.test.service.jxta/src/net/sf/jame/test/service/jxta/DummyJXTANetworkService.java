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
import java.util.List;

import net.sf.jame.service.jxta.JXTADiscoveryService;
import net.sf.jame.service.jxta.JXTANetworkService;
import net.sf.jame.service.network.RequestIDFactory;
import net.sf.jame.service.network.RequestMessage;
import net.sf.jame.service.network.ServiceEndpoint;
import net.sf.jame.service.network.ServiceException;
import net.sf.jame.service.network.ServiceListener;
import net.sf.jame.service.network.ServiceMessage;
import net.sf.jame.service.network.ServiceProcessor;
import net.sf.jame.service.network.ServiceSession;
import net.sf.jame.service.network.SessionHandler;

import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class DummyJXTANetworkService {
	private static final Logger logger = Logger.getLogger(DummyJXTANetworkService.class);

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
					logger.debug(message);
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
