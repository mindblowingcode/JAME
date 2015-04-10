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
package net.sf.jame.mandelbrot.colorRendererFormula;

import net.sf.jame.core.common.ExtensionReferenceElementNode;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.mandelbrot.MandelbrotResources;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererFormulaConfigElementNode extends AbstractConfigElementNode<ColorRendererFormulaConfigElement> {
	public static final String NODE_ID = ColorRendererFormulaConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.ColorRendererFormulaElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.ColorRendererFormulaElement");
	private final ColorRendererFormulaConfigElement formulaElement;

	/**
	 * @param formulaElement
	 */
	public ColorRendererFormulaConfigElementNode(final ColorRendererFormulaConfigElement formulaElement) {
		super(ColorRendererFormulaConfigElementNode.NODE_ID);
		if (formulaElement == null) {
			throw new IllegalArgumentException("formulaElement is null");
		}
		this.formulaElement = formulaElement;
		if (formulaElement.getReference() != null) {
			setNodeValue(new ColorRendererFormulaConfigElementNodeValue(formulaElement));
		}
		setNodeLabel(ColorRendererFormulaConfigElementNode.NODE_LABEL);
		setNodeClass(ColorRendererFormulaConfigElementNode.NODE_CLASS);
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public ColorRendererFormulaConfigElement getConfigElement() {
		return formulaElement;
	}

	protected void createChildNodes(final ColorRendererFormulaConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ColorRendererFormulaReferenceNode(ColorRendererFormulaConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class ColorRendererFormulaReferenceNode extends ExtensionReferenceElementNode {
		public static final String NODE_CLASS = "node.class.ColorRendererFormulaReference";

		/**
		 * @param nodeId
		 * @param filterElement
		 */
		public ColorRendererFormulaReferenceNode(final String nodeId, final ColorRendererFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getExtensionElement());
			setNodeClass(ColorRendererFormulaReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ExtensionReference value) {
			// return new ColorRendererFormulaExtensionReferenceNodeValue(value != null ? value.clone() : null);
			return new ColorRendererFormulaExtensionReferenceNodeValue(value);
		}
	}

	// private ExtensionReference getReference() {
	// if ((getNodeValue() != null) && (getNodeValue().getValue() != null)) {
	// return ((ColorRendererFormulaConfigElementNodeValue) getNodeValue()).getValue().getReference();
	// }
	// return null;
	// }
	/**
	 * @see net.sf.jame.core.tree.DefaultNode#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
	}

	/**
	 * @return the formulaElement
	 */
	public ColorRendererFormulaConfigElement getFormulaElement() {
		return formulaElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((ColorRendererFormulaConfigElementNodeValue) getNodeValue());
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
			// return new ColorRendererFormulaConfigElementNodeValue((ColorRendererFormulaConfigElement) value != null ? ((ColorRendererFormulaConfigElement) value).clone() : null);
			return new ColorRendererFormulaConfigElementNodeValue((ColorRendererFormulaConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ColorRendererFormulaConfigElementNodeValue.class;
		}
	}
}
