/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathReplacement;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementNode;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;
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
public class PathReplacementConfigElementNode extends AbstractConfigElementNode<PathReplacementConfigElement> {
	private static final String NODE_ID = PathReplacementConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.PathReplacementElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PathReplacementElement");
	private final PathReplacementConfigElement pathReplacement;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param pathOperation the pathOperation element.
	 */
	public PathReplacementConfigElementNode(final PathReplacementConfigElement pathReplacement) {
		super(PathReplacementConfigElementNode.NODE_ID);
		if (pathReplacement == null) {
			throw new IllegalArgumentException("pathReplacement is null");
		}
		this.pathReplacement = pathReplacement;
		setNodeLabel(PathReplacementConfigElementNode.NODE_LABEL);
		setNodeClass(PathReplacementConfigElementNode.NODE_CLASS);
		setNodeValue(new PathReplacementConfigElementNodeValue(pathReplacement));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof PathReplacementConfigElementNode) {
			return (pathReplacement == ((PathReplacementConfigElementNode) o).pathReplacement);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PathReplacementConfigElement getConfigElement() {
		return pathReplacement;
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
		createChildNodes((PathReplacementConfigElementNodeValue) getNodeValue());
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
	protected void createChildNodes(final PathReplacementConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ExtensionElementNode(PathReplacementConfigElementNode.NODE_ID + ".extension", value.getValue()));
		appendChildNode(new PathAdjustmentListElementNode(PathReplacementConfigElementNode.NODE_ID + ".pathAdjustmentList", value.getValue()));
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
			return new PathReplacementConfigElementNodeValue((PathReplacementConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PathReplacementConfigElementNodeValue.class;
		}
	}

	private static class ExtensionElementNode extends ConfigurableExtensionReferenceElementNode<PathReplacementExtensionConfig> {
		public static final String NODE_CLASS = "node.class.PathOperationReference";

		/**
		 * @param nodeId
		 * @param pathReplacement
		 */
		public ExtensionElementNode(final String nodeId, final PathReplacementConfigElement pathReplacement) {
			super(nodeId, pathReplacement.getExtensionElement());
			setNodeClass(ExtensionElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PathReplacementExtensionConfig> value) {
			return new PathReplacementExtensionReferenceNodeValue(value);
		}
	}
	private static class PathAdjustmentListElementNode extends AbstractConfigElementListNode<PathAdjustmentConfigElement> {
		public static final String NODE_CLASS = "node.class.PathAdjustmentListElement";

		/**
		 * @param nodeId
		 * @param pathReplacement
		 */
		public PathAdjustmentListElementNode(final String nodeId, final PathReplacementConfigElement pathReplacement) {
			super(nodeId, pathReplacement.getPathAdjustmentListElement());
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
