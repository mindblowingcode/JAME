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
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.contextfree.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.shapeAdjustment.ShapeAdjustmentConfigElementNode;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElementNode;
import net.sf.jame.core.common.IntegerElementNode;
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
public class MultiShapeReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((MultiShapeReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<MultiShapeReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final MultiShapeReplacementConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new TimesElementNode(getConfig()));
			parentNode.appendChildNode(new ShapeReplacementListElementNode(getConfig()));
			parentNode.appendChildNode(new ShapeAdjustmentListElementNode(getConfig()));
		}

		private class TimesElementNode extends IntegerElementNode {
			/**
			 * @param config
			 */
			public TimesElementNode(final MultiShapeReplacementConfig config) {
				super(config.getExtensionId() + ".times", config.getTimesElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.TimesElement"));
			}
		}
		private class ShapeReplacementListElementNode extends AbstractConfigElementListNode<ShapeReplacementConfigElement> {
			public static final String NODE_CLASS = "node.class.ShapeReplacementListElement";
			
			/**
			 * @param config
			 */
			public ShapeReplacementListElementNode(final MultiShapeReplacementConfig config) {
				super(config.getExtensionId() + ".shapeReplacementList", config.getShapeReplacementListElement());
				setNodeClass(ShapeReplacementListElementNode.NODE_CLASS);
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ShapeReplacementListElement"));
			}

			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
			 */
			@Override
			protected AbstractConfigElementNode<ShapeReplacementConfigElement> createChildNode(final ShapeReplacementConfigElement value) {
				return new ShapeReplacementConfigElementNode(value);
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
			 */
			@Override
			public Class<?> getChildValueType() {
				return ShapeReplacementConfigElementNodeValue.class;
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
			 */
			@Override
			public NodeValue<ShapeReplacementConfigElement> createNodeValue(final Object value) {
				return new ShapeReplacementConfigElementNodeValue((ShapeReplacementConfigElement) value);
			}
	
			private class ShapeReplacementConfigElementNodeValue extends ConfigElementListNodeValue<ShapeReplacementConfigElement> {
				private static final long serialVersionUID = 1L;
	
				/**
				 * @param value
				 */
				public ShapeReplacementConfigElementNodeValue(final ShapeReplacementConfigElement value) {
					super(value);
				}
			}
		}
		private class ShapeAdjustmentListElementNode extends AbstractConfigElementListNode<ShapeAdjustmentConfigElement> {
			public static final String NODE_CLASS = "node.class.ShapeAdjustmentListElement";
			
			/**
			 * @param config
			 */
			public ShapeAdjustmentListElementNode(final MultiShapeReplacementConfig config) {
				super(config.getExtensionId() + ".shapeAdjustmentList", config.getShapeAdjustmentListElement());
				setNodeClass(ShapeAdjustmentListElementNode.NODE_CLASS);
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ShapeAdjustmentListElement"));
			}

			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
			 */
			@Override
			protected AbstractConfigElementNode<ShapeAdjustmentConfigElement> createChildNode(final ShapeAdjustmentConfigElement value) {
				return new ShapeAdjustmentConfigElementNode(value);
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
			 */
			@Override
			public Class<?> getChildValueType() {
				return ShapeAdjustmentConfigElementNodeValue.class;
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
			 */
			@Override
			public NodeValue<ShapeAdjustmentConfigElement> createNodeValue(final Object value) {
				return new ShapeAdjustmentConfigElementNodeValue((ShapeAdjustmentConfigElement) value);
			}
	
			private class ShapeAdjustmentConfigElementNodeValue extends ConfigElementListNodeValue<ShapeAdjustmentConfigElement> {
				private static final long serialVersionUID = 1L;
	
				/**
				 * @param value
				 */
				public ShapeAdjustmentConfigElementNodeValue(final ShapeAdjustmentConfigElement value) {
					super(value);
				}
			}
		}
	}
}
