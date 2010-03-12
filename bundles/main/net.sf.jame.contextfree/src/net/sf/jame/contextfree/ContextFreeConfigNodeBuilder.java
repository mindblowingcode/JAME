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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.CFDGConfigElementNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.twister.common.SpeedElementNode;
import net.sf.jame.twister.common.ViewElementNode;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigNodeBuilder {
	private final ContextFreeConfig config;

	/**
	 * Constructs a new tree builder.
	 * 
	 * @param config
	 *        the config.
	 */
	public ContextFreeConfigNodeBuilder(final ContextFreeConfig config) {
		this.config = config;
	}

	/**
	 * Creates the nodes.
	 * 
	 * @param parentNode
	 */
	public void createNodes(final Node parentNode) {
		parentNode.appendChildNode(new ViewNode(config));
		parentNode.appendChildNode(new SpeedNode(config));
		parentNode.appendChildNode(new CFDGConfigElementNode(config.getContextFreeFractal()));
	}

	private static class SpeedNode extends SpeedElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.SpeedElement");

		/**
		 * @param config
		 */
		public SpeedNode(final ContextFreeConfig config) {
			super("attribute.speed", config.getSpeedElement());
			setNodeLabel(SpeedNode.NODE_LABEL);
		}
	}

	private static class ViewNode extends ViewElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.ViewElement");

		/**
		 * @param config
		 */
		public ViewNode(final ContextFreeConfig config) {
			super("attribute.view", config.getViewElement());
			setNodeLabel(ViewNode.NODE_LABEL);
		}
	}
}
