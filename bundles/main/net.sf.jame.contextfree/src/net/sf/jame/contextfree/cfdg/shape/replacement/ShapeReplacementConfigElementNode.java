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
package net.sf.jame.contextfree.cfdg.shape.replacement;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shape.adjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shape.adjustment.ShapeAdjustmentConfigElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.ConfigElementListNodeValue;

/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementConfigElementNode extends AbstractConfigElementNode<ShapeReplacementConfigElement> {
	private static final String NODE_ID = ShapeReplacementConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.ShapeReplacementElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.ShapeReplacementElement");
	private final ShapeReplacementConfigElement shapeReplacement;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param shapeReplacement the shapeReplacement element.
	 */
	public ShapeReplacementConfigElementNode(final ShapeReplacementConfigElement shapeReplacement) {
		super(ShapeReplacementConfigElementNode.NODE_ID);
		if (shapeReplacement == null) {
			throw new IllegalArgumentException("shapeReplacement is null");
		}
		this.shapeReplacement = shapeReplacement;
		setNodeLabel(ShapeReplacementConfigElementNode.NODE_LABEL);
		setNodeClass(ShapeReplacementConfigElementNode.NODE_CLASS);
		setNodeValue(new ShapeReplacementConfigElementNodeValue(shapeReplacement));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof ShapeReplacementConfigElementNode) {
			return (shapeReplacement == ((ShapeReplacementConfigElementNode) o).shapeReplacement);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public ShapeReplacementConfigElement getConfigElement() {
		return shapeReplacement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#addDescription(java.lang.StringBuilder)
	 */
	@Override
	protected void addDescription(final StringBuilder builder) {
		if (getChildNodeCount() > 0) {
			builder.append(getChildNode(0).getLabel());
		}
		else {
			super.addDescription(builder);
		}
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateNode()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((ShapeReplacementConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ShapeReplacementNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final ShapeReplacementConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new NameElementNode(ShapeReplacementConfigElementNode.NODE_ID + ".name", value.getValue()));
		appendChildNode(new ShapeAdjustmentListElementNode(ShapeReplacementConfigElementNode.NODE_ID + ".shapeAdjustmentList", value.getValue()));
	}

	private static class ShapeReplacementNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public ShapeReplacementNodeEditor(final Node node) {
			super(node);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createChildNode(net.sf.jame.core.tree.NodeValue)
		 */
		@Override
		protected Node createChildNode(final NodeValue<?> value) {
			return null;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new ShapeReplacementConfigElementNodeValue((ShapeReplacementConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ShapeReplacementConfigElementNodeValue.class;
		}
	}

	private static class NameElementNode extends StringElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.NameElement");

		/**
		 * @param nodeId
		 * @param shapeReplacement
		 */
		public NameElementNode(final String nodeId, final ShapeReplacementConfigElement shapeReplacement) {
			super(nodeId, shapeReplacement.getNameElement());
			setNodeLabel(NameElementNode.NODE_LABEL);
		}
	}
	private static class ShapeAdjustmentListElementNode extends AbstractConfigElementListNode<ShapeAdjustmentConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeAdjustmentListElement";

		/**
		 * @param nodeId
		 * @param shapeReplacement
		 */
		public ShapeAdjustmentListElementNode(final String nodeId, final ShapeReplacementConfigElement shapeReplacement) {
			super(nodeId, shapeReplacement.getShapeAdjustmentListElement());
			setNodeClass(ShapeAdjustmentListElementNode.NODE_CLASS);
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
