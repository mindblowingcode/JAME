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
package net.sf.jame.test.contextfree;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.ContextFreeConfigBuilder;
import net.sf.jame.contextfree.ContextFreeConfigNodeBuilder;
import net.sf.jame.contextfree.ContextFreeRuntime;
import net.sf.jame.contextfree.extensions.image.ContextFreeImageConfig;
import net.sf.jame.contextfree.renderer.ContextFreeRenderer;
import net.sf.jame.contextfree.renderer.DefaultContextFreeRenderer;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.tree.RootNode;
import net.sf.jame.core.tree.Tree;
import net.sf.jame.core.util.IntegerVector2D;
import net.sf.jame.core.util.Surface;
import net.sf.jame.core.util.Tile;

import org.junit.Test;

public class TestContextFree {
	@Test
	public void render() {
		try {
			Surface surface = new Surface(200, 200);
			ContextFreeConfigBuilder builder = new ContextFreeConfigBuilder(new ContextFreeImageConfig());
			ContextFreeConfig config = builder.createDefaultConfig();
			ContextFreeRuntime runtime = new ContextFreeRuntime(config);
			RootNode rootNode = new RootNode("contextfree");
			ContextFreeConfigNodeBuilder nodeBuilder = new ContextFreeConfigNodeBuilder(config);
			nodeBuilder.createNodes(rootNode);
			Tree tree = new Tree(rootNode);
			System.out.println(tree);
			ContextFreeRenderer renderer = new DefaultContextFreeRenderer(Thread.MIN_PRIORITY);
			renderer.setTile(new Tile(new IntegerVector2D(200, 200), new IntegerVector2D(200, 200), new IntegerVector2D(0, 0), new IntegerVector2D(0, 0)));
			renderer.setRuntime(runtime.getCFDG());
			renderer.start();
			try {
				renderer.startRenderer();
				renderer.joinRenderer();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			renderer.drawImage(surface.getGraphics2D());
			try {
				ImageIO.write(surface.getImage(), "png", new File("testcf.png"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			renderer.stop();
			renderer.dispose();
			rootNode.dispose();
			runtime.dispose();
			config.dispose();
		}
		catch (ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (ExtensionException e) {
			e.printStackTrace();
		}
	}
}
