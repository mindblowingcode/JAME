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
package net.sf.jame.mandelbrot.processingFormula;

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
public class ProcessingFormulaConfigElementNode extends AbstractConfigElementNode<ProcessingFormulaConfigElement> {
	public static final String NODE_ID = ProcessingFormulaConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.ProcessingFormulaElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.ProcessingFormulaElement");
	private final ProcessingFormulaConfigElement formulaElement;

	/**
	 * @param formulaElement
	 */
	public ProcessingFormulaConfigElementNode(final ProcessingFormulaConfigElement formulaElement) {
		super(ProcessingFormulaConfigElementNode.NODE_ID);
		if (formulaElement == null) {
			throw new IllegalArgumentException("formulaElement is null");
		}
		this.formulaElement = formulaElement;
		setNodeLabel(ProcessingFormulaConfigElementNode.NODE_LABEL);
		setNodeClass(ProcessingFormulaConfigElementNode.NODE_CLASS);
		setNodeValue(new ProcessingFormulaConfigElementNodeValue(formulaElement));
	}

	protected void createChildNodes(final ProcessingFormulaConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ProcessingFormulaReferenceNode(ProcessingFormulaConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class ProcessingFormulaReferenceNode extends ExtensionReferenceElementNode {
		public static final String NODE_CLASS = "node.class.ProcessingFormulaReference";

		/**
		 * @param nodeId
		 * @param formulaElement
		 */
		public ProcessingFormulaReferenceNode(final String nodeId, final ProcessingFormulaConfigElement formulaElement) {
			super(nodeId, formulaElement.getExtensionElement());
			setNodeClass(ProcessingFormulaReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ExtensionReference value) {
			return new ProcessingFormulaExtensionReferenceNodeValue(value);
		}
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
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
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public ProcessingFormulaConfigElement getConfigElement() {
		return formulaElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((ProcessingFormulaConfigElementNodeValue) getNodeValue());
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
			return new ProcessingFormulaConfigElementNodeValue((ProcessingFormulaConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ProcessingFormulaConfigElementNodeValue.class;
		}
	}
}
