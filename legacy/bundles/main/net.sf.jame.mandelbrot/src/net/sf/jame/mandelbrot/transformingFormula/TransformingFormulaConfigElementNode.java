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
package net.sf.jame.mandelbrot.transformingFormula;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.mandelbrot.MandelbrotResources;
import net.sf.jame.mandelbrot.transformingFormula.extension.TransformingFormulaExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class TransformingFormulaConfigElementNode extends AbstractConfigElementNode<TransformingFormulaConfigElement> {
	public static final String NODE_ID = TransformingFormulaConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.TransformingFormulaElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.TransformingFormulaElement");
	private final TransformingFormulaConfigElement formulaElement;

	/**
	 * @param formulaElement
	 */
	public TransformingFormulaConfigElementNode(final TransformingFormulaConfigElement formulaElement) {
		super(TransformingFormulaConfigElementNode.NODE_ID);
		if (formulaElement == null) {
			throw new IllegalArgumentException("formulaElement is null");
		}
		this.formulaElement = formulaElement;
		setNodeLabel(TransformingFormulaConfigElementNode.NODE_LABEL);
		setNodeClass(TransformingFormulaConfigElementNode.NODE_CLASS);
		setNodeValue(new TransformingFormulaConfigElementNodeValue(formulaElement));
	}

	protected void createChildNodes(final TransformingFormulaConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new TransformingFormulaReferenceNode(TransformingFormulaConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class TransformingFormulaReferenceNode extends ConfigurableExtensionReferenceElementNode<TransformingFormulaExtensionConfig> {
		public static final String NODE_CLASS = "node.class.TransformingFormulaReference";

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public TransformingFormulaReferenceNode(final String nodeId, final TransformingFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getExtensionElement());
			setNodeClass(TransformingFormulaReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<TransformingFormulaExtensionConfig> value) {
			// return new TransformingFormulaExtensionReferenceNodeValue(value != null ? value.clone() : null);
			return new TransformingFormulaExtensionReferenceNodeValue(value);
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
	// return ((TransformingFormulaConfigElementNodeValue) getNodeValue()).getValue().getReference();
	// }
	// return null;
	// }
	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public TransformingFormulaConfigElement getConfigElement() {
		return formulaElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((TransformingFormulaConfigElementNodeValue) getNodeValue());
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
			// return new TransformingFormulaConfigElementNodeValue((TransformingFormulaConfigElement) value != null ? ((TransformingFormulaConfigElement) value).clone() : null);
			return new TransformingFormulaConfigElementNodeValue((TransformingFormulaConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return TransformingFormulaConfigElementNodeValue.class;
		}
	}
}
