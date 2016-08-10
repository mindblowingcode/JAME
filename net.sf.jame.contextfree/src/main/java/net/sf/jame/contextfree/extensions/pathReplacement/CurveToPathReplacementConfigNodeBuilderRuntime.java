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
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.core.common.FloatElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class CurveToPathReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((CurveToPathReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<CurveToPathReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final CurveToPathReplacementConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new XElementNode(getConfig()));
			parentNode.appendChildNode(new YElementNode(getConfig()));
			parentNode.appendChildNode(new X1ElementNode(getConfig()));
			parentNode.appendChildNode(new Y1ElementNode(getConfig()));
			parentNode.appendChildNode(new X2ElementNode(getConfig()));
			parentNode.appendChildNode(new Y2ElementNode(getConfig()));
		}

		private class XElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public XElementNode(final CurveToPathReplacementConfig config) {
				super(config.getExtensionId() + ".x", config.getXElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.XElement"));
			}
		}
		private class YElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public YElementNode(final CurveToPathReplacementConfig config) {
				super(config.getExtensionId() + ".y", config.getYElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.YElement"));
			}
		}
		private class X1ElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public X1ElementNode(final CurveToPathReplacementConfig config) {
				super(config.getExtensionId() + ".x1", config.getX1Element());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.X1Element"));
			}
		}
		private class Y1ElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public Y1ElementNode(final CurveToPathReplacementConfig config) {
				super(config.getExtensionId() + ".y1", config.getY1Element());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.Y1Element"));
			}
		}
		private class X2ElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public X2ElementNode(final CurveToPathReplacementConfig config) {
				super(config.getExtensionId() + ".x2", config.getX2Element());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.X2Element"));
			}
		}
		private class Y2ElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public Y2ElementNode(final CurveToPathReplacementConfig config) {
				super(config.getExtensionId() + ".y2", config.getY2Element());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.Y2Element"));
			}
		}
	}
}
