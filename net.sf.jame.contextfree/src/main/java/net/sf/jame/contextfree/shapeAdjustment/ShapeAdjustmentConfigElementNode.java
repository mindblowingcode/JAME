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
package net.sf.jame.contextfree.shapeAdjustment;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;

/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentConfigElementNode extends AbstractConfigElementNode<ShapeAdjustmentConfigElement> {
	private static final String NODE_ID = ShapeAdjustmentConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.ShapeAdjustmentElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.ShapeAdjustmentElement");
	private final ShapeAdjustmentConfigElement shapeAdjustment;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param shapeAdjustment the shapeAdjustment element.
	 */
	public ShapeAdjustmentConfigElementNode(final ShapeAdjustmentConfigElement shapeAdjustment) {
		super(ShapeAdjustmentConfigElementNode.NODE_ID);
		if (shapeAdjustment == null) {
			throw new IllegalArgumentException("shapeAdjustment is null");
		}
		this.shapeAdjustment = shapeAdjustment;
		setNodeLabel(ShapeAdjustmentConfigElementNode.NODE_LABEL);
		setNodeClass(ShapeAdjustmentConfigElementNode.NODE_CLASS);
		setNodeValue(new ShapeAdjustmentConfigElementNodeValue(shapeAdjustment));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof ShapeAdjustmentConfigElementNode) {
			return (shapeAdjustment == ((ShapeAdjustmentConfigElementNode) o).shapeAdjustment);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public ShapeAdjustmentConfigElement getConfigElement() {
		return shapeAdjustment;
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
		createChildNodes((ShapeAdjustmentConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ShapeAdjustmentNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final ShapeAdjustmentConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ExtensionElementNode(ShapeAdjustmentConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class ShapeAdjustmentNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public ShapeAdjustmentNodeEditor(final Node node) {
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
			return new ShapeAdjustmentConfigElementNodeValue((ShapeAdjustmentConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ShapeAdjustmentConfigElementNodeValue.class;
		}
	}

	private static class ExtensionElementNode extends ConfigurableExtensionReferenceElementNode<ShapeAdjustmentExtensionConfig> {
		public static final String NODE_CLASS = "node.class.ShapeAdjustmentReference";

		/**
		 * @param nodeId
		 * @param shapeAdjustment
		 */
		public ExtensionElementNode(final String nodeId, final ShapeAdjustmentConfigElement shapeAdjustment) {
			super(nodeId, shapeAdjustment.getExtensionElement());
			setNodeClass(ExtensionElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<ShapeAdjustmentExtensionConfig> value) {
			return new ShapeAdjustmentExtensionReferenceNodeValue(value);
		}
	}
}
