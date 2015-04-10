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
package net.sf.jame.twister.swing;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.jame.core.config.DefaultConfigContext;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.log4j.Configurator;
import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.service.DefaultConnectionFactory;
import net.sf.jame.service.LibraryService;
import net.sf.jame.service.RenderService;
import net.sf.jame.service.RenderServiceException;
import net.sf.jame.service.RenderServiceRegistry;
import net.sf.jame.service.Session;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.TwisterConfigBuilder;

/**
 * @author Andrea Medeghini
 */
public class TwisterHelper {
	/**
	 * @param context
	 * @param config
	 * @return
	 */
	public static boolean openTwisterFrame(final TwisterContext context, TwisterConfig config) {
		try {
			JFrame frame = createWindow(context, config);
			if (frame != null) {
				frame.setVisible(true);
				return true;
			}
		}
		catch (final Exception e) {
			JOptionPane.showMessageDialog(new Frame(), TwisterSwingResources.getInstance().getString("error.failure"), TwisterSwingResources.getInstance().getString("label.error"), JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return false;
	}

	private static JFrame createWindow(final TwisterContext context, TwisterConfig config) throws ExtensionException, RenderServiceException {
		final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser.setDialogTitle(TwisterSwingResources.getInstance().getString("label.workspace"));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		final Properties properties = new Properties();
		File workspace = null;
		try {
			properties.load(new FileInputStream(System.getProperty("user.home") + "/JAME.properties"));
			if ((properties.get("workspace") == null) || (System.getProperty("jame.workspace") != null)) {
				workspace = new File(System.getProperty("jame.workspace", System.getProperty("user.home") + "/JAME-workspace").replace("${user.home}", System.getProperty("user.home")));
				properties.put("workspace", workspace.getAbsolutePath());
				try {
					properties.store(new FileOutputStream(System.getProperty("user.home") + "/JAME.properties"), null);
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
			workspace = new File(System.getProperty("jame.workspace", System.getProperty("user.home") + "/JAME-workspace").replace("${user.home}", System.getProperty("user.home")));
			properties.put("workspace", workspace.getAbsolutePath());
			try {
				properties.store(new FileOutputStream(System.getProperty("user.home") + "/JAME.properties"), null);
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
				JOptionPane.showMessageDialog(new Frame(), TwisterSwingResources.getInstance().getString("error.workspace"), TwisterSwingResources.getInstance().getString("label.error"), JOptionPane.WARNING_MESSAGE);
				// fileChooser.setCurrentDirectory(workspace.getParentFile());
				fileChooser.setCurrentDirectory(workspace);
				// fileChooser.setSelectedFile(workspace);
				final int returnVal = fileChooser.showSaveDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					workspace = fileChooser.getSelectedFile().getAbsoluteFile().getAbsoluteFile();
				}
				else {
					workspace = null;
				}
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
			Configurator.configure(workspace);
			final Session session = new Session(connectionFactory);
			final RenderService service = new RenderService(new LibraryService(session, workspace), RenderServiceRegistry.getInstance().getSpoolRegistry().getConfigurableExtension("service.spool.local").getExtensionReference());
			if (config == null) {
				final TwisterConfigBuilder configBuilder = new TwisterConfigBuilder();
				config = configBuilder.createDefaultConfig();
				config.setContext(new DefaultConfigContext());
			}
			final int hcells = Integer.getInteger("jame.hcells", 1);
			final int vcells = Integer.getInteger("jame.vcells", 1);
			return new TwisterFrame(context, service, config, hcells, vcells);
		}
		return null;
	}

	/**
	 * @param context
	 * @param config
	 * @return
	 */
	public static boolean openConfiguratorFrame(TwisterContext context, TwisterConfig config) {
		try {
			JFrame frame = new ConfiguratorFrame(context, config);
			if (frame != null) {
				frame.setVisible(true);
				return true;
			}
		}
		catch (final Exception e) {
			JOptionPane.showMessageDialog(new Frame(), TwisterSwingResources.getInstance().getString("error.failure"), TwisterSwingResources.getInstance().getString("label.error"), JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return false;
	}
}
