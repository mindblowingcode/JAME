/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementNode;
import net.sf.jame.core.common.FloatElementNode;
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
public class RuleConfigElementNode extends AbstractConfigElementNode<RuleConfigElement> {
	private static final String NODE_ID = RuleConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.RuleElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.RuleElement");
	private final RuleConfigElement rule;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param rule the rule element.
	 */
	public RuleConfigElementNode(final RuleConfigElement rule) {
		super(RuleConfigElementNode.NODE_ID);
		if (rule == null) {
			throw new IllegalArgumentException("rule is null");
		}
		this.rule = rule;
		setNodeLabel(RuleConfigElementNode.NODE_LABEL);
		setNodeClass(RuleConfigElementNode.NODE_CLASS);
		setNodeValue(new RuleConfigElementNodeValue(rule));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof RuleConfigElementNode) {
			return (rule == ((RuleConfigElementNode) o).rule);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public RuleConfigElement getConfigElement() {
		return rule;
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
		createChildNodes((RuleConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new RuleNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final RuleConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new NameElementNode(RuleConfigElementNode.NODE_ID + ".name", value.getValue()));
		appendChildNode(new PropabilityElementNode(RuleConfigElementNode.NODE_ID + ".propability", value.getValue()));
		appendChildNode(new ShapeReplacementListElementNode(RuleConfigElementNode.NODE_ID + ".shapeReplacementList", value.getValue()));
	}

	private static class RuleNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public RuleNodeEditor(final Node node) {
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
			return new RuleConfigElementNodeValue((RuleConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return RuleConfigElementNodeValue.class;
		}
	}

	private static class NameElementNode extends StringElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.NameElement");

		/**
		 * @param nodeId
		 * @param rule
		 */
		public NameElementNode(final String nodeId, final RuleConfigElement rule) {
			super(nodeId, rule.getNameElement());
			setNodeLabel(NameElementNode.NODE_LABEL);
		}
	}
	private static class PropabilityElementNode extends FloatElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PropabilityElement");

		/**
		 * @param nodeId
		 * @param rule
		 */
		public PropabilityElementNode(final String nodeId, final RuleConfigElement rule) {
			super(nodeId, rule.getPropabilityElement());
			setNodeLabel(PropabilityElementNode.NODE_LABEL);
		}
	}
	private static class ShapeReplacementListElementNode extends AbstractConfigElementListNode<ShapeReplacementConfigElement> {
		public static final String NODE_CLASS = "node.class.ShapeReplacementListElement";

		/**
		 * @param nodeId
		 * @param rule
		 */
		public ShapeReplacementListElementNode(final String nodeId, final RuleConfigElement rule) {
			super(nodeId, rule.getShapeReplacementListElement());
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
}
