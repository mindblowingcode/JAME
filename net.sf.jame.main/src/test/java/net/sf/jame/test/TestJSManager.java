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

import java.io.File;

import net.sf.jame.core.DefaultTree;
import net.sf.jame.core.config.DefaultConfigContext;
import net.sf.jame.core.scripting.DefaultJSContext;
import net.sf.jame.core.scripting.JSManager;
import net.sf.jame.core.tree.DefaultNodeSession;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.RenderContext;
import net.sf.jame.core.util.RenderContextListener;
import net.sf.jame.twister.TwisterConfig;
import net.sf.jame.twister.TwisterConfigBuilder;
import net.sf.jame.twister.TwisterConfigNode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andrea Medeghini
 */
public class TestJSManager {
	@Test
	public void test() {
		try {
			DefaultNodeSession session = new DefaultNodeSession("test");
			TwisterConfigBuilder builder = new TwisterConfigBuilder();
			TwisterConfig config = builder.createDefaultConfig();
			TwisterConfigNode configNode = new TwisterConfigNode(config);
			DefaultTree twisterTree = new DefaultTree();
			twisterTree.getRootNode().appendChildNode(configNode);
			twisterTree.getRootNode().setSession(session);
			twisterTree.getRootNode().setContext(new DefaultConfigContext());
			JSManager.execute(new TestRenderContext(), new TestJSContext(), twisterTree.getRootNode(), new File("."), new File("test.js"));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	private class TestJSContext extends DefaultJSContext {
		/**
		 * @see net.sf.jame.core.scripting.JSContext#loadDefaultConfig()
		 */
		public void loadDefaultConfig() {
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
}
