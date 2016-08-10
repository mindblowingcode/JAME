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
package net.sf.jame.twister.extensions.effect;

import net.sf.jame.core.common.ComplexElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;
import net.sf.jame.twister.common.PercentageElementNode;
import net.sf.jame.twister.extensions.TwisterExtensionResources;

/**
 * @author Andrea Medeghini
 */
public class SpotConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((SpotConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<SpotConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final SpotConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new IntensityNode(getConfig()));
			parentNode.appendChildNode(new CenterNode(getConfig()));
		}

		private class IntensityNode extends PercentageElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.IntensityElement");

			/**
			 * @param config
			 */
			public IntensityNode(final SpotConfig config) {
				super(config.getExtensionId() + ".intensity", config.getIntensityElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class CenterNode extends ComplexElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.CenterElement");

			/**
			 * @param config
			 */
			public CenterNode(final SpotConfig config) {
				super(config.getExtensionId() + ".center", config.getCenterElement());
				setNodeLabel(NODE_LABEL);
			}
		}
	}
}
