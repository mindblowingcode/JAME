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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import net.sf.jame.core.launcher.LauncherContext;
import net.sf.jame.core.launcher.LauncherContextListener;
import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.queue.DefaultConnectionFactory;

/**
 * @author Andrea Medeghini
 */
public class ProxyLauncherThread extends Thread implements LauncherContextListener {
	private JXTAProxyService service;
	private final LauncherContext context;

	/**
	 * @param context
	 */
	public ProxyLauncherThread(final LauncherContext context) {
		super("Launcher");
		setDaemon(true);
		this.context = context;
		context.setContextListener(this);
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			final Properties properties = new Properties();
			File workspace = null;
			try {
				properties.load(new FileInputStream(System.getProperty("user.home") + "/JXTAProxy.properties"));
				if ((properties.get("workspace") == null) || (System.getProperty("jame.workspace") != null)) {
					workspace = new File(System.getProperty("jame.workspace", System.getProperty("user.home") + "/JXTAProxy-workspace").replace("${user.home}", System.getProperty("user.home")));
					properties.put("workspace", workspace.getAbsolutePath());
					try {
						properties.store(new FileOutputStream(System.getProperty("user.home") + "/JXTAProxy.properties"), null);
					}
					catch (final Exception e) {
						e.printStackTrace();
					}
				}
				else {
					workspace = new File((String) properties.get("workspace"));
				}
			}
			catch (final Exception x) {
				x.printStackTrace();
				workspace = new File(System.getProperty("jame.workspace", System.getProperty("user.home") + "/JXTAProxy-workspace").replace("${user.home}", System.getProperty("user.home")));
				properties.put("workspace", workspace.getAbsolutePath());
				try {
					properties.store(new FileOutputStream(System.getProperty("user.home") + "/JXTAProxy.properties"), null);
				}
				catch (final Exception e) {
					e.printStackTrace();
				}
			}
			ConnectionFactory connectionFactory = null;
			while (workspace != null) {
				connectionFactory = new DefaultConnectionFactory(workspace);
				final Connection connection = null;
				try {
					connectionFactory.createConnection();
					break;
				}
				catch (final Exception e) {
					e.printStackTrace();
					workspace = null;
				}
				finally {
					if (connection != null) {
						try {
							connection.close();
						}
						catch (final SQLException e) {
						}
					}
				}
			}
			if (workspace != null) {
				final File tmpDir = new File(workspace, "/JXTAProxy");
				service = new JXTAProxyService(tmpDir);
				service.start();
			}
		}
		catch (final Exception e) {
			e.printStackTrace();
			context.exit();
		}
	}

	/**
	 * @see net.sf.jame.launcher.LauncherContextListener#started()
	 */
	public void started() {
	}

	/**
	 * @see net.sf.jame.launcher.LauncherContextListener#stopped()
	 */
	public void stopped() {
		service.stop();
	}
}
