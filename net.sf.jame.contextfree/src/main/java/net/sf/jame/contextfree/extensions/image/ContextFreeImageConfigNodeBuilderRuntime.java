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
package net.sf.jame.contextfree.extensions.image;

import net.sf.jame.contextfree.ContextFreeConfigNodeBuilder;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeImageConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ContextFreetImageNodeBuilder((ContextFreeImageConfig) config);
	}

	private class ContextFreetImageNodeBuilder implements NodeBuilder {
		private final ContextFreeImageConfig config;

		/**
		 * @param config
		 */
		public ContextFreetImageNodeBuilder(final ContextFreeImageConfig config) {
			this.config = config;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeBuilder#createNodes(Node)
		 */
		public void createNodes(final Node parentNode) {
			final ContextFreeConfigNodeBuilder builder = new ContextFreeConfigNodeBuilder(config.getContextFreeConfig());
			builder.createNodes(parentNode);
		}
	}
}
