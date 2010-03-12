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
import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElement;
import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElementNode;
import net.sf.jame.contextfree.cfdg.shape.adjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shape.adjustment.ShapeAdjustmentConfigElementNode;
import net.sf.jame.core.common.IntegerElementNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.AbstractConfigElementSingleNode;
import net.sf.jame.core.util.ConfigElementListNodeValue;
import net.sf.jame.core.util.ConfigElementSingleNodeValue;

/**
 * @author Andrea Medeghini
 */
public class LoopShapeReplacementConfigElementNode extends AbstractConfigElementNode<LoopShapeReplacementConfigElement> {
	private static final String NODE_ID = LoopShapeReplacementConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.LoopShapeReplacementElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.LoopShapeReplacementElement");
	private final LoopShapeReplacementConfigElement loopShapeReplacement;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param loopShapeReplacement the loopShapeReplacement element.
	 */
	public LoopShapeReplacementConfigElementNode(final LoopShapeReplacementConfigElement loopShapeReplacement) {
		super(LoopShapeReplacementConfigElementNode.NODE_ID);
		if (loopShapeReplacement == null) {
			throw new IllegalArgumentException("loopShapeReplacement is null");
		}
		this.loopShapeReplacement = loopShapeReplacement;
		setNodeLabel(LoopShapeReplacementConfigElementNode.NODE_LABEL);
		setNodeClass(LoopShapeReplacementConfigElementNode.NODE_CLASS);
		setNodeValue(new LoopShapeReplacementConfigElementNodeValue(loopShapeReplacement));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof LoopShapeReplacementConfigElementNode) {
			return (loopShapeReplacement == ((LoopShapeReplacementConfigElementNode) o).loopShapeReplacement);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public LoopShapeReplacementConfigElement getConfigElement() {
		return loopShapeReplacement;
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
		createChildNodes((LoopShapeReplacementConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new LoopShapeReplacementNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final LoopShapeReplacementConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new TimesElementNode(LoopShapeReplacementConfigElementNode.NODE_ID + ".times", value.getValue()));
		appendChildNode(new ShapeAdjustmentListElementNode(LoopShapeReplacementConfigElementNode.NODE_ID + ".shapeAdjustmentList", value.getValue()));
		appendChildNode(new ShapeSingleElementNode(LoopShapeReplacementConfigElementNode.NODE_ID + ".shapeSingle", value.getValue()));
	}

	private static class LoopShapeReplacementNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public LoopShapeReplacementNodeEditor(final Node node) {
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
			return new LoopShapeReplacementConfigElementNodeValue((LoopShapeReplacementConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return LoopShapeReplacementConfigElementNodeValue.class;
		}
	}

	private static class TimesElementNode extends IntegerElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.TimesElement");

		/**
		 * @param nodeId
		 * @param loopShapeReplacement
		 */
		public TimesElementNode(final String nodeId, final LoopShapeReplacementConfigElement loopShapeReplacement) {
			super(nodeId, loopShapeReplacement.getTimesElement());
			setNodeLabel(TimesElementNode.NODE_LABEL);
		}
	}
	private static class ShapeAdjustmentListElementNode extends AbstractConfigElementListNode<ShapeAdjustmentConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeAdjustmentListElement";

		/**
		 * @param nodeId
		 * @param loopShapeReplacement
		 */
		public ShapeAdjustmentListElementNode(final String nodeId, final LoopShapeReplacementConfigElement loopShapeReplacement) {
			super(nodeId, loopShapeReplacement.getShapeAdjustmentListElement());
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
	private static class ShapeSingleElementNode extends AbstractConfigElementSingleNode<ShapeConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeSingleElement";

		/**
		 * @param nodeId
		 * @param loopShapeReplacement
		 */
		public ShapeSingleElementNode(final String nodeId, final LoopShapeReplacementConfigElement loopShapeReplacement) {
			super(nodeId, loopShapeReplacement.getShapeSingleElement());
			setNodeClass(ShapeSingleElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
		 */
		@Override
		protected AbstractConfigElementNode<ShapeConfigElement> createChildNode(final ShapeConfigElement value) {
			return new ShapeConfigElementNode(value);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
		 */
		@Override
		public NodeValue<ShapeConfigElement> createNodeValue(final Object value) {
			return new ShapeConfigElementNodeValue((ShapeConfigElement) value);
		}

		private class ShapeConfigElementNodeValue extends ConfigElementSingleNodeValue<ShapeConfigElement> {
			private static final long serialVersionUID = 1L;

			/**
			 * @param value
			 */
			public ShapeConfigElementNodeValue(final ShapeConfigElement value) {
				super(value);
			}
		}
	}
}
