/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathAdjustment;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentConfigElementNode extends AbstractConfigElementNode<PathAdjustmentConfigElement> {
	private static final String NODE_ID = PathAdjustmentConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.PathAdjustmentElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PathAdjustmentElement");
	private final PathAdjustmentConfigElement pathAdjustment;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param pathAdjustment the pathAdjustment element.
	 */
	public PathAdjustmentConfigElementNode(final PathAdjustmentConfigElement pathAdjustment) {
		super(PathAdjustmentConfigElementNode.NODE_ID);
		if (pathAdjustment == null) {
			throw new IllegalArgumentException("pathAdjustment is null");
		}
		this.pathAdjustment = pathAdjustment;
		setNodeLabel(PathAdjustmentConfigElementNode.NODE_LABEL);
		setNodeClass(PathAdjustmentConfigElementNode.NODE_CLASS);
		setNodeValue(new PathAdjustmentConfigElementNodeValue(pathAdjustment));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof PathAdjustmentConfigElementNode) {
			return (pathAdjustment == ((PathAdjustmentConfigElementNode) o).pathAdjustment);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PathAdjustmentConfigElement getConfigElement() {
		return pathAdjustment;
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
		createChildNodes((PathAdjustmentConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new PathAdjustmentNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final PathAdjustmentConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ExtensionElementNode(PathAdjustmentConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class PathAdjustmentNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public PathAdjustmentNodeEditor(final Node node) {
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
			return new PathAdjustmentConfigElementNodeValue((PathAdjustmentConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PathAdjustmentConfigElementNodeValue.class;
		}
	}

	private static class ExtensionElementNode extends ConfigurableExtensionReferenceElementNode<PathAdjustmentExtensionConfig> {
		public static final String NODE_CLASS = "node.class.PathAdjustmentReference";

		/**
		 * @param nodeId
		 * @param pathAdjustment
		 */
		public ExtensionElementNode(final String nodeId, final PathAdjustmentConfigElement pathAdjustment) {
			super(nodeId, pathAdjustment.getExtensionElement());
			setNodeClass(ExtensionElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> value) {
			return new PathAdjustmentExtensionReferenceNodeValue(value);
		}
	}
}
