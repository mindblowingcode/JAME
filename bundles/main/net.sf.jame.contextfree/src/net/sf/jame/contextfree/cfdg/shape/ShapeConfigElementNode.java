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
package net.sf.jame.contextfree.cfdg.shape;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shape.replacement.LoopShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.LoopShapeReplacementConfigElementNode;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElementNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.ConfigElementListNodeValue;

/**
 * @author Andrea Medeghini
 */
public class ShapeConfigElementNode extends AbstractConfigElementNode<ShapeConfigElement> {
	private static final String NODE_ID = ShapeConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.ShapeElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.ShapeElement");
	private final ShapeConfigElement shape;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param shape the shape element.
	 */
	public ShapeConfigElementNode(final ShapeConfigElement shape) {
		super(ShapeConfigElementNode.NODE_ID);
		if (shape == null) {
			throw new IllegalArgumentException("shape is null");
		}
		this.shape = shape;
		setNodeLabel(ShapeConfigElementNode.NODE_LABEL);
		setNodeClass(ShapeConfigElementNode.NODE_CLASS);
		setNodeValue(new ShapeConfigElementNodeValue(shape));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof ShapeConfigElementNode) {
			return (shape == ((ShapeConfigElementNode) o).shape);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public ShapeConfigElement getConfigElement() {
		return shape;
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
		createChildNodes((ShapeConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ShapeNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final ShapeConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ReplacementListElementNode(ShapeConfigElementNode.NODE_ID + ".replacementList", value.getValue()));
	}

	private static class ShapeNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public ShapeNodeEditor(final Node node) {
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
			return new ShapeConfigElementNodeValue((ShapeConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ShapeConfigElementNodeValue.class;
		}
	}

	private static class ReplacementListElementNode extends AbstractConfigElementListNode<ReplacementConfigElement> {
		public static final String NODE_CLASS = "node.class.ReplacementListElement";

		/**
		 * @param nodeId
		 * @param shape
		 */
		public ReplacementListElementNode(final String nodeId, final ShapeConfigElement shape) {
			super(nodeId, shape.getReplacementListElement());
			setNodeClass(ReplacementListElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
		 */
		@Override
		protected AbstractConfigElementNode<? extends ReplacementConfigElement> createChildNode(final ReplacementConfigElement value) {
			if (value.getClassId().equals(ShapeReplacementConfigElement.CLASS_ID)) {
				return new ShapeReplacementConfigElementNode((ShapeReplacementConfigElement) value);
			} else if (value.getClassId().equals(LoopShapeReplacementConfigElement.CLASS_ID)) {
				return new LoopShapeReplacementConfigElementNode((LoopShapeReplacementConfigElement) value);
			}
			return null;
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
		 */
		@Override
		public Class<?> getChildValueType() {
			return ReplacementConfigElementNodeValue.class;
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
		 */
		@Override
		public NodeValue<ReplacementConfigElement> createNodeValue(final Object value) {
			return new ReplacementConfigElementNodeValue((ReplacementConfigElement) value);
		}

		private class ReplacementConfigElementNodeValue extends ConfigElementListNodeValue<ReplacementConfigElement> {
			private static final long serialVersionUID = 1L;

			/**
			 * @param value
			 */
			public ReplacementConfigElementNodeValue(final ReplacementConfigElement value) {
				super(value);
			}
		}
	}
}
