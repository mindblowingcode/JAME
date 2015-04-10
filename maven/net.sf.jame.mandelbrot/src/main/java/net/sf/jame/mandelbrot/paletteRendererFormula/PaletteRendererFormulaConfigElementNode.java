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
package net.sf.jame.mandelbrot.paletteRendererFormula;

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
public class PaletteRendererFormulaConfigElementNode extends AbstractConfigElementNode<PaletteRendererFormulaConfigElement> {
	public static final String NODE_ID = PaletteRendererFormulaConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.PaletteRendererFormulaElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.PaletteRendererFormulaElement");
	private final PaletteRendererFormulaConfigElement formulaElement;

	/**
	 * @param formulaElement
	 */
	public PaletteRendererFormulaConfigElementNode(final PaletteRendererFormulaConfigElement formulaElement) {
		super(PaletteRendererFormulaConfigElementNode.NODE_ID);
		if (formulaElement == null) {
			throw new IllegalArgumentException("formulaElement is null");
		}
		this.formulaElement = formulaElement;
		if (formulaElement.getReference() != null) {
			setNodeValue(new PaletteRendererFormulaConfigElementNodeValue(formulaElement));
		}
		setNodeLabel(PaletteRendererFormulaConfigElementNode.NODE_LABEL);
		setNodeClass(PaletteRendererFormulaConfigElementNode.NODE_CLASS);
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PaletteRendererFormulaConfigElement getConfigElement() {
		return formulaElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((PaletteRendererFormulaConfigElementNodeValue) getNodeValue());
	}

	protected void createChildNodes(final PaletteRendererFormulaConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new PaletteRendererFormulaReferenceNode(PaletteRendererFormulaConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class PaletteRendererFormulaReferenceNode extends ExtensionReferenceElementNode {
		public static final String NODE_CLASS = "node.class.PaletteRendererFormulaReference";

		/**
		 * @param nodeId
		 * @param filterElement
		 */
		public PaletteRendererFormulaReferenceNode(final String nodeId, final PaletteRendererFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getExtensionElement());
			setNodeClass(PaletteRendererFormulaReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ExtensionReference value) {
			// return new PaletteRendererFormulaExtensionReferenceNodeValue(value != null ? value.clone() : null);
			return new PaletteRendererFormulaExtensionReferenceNodeValue(value);
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
	// return ((PaletteRendererFormulaConfigElementNodeValue) getNodeValue()).getValue().getReference();
	// }
	// return null;
	// }
	/**
	 * @return the formulaElement
	 */
	public PaletteRendererFormulaConfigElement getFormulaElement() {
		return formulaElement;
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
			// return new PaletteRendererFormulaConfigElementNodeValue((PaletteRendererFormulaConfigElement) value != null ? ((PaletteRendererFormulaConfigElement) value).clone() : null);
			return new PaletteRendererFormulaConfigElementNodeValue((PaletteRendererFormulaConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PaletteRendererFormulaConfigElementNodeValue.class;
		}
	}
}
