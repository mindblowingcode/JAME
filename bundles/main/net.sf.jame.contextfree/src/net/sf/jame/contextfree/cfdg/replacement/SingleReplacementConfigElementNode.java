/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementNode;
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
public class SingleReplacementConfigElementNode extends AbstractConfigElementNode<SingleReplacementConfigElement> {
	private static final String NODE_ID = SingleReplacementConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.SingleReplacementElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.SingleReplacementElement");
	private final SingleReplacementConfigElement singleReplacement;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param singleReplacement the singleReplacement element.
	 */
	public SingleReplacementConfigElementNode(final SingleReplacementConfigElement singleReplacement) {
		super(SingleReplacementConfigElementNode.NODE_ID);
		if (singleReplacement == null) {
			throw new IllegalArgumentException("singleReplacement is null");
		}
		this.singleReplacement = singleReplacement;
		setNodeLabel(SingleReplacementConfigElementNode.NODE_LABEL);
		setNodeClass(SingleReplacementConfigElementNode.NODE_CLASS);
		setNodeValue(new SingleReplacementConfigElementNodeValue(singleReplacement));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof SingleReplacementConfigElementNode) {
			return (singleReplacement == ((SingleReplacementConfigElementNode) o).singleReplacement);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public SingleReplacementConfigElement getConfigElement() {
		return singleReplacement;
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
		createChildNodes((SingleReplacementConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new SingleReplacementNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final SingleReplacementConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ShapeElementNode(SingleReplacementConfigElementNode.NODE_ID + ".shape", value.getValue()));
		appendChildNode(new ShapeAdjustmentListElementNode(SingleReplacementConfigElementNode.NODE_ID + ".shapeAdjustmentList", value.getValue()));
	}

	private static class SingleReplacementNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public SingleReplacementNodeEditor(final Node node) {
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
			return new SingleReplacementConfigElementNodeValue((SingleReplacementConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return SingleReplacementConfigElementNodeValue.class;
		}
	}

	private static class ShapeElementNode extends StringElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.ShapeElement");

		/**
		 * @param nodeId
		 * @param singleReplacement
		 */
		public ShapeElementNode(final String nodeId, final SingleReplacementConfigElement singleReplacement) {
			super(nodeId, singleReplacement.getShapeElement());
			setNodeLabel(ShapeElementNode.NODE_LABEL);
		}
	}
	private static class ShapeAdjustmentListElementNode extends AbstractConfigElementListNode<ShapeAdjustmentConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeAdjustmentListElement";

		/**
		 * @param nodeId
		 * @param singleReplacement
		 */
		public ShapeAdjustmentListElementNode(final String nodeId, final SingleReplacementConfigElement singleReplacement) {
			super(nodeId, singleReplacement.getShapeAdjustmentListElement());
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
