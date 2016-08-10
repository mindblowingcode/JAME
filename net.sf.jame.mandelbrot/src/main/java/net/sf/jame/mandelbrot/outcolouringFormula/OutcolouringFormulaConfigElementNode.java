/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.mandelbrot.outcolouringFormula;

import net.sf.jame.core.common.BooleanElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.mandelbrot.MandelbrotResources;
import net.sf.jame.mandelbrot.common.IterationsElementNode;
import net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionConfig;
import net.sf.jame.twister.TwisterResources;
import net.sf.jame.twister.util.OpacityElementNode;

/**
 * @author Andrea Medeghini
 */
public class OutcolouringFormulaConfigElementNode extends AbstractConfigElementNode<OutcolouringFormulaConfigElement> {
	public static final String NODE_ID = OutcolouringFormulaConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.OutcolouringFormulaElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.OutcolouringFormulaElement");
	private final OutcolouringFormulaConfigElement formulaElement;

	/**
	 * @param formulaElement
	 */
	public OutcolouringFormulaConfigElementNode(final OutcolouringFormulaConfigElement formulaElement) {
		super(OutcolouringFormulaConfigElementNode.NODE_ID);
		if (formulaElement == null) {
			throw new IllegalArgumentException("formulaElement is null");
		}
		this.formulaElement = formulaElement;
		setNodeLabel(OutcolouringFormulaConfigElementNode.NODE_LABEL);
		setNodeClass(OutcolouringFormulaConfigElementNode.NODE_CLASS);
		setNodeValue(new OutcolouringFormulaConfigElementNodeValue(formulaElement));
	}

	protected void createChildNodes(final OutcolouringFormulaConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new OutcolouringFormulaReferenceNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".extension", value.getValue()));
		appendChildNode(new OutcolouringFormulaLockedNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".locked", value.getValue()));
		appendChildNode(new OutcolouringFormulaEnabledNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".enabled", value.getValue()));
		appendChildNode(new OutcolouringFormulaOpacityNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".opacity", value.getValue()));
		appendChildNode(new OutcolouringFormulaIterationsNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".iterations", value.getValue()));
		appendChildNode(new OutcolouringFormulaAutoIterationsNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".autoIterations", value.getValue()));
		appendChildNode(new OutcolouringFormulaLabelNode(OutcolouringFormulaConfigElementNode.NODE_ID + ".label", value.getValue()));
	}

	private static class OutcolouringFormulaReferenceNode extends ConfigurableExtensionReferenceElementNode<OutcolouringFormulaExtensionConfig> {
		public static final String NODE_CLASS = "node.class.OutcolouringFormulaReference";

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaReferenceNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getExtensionElement());
			setNodeClass(OutcolouringFormulaReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<OutcolouringFormulaExtensionConfig> value) {
			// return new OutcolouringFormulaExtensionReferenceNodeValue(value != null ? value.clone() : null);
			return new OutcolouringFormulaExtensionReferenceNodeValue(value);
		}
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
	}

	// private ExtensionReference getReference() {
	// if ((getNodeValue() != null) && (getNodeValue().getValue() != null)) {
	// return ((OutcolouringFormulaConfigElementNodeValue) getNodeValue()).getValue().getReference();
	// }
	// return null;
	// }
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
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public OutcolouringFormulaConfigElement getConfigElement() {
		return formulaElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((OutcolouringFormulaConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new FormulaNodeEditor(this);
	}

	private static class FormulaNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public FormulaNodeEditor(final Node node) {
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
			// return new OutcolouringFormulaConfigElementNodeValue((OutcolouringFormulaConfigElement) value != null ? ((OutcolouringFormulaConfigElement) value).clone() : null);
			return new OutcolouringFormulaConfigElementNodeValue((OutcolouringFormulaConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return OutcolouringFormulaConfigElementNodeValue.class;
		}
	}

	private static class OutcolouringFormulaOpacityNode extends OpacityElementNode {
		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaOpacityNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getOpacityElement());
		}
	}

	private static class OutcolouringFormulaIterationsNode extends IterationsElementNode {
		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaIterationsNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getIterationsElement());
		}
	}

	private static class OutcolouringFormulaLockedNode extends BooleanElementNode {
		private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.LockedElement");

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaLockedNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getLockedElement());
			setNodeLabel(OutcolouringFormulaLockedNode.NODE_LABEL);
		}
	}

	private static class OutcolouringFormulaEnabledNode extends BooleanElementNode {
		private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.EnabledElement");

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaEnabledNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getEnabledElement());
			setNodeLabel(OutcolouringFormulaEnabledNode.NODE_LABEL);
		}
	}

	private static class OutcolouringFormulaAutoIterationsNode extends BooleanElementNode {
		private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.AutoIterationsElement");

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaAutoIterationsNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getAutoIterationsElement());
			setNodeLabel(OutcolouringFormulaAutoIterationsNode.NODE_LABEL);
		}
	}

	private static class OutcolouringFormulaLabelNode extends StringElementNode {
		private static final String NODE_LABEL = TwisterResources.getInstance().getString("node.label.LabelElement");

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public OutcolouringFormulaLabelNode(final String nodeId, final OutcolouringFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getLabelElement());
			setNodeLabel(OutcolouringFormulaLabelNode.NODE_LABEL);
		}
	}
}
