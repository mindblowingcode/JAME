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
package net.sf.jame.test.twister;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.sf.jame.core.DefaultTree;
import net.sf.jame.core.config.DefaultConfigContext;
import net.sf.jame.core.scripting.DefaultJSContext;
import net.sf.jame.core.scripting.JSException;
import net.sf.jame.core.scripting.JSManager;
import net.sf.jame.core.tree.NodeAction;
import net.sf.jame.core.tree.NodeSession;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.core.util.RenderContextListener;
import net.sf.jame.launcher.Launcher;
import net.sf.jame.launcher.LauncherContextListener;
import net.sf.jame.launcher.LauncherThreadFactory;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.TwisterConfigBuilder;
import net.sf.jame.twister.TwisterConfigNodeBuilder;
import net.sf.jame.twister.swing.ConfigFrame;
import net.sf.jame.twister.swing.TwisterConfigContext;
import net.sf.jame.twister.swing.TwisterContext;

import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class ConfigFrameTest {
	private final Launcher<TwisterContext> launcher = new Launcher<TwisterContext>(new TestTwisterContext(), new TestThreadFactory());
	private DefaultTree twisterTree;
	private TwisterConfig config;

	@Test
	public void testConfigPanel() {
		try {
			launcher.init();
			launcher.start();
			launcher.dispatch();
			launcher.dispose();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private class TestThreadFactory implements LauncherThreadFactory<TwisterContext> {
		public Thread createThread(final TwisterContext context) {
			final Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
						}
						catch (Exception x) {
							x.printStackTrace();
						}
						final TestRenderContext renderContext = new TestRenderContext();
						final NodeSession session = new TestNodeSesion();
						twisterTree = new DefaultTree();
						TwisterConfigBuilder builder = new TwisterConfigBuilder();
						config = builder.createDefaultConfig();
						config.setContext(new DefaultConfigContext());
						final TwisterConfigNodeBuilder nodeBuilder = new TwisterConfigNodeBuilder(config);
						twisterTree.getRootNode().setContext(config.getContext());
						twisterTree.getRootNode().setSession(session);
						nodeBuilder.createNodes(twisterTree.getRootNode());
						final ConfigFrame frame = new ConfigFrame(new TestTwisterConfigContext(renderContext), config, renderContext, session);
						frame.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(final WindowEvent e) {
								context.exit();
							}
						});
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								frame.setVisible(true);
							}
						});
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								frame.setup();
							}
						});
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return thread;
		}
	}

	private class TestTwisterConfigContext implements TwisterConfigContext {
		private RenderContext renderContext;

		public TestTwisterConfigContext(TestRenderContext renderContext) {
			this.renderContext = renderContext;
		}

		/**
		 * @see net.sf.jame.twister.swing.TwisterConfigContext#openAdvancedConfigWindow()
		 */
		public void openAdvancedConfigWindow() {
		}

		/**
		 * @see net.sf.jame.twister.swing.TwisterConfigContext#executeScript(java.io.File)
		 */
		public void executeScript(File scriptFile) throws JSException {
			JSManager.execute(renderContext, new TestJSContext(), twisterTree.getRootNode(), scriptFile.getParentFile(), scriptFile);
		}
	}

	private class TestJSContext extends DefaultJSContext {
		/**
		 * @see net.sf.jame.core.scripting.JSContext#loadDefaultConfig()
		 */
		public void loadDefaultConfig() {
		}
	}

	private class TestTwisterContext implements TwisterContext {
		/**
		 * @see net.sf.jame.twister.swing.TwisterContext#addFrame(javax.swing.JFrame)
		 */
		public void addFrame(final JFrame frame) {
		}

		/**
		 * @see net.sf.jame.twister.swing.TwisterContext#exit()
		 */
		public void exit() {
			launcher.stop();
		}

		/**
		 * @see net.sf.jame.twister.swing.TwisterContext#getFrameCount()
		 */
		public int getFrameCount() {
			return 0;
		}

		/**
		 * @see net.sf.jame.twister.swing.TwisterContext#removeFrame(javax.swing.JFrame)
		 */
		public void removeFrame(final JFrame frame) {
		}

		/**
		 * @see net.sf.jame.twister.swing.TwisterContext#restart()
		 */
		public void restart() {
			launcher.stop();
		}

		/**
		 * @see net.sf.jame.launcher.LauncherContext#setContextListener(net.sf.jame.launcher.LauncherContextListener)
		 */
		public void setContextListener(final LauncherContextListener listener) {
		}
	}

	private class TestRenderContext implements RenderContext {
		/**
		 * @see net.sf.jame.core.util.RenderContext#startRenderers()
		 */
		public void startRenderers() {
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#stopRenderers()
		 */
		public void stopRenderers() {
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#getImageSize()
		 */
		public IntegerVector2D getImageSize() {
			return new IntegerVector2D(100, 100);
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#refresh()
		 */
		public void refresh() {
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#acquire()
		 */
		public void acquire() throws InterruptedException {
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#release()
		 */
		public void release() {
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#addRenderContextListener(net.sf.jame.core.util.RenderContextListener)
		 */
		public void addRenderContextListener(RenderContextListener listener) {
		}

		/**
		 * @see net.sf.jame.core.util.RenderContext#removeRenderContextListener(net.sf.jame.core.util.RenderContextListener)
		 */
		public void removeRenderContextListener(RenderContextListener listener) {
		}
	}

	private class TestNodeSesion implements NodeSession {
		/**
		 * @see net.sf.jame.core.tree.NodeSession#appendAction(net.sf.jame.core.tree.NodeAction)
		 */
		public void appendAction(final NodeAction action) {
			System.out.println(action);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeSession#getActions()
		 */
		public List<NodeAction> getActions() {
			return null;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeSession#getSessionName()
		 */
		public String getSessionName() {
			return "Test";
		}

		/**
		 * @see net.sf.jame.core.tree.NodeSession#getTimestamp()
		 */
		public long getTimestamp() {
			return 0;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeSession#isAcceptImmediatly()
		 */
		public boolean isAcceptImmediatly() {
			return true;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeSession#setAcceptImmediatly(boolean)
		 */
		public void setAcceptImmediatly(final boolean isApplyImmediatly) {
		}

		/**
		 * @see net.sf.jame.core.tree.NodeSession#setTimestamp(long)
		 */
		public void setTimestamp(final long timestamp) {
			System.out.println("Timestamp = " + timestamp);
		}
	}
}
