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
package net.sf.jame.core.scripting;

import net.sf.jame.core.tree.Node;
import net.sf.jame.core.util.RenderContext;

/**
 * @author Andrea Medeghini
 */
public class DefaultJSTree implements JSTree {
	private final RenderContext renderContext;
	private final JSNode jsRootNode;
	private final Node rootNode;

	/**
	 * @param renderContext
	 * @param jsContext
	 * @param rootNode
	 */
	public DefaultJSTree(final RenderContext renderContext, final Node rootNode) {
		this.renderContext = renderContext;
		this.rootNode = rootNode;
		jsRootNode = new DefaultJSNode(rootNode);
	}

	/**
	 * @see net.sf.jame.core.scripting.JSTree#accept()
	 */
	public void accept() {
		try {
			renderContext.acquire();
			rootNode.getContext().updateTimestamp();
			rootNode.accept();
			renderContext.release();
			renderContext.refresh();
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * @see net.sf.jame.core.scripting.JSTree#cancel()
	 */
	public void cancel() {
		rootNode.cancel();
	}

	/**
	 * @see net.sf.jame.core.scripting.JSTree#refresh()
	 */
	public void refresh() {
		renderContext.refresh();
	}

	/**
	 * @see net.sf.jame.core.scripting.JSTree#redraw()
	 */
	public void redraw() {
		try {
			renderContext.acquire();
			renderContext.stopRenderers();
			renderContext.startRenderers();
			renderContext.release();
			renderContext.refresh();
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * @see net.sf.jame.core.scripting.JSTree#getRootNode()
	 */
	public JSNode getRootNode() {
		return jsRootNode;
	}

	/**
	 * @see net.sf.jame.core.scripting.JSTree#dump()
	 */
	public String dump() {
		return rootNode.dump();
	}
}
