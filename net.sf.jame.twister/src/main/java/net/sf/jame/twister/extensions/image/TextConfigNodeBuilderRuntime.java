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
package net.sf.jame.twister.extensions.image;

import net.sf.jame.core.common.ColorElementNode;
import net.sf.jame.core.common.DoubleElementNode;
import net.sf.jame.core.common.FontElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;
import net.sf.jame.twister.extensions.TwisterExtensionResources;

/**
 * @author Andrea Medeghini
 */
public class TextConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((TextConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<TextConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final TextConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new ColorNode(getConfig()));
			parentNode.appendChildNode(new FontNode(getConfig()));
			parentNode.appendChildNode(new SizeNode(getConfig()));
			parentNode.appendChildNode(new TextNode(getConfig()));
			parentNode.appendChildNode(new LeftNode(getConfig()));
			parentNode.appendChildNode(new TopNode(getConfig()));
			parentNode.appendChildNode(new RotationNode(getConfig()));
		}

		private class ColorNode extends ColorElementNode {
			/**
			 * @param config
			 */
			public ColorNode(final TextConfig config) {
				super(config.getExtensionId() + ".color", config.getColorElement());
			}
		}

		private class SizeNode extends DoubleElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.SizeElement");

			/**
			 * @param config
			 */
			public SizeNode(final TextConfig config) {
				super(config.getExtensionId() + ".size", config.getSizeElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class FontNode extends FontElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.FontElement");

			/**
			 * @param config
			 */
			public FontNode(final TextConfig config) {
				super(config.getExtensionId() + ".font", config.getFontElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class TextNode extends StringElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.TextElement");

			/**
			 * @param config
			 */
			public TextNode(final TextConfig config) {
				super(config.getExtensionId() + ".text", config.getTextElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class LeftNode extends DoubleElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.LeftElement");

			/**
			 * @param config
			 */
			public LeftNode(final TextConfig config) {
				super(config.getExtensionId() + ".left", config.getLeftElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class TopNode extends DoubleElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.TopElement");

			/**
			 * @param config
			 */
			public TopNode(final TextConfig config) {
				super(config.getExtensionId() + ".top", config.getTopElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class RotationNode extends DoubleElementNode {
			private final String NODE_LABEL = TwisterExtensionResources.getInstance().getString("node.label.RotationElement");

			/**
			 * @param config
			 */
			public RotationNode(final TextConfig config) {
				super(config.getExtensionId() + ".rotation", config.getRotationElement());
				setNodeLabel(NODE_LABEL);
			}
		}
	}
}
