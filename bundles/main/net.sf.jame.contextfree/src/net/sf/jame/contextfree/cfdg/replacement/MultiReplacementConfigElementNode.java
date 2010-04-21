/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementNode;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementNode;
import net.sf.jame.core.common.IntegerElementNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.ConfigElementListNodeValue;

/**
 * @author Andrea Medeghini
 */
public class MultiReplacementConfigElementNode extends AbstractConfigElementNode<MultiReplacementConfigElement> {
	private static final String NODE_ID = MultiReplacementConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.multiReplacementElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.multiReplacementElement");
	private final MultiReplacementConfigElement multiReplacement;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param multiReplacement the multiReplacement element.
	 */
	public MultiReplacementConfigElementNode(final MultiReplacementConfigElement multiReplacement) {
		super(MultiReplacementConfigElementNode.NODE_ID);
		if (multiReplacement == null) {
			throw new IllegalArgumentException("multiReplacement is null");
		}
		this.multiReplacement = multiReplacement;
		setNodeLabel(MultiReplacementConfigElementNode.NODE_LABEL);
		setNodeClass(MultiReplacementConfigElementNode.NODE_CLASS);
		setNodeValue(new MultiReplacementConfigElementNodeValue(multiReplacement));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof MultiReplacementConfigElementNode) {
			return (multiReplacement == ((MultiReplacementConfigElementNode) o).multiReplacement);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public MultiReplacementConfigElement getConfigElement() {
		return multiReplacement;
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
		createChildNodes((MultiReplacementConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new MultiReplacementNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final MultiReplacementConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new TimesElementNode(MultiReplacementConfigElementNode.NODE_ID + ".times", value.getValue()));
		appendChildNode(new ShapeReplacementListElementNode(MultiReplacementConfigElementNode.NODE_ID + ".shapeReplacementList", value.getValue()));
		appendChildNode(new ShapeAdjustmentListElementNode(MultiReplacementConfigElementNode.NODE_ID + ".shapeAdjustmentList", value.getValue()));
	}

	private static class MultiReplacementNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public MultiReplacementNodeEditor(final Node node) {
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
			return new MultiReplacementConfigElementNodeValue((MultiReplacementConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return MultiReplacementConfigElementNodeValue.class;
		}
	}

	private static class TimesElementNode extends IntegerElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.TimesElement");

		/**
		 * @param nodeId
		 * @param multiReplacement
		 */
		public TimesElementNode(final String nodeId, final MultiReplacementConfigElement multiReplacement) {
			super(nodeId, multiReplacement.getTimesElement());
			setNodeLabel(TimesElementNode.NODE_LABEL);
		}
	}
	private static class ShapeReplacementListElementNode extends AbstractConfigElementListNode<ShapeReplacementConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeReplacementListElement";

		/**
		 * @param nodeId
		 * @param multiReplacement
		 */
		public ShapeReplacementListElementNode(final String nodeId, final MultiReplacementConfigElement multiReplacement) {
			super(nodeId, multiReplacement.getShapeReplacementListElement());
			setNodeClass(ShapeReplacementListElementNode.NODE_CLASS);
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
	private static class ShapeAdjustmentListElementNode extends AbstractConfigElementListNode<ShapeAdjustmentConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeAdjustmentListElement";

		/**
		 * @param nodeId
		 * @param multiReplacement
		 */
		public ShapeAdjustmentListElementNode(final String nodeId, final MultiReplacementConfigElement multiReplacement) {
			super(nodeId, multiReplacement.getShapeAdjustmentListElement());
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
