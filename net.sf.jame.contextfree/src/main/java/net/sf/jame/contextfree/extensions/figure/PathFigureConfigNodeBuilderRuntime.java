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
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;
import net.sf.jame.core.util.ConfigElementListNodeValue;

/**
 * @author Andrea Medeghini
 */
public class PathFigureConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((PathFigureConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<PathFigureConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final PathFigureConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new NameElementNode(getConfig()));
			parentNode.appendChildNode(new PathReplacementListElementNode(getConfig()));
		}

		private class NameElementNode extends StringElementNode {
			/**
			 * @param config
			 */
			public NameElementNode(final PathFigureConfig config) {
				super(config.getExtensionId() + ".name", config.getNameElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.NameElement"));
			}
		}
		private class PathReplacementListElementNode extends AbstractConfigElementListNode<PathReplacementConfigElement> {
			public static final String NODE_CLASS = "node.class.PathReplacementListElement";
			
			/**
			 * @param config
			 */
			public PathReplacementListElementNode(final PathFigureConfig config) {
				super(config.getExtensionId() + ".pathReplacementList", config.getPathReplacementListElement());
				setNodeClass(PathReplacementListElementNode.NODE_CLASS);
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.PathReplacementListElement"));
			}

			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
			 */
			@Override
			protected AbstractConfigElementNode<PathReplacementConfigElement> createChildNode(final PathReplacementConfigElement value) {
				return new PathReplacementConfigElementNode(value);
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
			 */
			@Override
			public Class<?> getChildValueType() {
				return PathReplacementConfigElementNodeValue.class;
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
			 */
			@Override
			public NodeValue<PathReplacementConfigElement> createNodeValue(final Object value) {
				return new PathReplacementConfigElementNodeValue((PathReplacementConfigElement) value);
			}
	
			private class PathReplacementConfigElementNodeValue extends ConfigElementListNodeValue<PathReplacementConfigElement> {
				private static final long serialVersionUID = 1L;
	
				/**
				 * @param value
				 */
				public PathReplacementConfigElementNodeValue(final PathReplacementConfigElement value) {
					super(value);
				}
			}
		}
	}
}
