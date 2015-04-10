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
package net.sf.jame.queue.jxta;

import net.sf.jame.core.Application;
import net.sf.jame.core.ApplicationContext;
import net.sf.jame.core.launcher.Launcher;
import net.sf.jame.core.launcher.LauncherContext;
import net.sf.jame.core.launcher.LauncherContextListener;
import net.sf.jame.core.launcher.LauncherThreadFactory;

/**
 * Application implementation.
 * 
 * @author Andrea Medeghini
 */
public class SpoolApplication implements Application {
	private final Launcher<DefaultLauncherContext> launcher = new Launcher<DefaultLauncherContext>(new DefaultLauncherContext(), new DefaultLauncherThreadFactory());
	private LauncherContextListener listener;

	public Object start(final ApplicationContext context) throws Exception {
//		Properties log4jProperties = new Properties();
//		log4jProperties.put("log4j.rootLogger", "INFO, console");
//		log4jProperties.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
//		log4jProperties.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
//		log4jProperties.put("log4j.appender.console.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p %c - %m%n");
//		log4jProperties.put("log4j.logger.net.sf.jame", "INFO");
//		log4jProperties.put("log4j.logger.org.apache.derby", "INFO");
//		PropertyConfigurator.configure(log4jProperties);
		boolean restart = false;
		try {
			launcher.init();
			launcher.start();
			if (listener != null) {
				listener.started();
			}
			context.applicationRunning();
			restart = launcher.dispatch();
			if (listener != null) {
				listener.stopped();
			}
			launcher.dispose();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
		if (restart) {
			return Application.EXIT_RESTART;
		}
		return Application.EXIT_OK;
	}

	/**
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		launcher.stop();
	}

	private class DefaultLauncherContext implements LauncherContext {
		/**
		 * @see net.sf.jame.launcher.LauncherContext#exit()
		 */
		public void exit() {
			launcher.stop();
		}

		/**
		 * @see net.sf.jame.launcher.LauncherContext#restart()
		 */
		public void restart() {
			launcher.restart();
		}

		/**
		 * @see net.sf.jame.launcher.LauncherContext#setContextListener(net.sf.jame.launcher.LauncherContextListener)
		 */
		public void setContextListener(final LauncherContextListener listener) {
			SpoolApplication.this.listener = listener;
		}
	}

	private class DefaultLauncherThreadFactory implements LauncherThreadFactory<DefaultLauncherContext> {
		/**
		 * @see net.sf.jame.networking.jxta.spool.LauncherThreadFactory#createThread(net.sf.jame.launcher.LauncherContext)
		 */
		public Thread createThread(final DefaultLauncherContext context) {
			return new SpoolLauncherThread(context);
		}
	}
}
