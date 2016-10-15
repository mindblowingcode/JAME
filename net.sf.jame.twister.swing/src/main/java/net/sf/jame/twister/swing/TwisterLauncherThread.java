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
package net.sf.jame.twister.swing;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;

import net.sf.jame.core.config.DefaultConfigContext;
import net.sf.jame.core.swing.util.GUIUtil;
import net.sf.jame.core.xml.XML;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.TwisterConfigBuilder;
import net.sf.jame.twister.TwisterConfigXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class TwisterLauncherThread extends Thread {
	private final TwisterContext context;

	/**
	 * @param context
	 */
	public TwisterLauncherThread(final TwisterContext context) {
		super("Launcher");
		setDaemon(true);
		this.context = context;
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		GUIUtil.executeTask(new Runnable() {
			public void run() {
				try {
					if (Boolean.getBoolean("jame.enableConfigurator")) {
						if (System.getProperty("jame.configFile") != null) {
							try {
								final InputStream is = new FileInputStream(new File(System.getProperty("config")));
								final Document doc = XML.loadDocument(is, "twister-config.xml");
								is.close();
								final TwisterConfigXMLImporter importer = new TwisterConfigXMLImporter();
								final TwisterConfig config = importer.importFromElement(doc.getDocumentElement());
								config.setContext(new DefaultConfigContext());
								if (!TwisterHelper.openTwisterFrame(context, config)) {
									context.exit();
								}
							}
							catch (Exception x) {
								x.printStackTrace();
							}
						}
						else {
							final TwisterConfigBuilder configBuilder = new TwisterConfigBuilder();
							final TwisterConfig config = configBuilder.createDefaultConfig();
							config.setContext(new DefaultConfigContext());
							if (!TwisterHelper.openConfiguratorFrame(context, config)) {
								context.exit();
							}
						}
					} else {
						if (!TwisterHelper.openTwisterFrame(context, null)) {
							context.exit();
						}
					}
				}
				catch (final Exception e) {
					JOptionPane.showMessageDialog(new Frame(), TwisterSwingResources.getInstance().getString("error.failure"), TwisterSwingResources.getInstance().getString("label.error"), JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
					context.exit();
				}
			}
		}, true);
	}
}
