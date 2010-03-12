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
package net.sf.jame.launcher;

import java.lang.management.ManagementFactory;


import org.apache.log4j.Logger;

/**
 * @author Andrea Medeghini
 */
public class Launcher<T extends LauncherContext> {
	private static final Logger logger = Logger.getLogger(Launcher.class);
	private final LauncherThreadFactory<T> factory;
	private T context;
	private boolean running;
	private boolean restart;

	/**
	 * @param context
	 * @param factory
	 */
	public Launcher(final T context, final LauncherThreadFactory<T> factory) {
		this.context = context;
		this.factory = factory;
	}

	/**
	 * @throws Exception
	 */
	public void init() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	public void start() throws Exception {
		running = true;
		restart = false;
		final String version = ManagementFactory.getRuntimeMXBean().getVmVersion();
		if ((version.length() > 2) && ("1.5".compareTo(version.substring(0, 3)) > 0)) {
			logger.error("Could not launch the application because it requires Java 1.5 or later!");
			stop();
		}
		else {
			final Thread thread = factory.createThread(context);
			thread.start();
		}
	}

	/**
	 * 
	 */
	public void stop() {
		synchronized (this) {
			running = false;
			notify();
		}
	}

	/**
	 * @return
	 */
	public boolean dispatch() {
		try {
			while (running) {
				synchronized (this) {
					wait();
				}
			}
		}
		catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return restart;
	}

	/**
	 * 
	 */
	public void dispose() {
		context = null;
	}

	/**
	 * 
	 */
	public void restart() {
		restart = true;
		stop();
	}
}
