/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementNode;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.ConfigElementListNodeValue;

/**
 * @author Andrea Medeghini
 */
public class PathOperationConfigElementNode extends AbstractConfigElementNode<PathOperationConfigElement> {
	private static final String NODE_ID = PathOperationConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.PathOperationElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PathOperationElement");
	private final PathOperationConfigElement pathOperation;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param pathOperation the pathOperation element.
	 */
	public PathOperationConfigElementNode(final PathOperationConfigElement pathOperation) {
		super(PathOperationConfigElementNode.NODE_ID);
		if (pathOperation == null) {
			throw new IllegalArgumentException("pathOperation is null");
		}
		this.pathOperation = pathOperation;
		setNodeLabel(PathOperationConfigElementNode.NODE_LABEL);
		setNodeClass(PathOperationConfigElementNode.NODE_CLASS);
		setNodeValue(new PathOperationConfigElementNodeValue(pathOperation));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof PathOperationConfigElementNode) {
			return (pathOperation == ((PathOperationConfigElementNode) o).pathOperation);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PathOperationConfigElement getConfigElement() {
		return pathOperation;
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
		createChildNodes((PathOperationConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new PathOperationNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final PathOperationConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ExtensionElementNode(PathOperationConfigElementNode.NODE_ID + ".extension", value.getValue()));
		appendChildNode(new PathAdjustmentListElementNode(PathOperationConfigElementNode.NODE_ID + ".pathAdjustmentList", value.getValue()));
	}

	private static class PathOperationNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public PathOperationNodeEditor(final Node node) {
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
			return new PathOperationConfigElementNodeValue((PathOperationConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PathOperationConfigElementNodeValue.class;
		}
	}

	private static class ExtensionElementNode extends ConfigurableExtensionReferenceElementNode<PathOperationExtensionConfig> {
		public static final String NODE_CLASS = "node.class.PathOperationReference";

		/**
		 * @param nodeId
		 * @param pathOperation
		 */
		public ExtensionElementNode(final String nodeId, final PathOperationConfigElement pathOperation) {
			super(nodeId, pathOperation.getExtensionElement());
			setNodeClass(ExtensionElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PathOperationExtensionConfig> value) {
			return new PathOperationExtensionReferenceNodeValue(value);
		}
	}
	private static class PathAdjustmentListElementNode extends AbstractConfigElementListNode<PathAdjustmentConfigElement> {
		public static final String NODE_CLASS = "node.class.PathAdjustmentListElement";

		/**
		 * @param nodeId
		 * @param pathOperation
		 */
		public PathAdjustmentListElementNode(final String nodeId, final PathOperationConfigElement pathOperation) {
			super(nodeId, pathOperation.getPathAdjustmentListElement());
			setNodeClass(PathAdjustmentListElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
		 */
		@Override
		protected AbstractConfigElementNode<PathAdjustmentConfigElement> createChildNode(final PathAdjustmentConfigElement value) {
			return new PathAdjustmentConfigElementNode(value);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
		 */
		@Override
		public Class<?> getChildValueType() {
			return PathAdjustmentConfigElementNodeValue.class;
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
		 */
		@Override
		public NodeValue<PathAdjustmentConfigElement> createNodeValue(final Object value) {
			return new PathAdjustmentConfigElementNodeValue((PathAdjustmentConfigElement) value);
		}

		private class PathAdjustmentConfigElementNodeValue extends ConfigElementListNodeValue<PathAdjustmentConfigElement> {
			private static final long serialVersionUID = 1L;

			/**
			 * @param value
			 */
			public PathAdjustmentConfigElementNodeValue(final PathAdjustmentConfigElement value) {
				super(value);
			}
		}
	}
}
