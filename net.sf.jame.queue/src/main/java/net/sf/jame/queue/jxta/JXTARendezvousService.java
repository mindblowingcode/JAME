/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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

import java.io.File;
import java.net.URI;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;

import net.jxta.id.IDFactory;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.platform.NetworkConfigurator;
import net.jxta.platform.NetworkManager;
import net.sf.jame.core.util.DefaultThreadFactory;

/**
 * @author Andrea Medeghini
 */
public class JXTARendezvousService {
	private static final Logger logger = Logger.getLogger(JXTARendezvousService.class.getName());
	private static final ThreadFactory factory = new DefaultThreadFactory("Thread", true, Thread.MIN_PRIORITY);
	private NetworkManager manager;
	private final File workdir;
	private Thread thread;
	private boolean running;

	/**
	 * @param workdir
	 * @param serviceURI
	 * @param serviceName
	 * @param serviceAuthor
	 * @param serviceVersion
	 * @param processor
	 */
	public JXTARendezvousService(final File workdir) {
		this.workdir = workdir;
	}

	/**
	 * 
	 */
	public void start() {
		if (thread == null) {
			thread = factory.newThread(new NetworkHandler());
			thread.setName("RendezvousService Thread");
			running = true;
			thread.start();
		}
	}

	/**
	 * 
	 */
	public void stop() {
		if (thread != null) {
			running = false;
			thread.interrupt();
			try {
				thread.join();
			}
			catch (final InterruptedException e) {
			}
			thread = null;
		}
	}

	private class NetworkHandler implements Runnable {
		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			boolean interrupted = false;
			try {
				final File cache = new File(workdir, "jxta");
				manager = new NetworkManager(NetworkManager.ConfigMode.RENDEZVOUS_RELAY, "JXTARendezvous", cache.toURI());
				configureNetwork(manager);
				manager.startNetwork();
				if (!manager.waitForRendezvousConnection(20000)) {
					logger.warning("Failed to connect to Rendezvous service");
				}
				while (running) {
					Thread.sleep(60000);
				}
			}
			catch (final InterruptedException e) {
				interrupted = true;
			}
			catch (final Exception e) {
				e.printStackTrace();
			}
			finally {
				if (manager != null) {
					manager.stopNetwork();
				}
			}
			if (interrupted) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private static void configureNetwork(final NetworkManager manager) throws Exception {
		NetworkConfigurator config = manager.getConfigurator();
		if (config.exists()) {
			config.load();
		}
		else {
			config.setName("JAME");
			config.setInfrastructureID(createInfrastructurePeerGroupID());
			config.setInfrastructureDescriptionStr("JAME");
			config.setInfrastructureName("JAME-JXTA");
			config.setPeerID(createPeerID(manager.getInfrastructureID()));
			config.setUseMulticast(false);
			config.setTcpIncoming(true);
			config.setTcpOutgoing(true);
			config.setTcpEnabled(true);
			config.setTcpStartPort(9701);
			config.setTcpEndPort(9704);
			config.setTcpPort(9702);
			config.setHttpIncoming(true);
			config.setHttpEnabled(true);
			config.setHttpPort(9902);
			config.save();
		}
		if (System.getProperty(JXTANetworkService.JAME_JXTA_RELAY_SEEDS) != null) {
			URI seedingURI = new File(System.getProperty(JXTANetworkService.JAME_JXTA_RELAY_SEEDS)).toURI();
			logger.info("Add relay seeds: " + seedingURI);
			config.clearRelaySeedingURIs();
			config.addRelaySeedingURI(seedingURI);
		}
		if (System.getProperty(JXTANetworkService.JAME_JXTA_RENDEZVOUS_SEEDS) != null) {
			URI seedingURI = new File(System.getProperty(JXTANetworkService.JAME_JXTA_RENDEZVOUS_SEEDS)).toURI();
			logger.info("Add rendezvous seeds: " + seedingURI);
			config.clearRendezvousSeedingURIs();
			config.addRdvSeedingURI(seedingURI);
		}
		if (Boolean.getBoolean(JXTANetworkService.JAME_JXTA_MULTICAST)) {
			logger.info("Enable multicast");
			config.setUseMulticast(true);
		}
	}

	private static final PeerGroupID createInfrastructurePeerGroupID() {
		return IDFactory.newPeerGroupID("JAME-PeerGroup".getBytes());
	}

	private static final PeerID createPeerID(final PeerGroupID peerGroupId) {
		return IDFactory.newPeerID(peerGroupId, String.valueOf(System.currentTimeMillis()).getBytes());
	}
}
