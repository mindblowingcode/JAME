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
package net.sf.jame.test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;

import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.swing.util.GUIUtil;
import net.sf.jame.core.util.ConnectionFactory;
import net.sf.jame.queue.DefaultConnectionFactory;
import net.sf.jame.queue.LibraryService;
import net.sf.jame.queue.RenderService;
import net.sf.jame.queue.Session;
import net.sf.jame.twister.TwisterClip;
import net.sf.jame.twister.swing.RenderClipTableModel;
import net.sf.jame.twister.swing.RenderJobTableModel;
import net.sf.jame.twister.swing.RenderProfileTableModel;
import net.sf.jame.twister.swing.ServiceContext;
import net.sf.jame.twister.swing.ServiceFrame;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ServiceFrameTest {
	@Test
	public void testConfigPanel() {
		try {
			final ServiceContext context = new TestServiceContext();
			final File workspace = new File("workdir");
			synchronized (context) {
				GUIUtil.executeTask(new Runnable() {
						public void run() {
							try {
								final ConnectionFactory factory = new DefaultConnectionFactory(workspace);
								final Session session = new Session(factory);
								ExtensionReference extensionReference = new ExtensionReference("service.spool.local", "Local Spool");
								RenderService service = new RenderService(new LibraryService(session, workspace), extensionReference);
								RenderClipTableModel renderClipTableModel = new RenderClipTableModel(service);
								RenderProfileTableModel renderProfileTableModel = new RenderProfileTableModel(service);
								RenderJobTableModel renderJobTableModel = new RenderJobTableModel(service);
								ServiceFrame frame = new ServiceFrame(context, service, renderClipTableModel, renderProfileTableModel, renderJobTableModel);
								frame.addWindowListener(new WindowAdapter() {
									@Override
									public void windowClosing(final WindowEvent e) {
										context.exit();
										super.windowClosing(e);
									}
								});
								frame.setVisible(true);
							}
							catch (Exception e) {
								Assert.fail();
								context.exit();
							}
						}
				}, true);
				context.wait();
			}
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private class TestServiceContext implements ServiceContext {
		public void addFrame(final JFrame frame) {
		}

		public void removeFrame(final JFrame frame) {
		}

		public int getFrameCount() {
			return 0;
		}

		public void openClip(final TwisterClip clip) {
		}

		public void exit() {
			synchronized (this) {
				notify();
			}
			System.exit(0);
		}

		public void restart() {
			exit();
		}
	}
}
