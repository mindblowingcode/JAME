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
package net.sf.jame.mandelbrot.colorRenderer;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.mandelbrot.MandelbrotResources;
import net.sf.jame.mandelbrot.colorRenderer.extension.ColorRendererExtensionConfig;
import net.sf.jame.mandelbrot.colorRendererFormula.ColorRendererFormulaConfigElement;
import net.sf.jame.mandelbrot.colorRendererFormula.ColorRendererFormulaConfigElementNodeValue;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererConfigElementNode extends AbstractConfigElementNode<ColorRendererConfigElement> {
	public static final String NODE_ID = ColorRendererConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.ColorRendererElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.ColorRendererElement");
	private final ColorRendererConfigElement rendererElement;

	/**
	 * @param rendererElement
	 */
	public ColorRendererConfigElementNode(final ColorRendererConfigElement rendererElement) {
		super(ColorRendererConfigElementNode.NODE_ID);
		if (rendererElement == null) {
			throw new IllegalArgumentException("rendererElement is null");
		}
		this.rendererElement = rendererElement;
		setNodeLabel(ColorRendererConfigElementNode.NODE_LABEL);
		setNodeClass(ColorRendererConfigElementNode.NODE_CLASS);
		setNodeValue(new ColorRendererConfigElementNodeValue(rendererElement));
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public ColorRendererConfigElement getConfigElement() {
		return rendererElement;
	}

	protected void createChildNodes(final ColorRendererConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ColorRendererReferenceNode(ColorRendererConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class ColorRendererReferenceNode extends ConfigurableExtensionReferenceElementNode<ColorRendererExtensionConfig> {
		public static final String NODE_CLASS = "node.class.ColorRendererReference";

		/**
		 * @param nodeId
		 * @param filterElement
		 */
		public ColorRendererReferenceNode(final String nodeId, final ColorRendererConfigElement rendererElement) {
			super(nodeId, rendererElement.getExtensionElement());
			setNodeClass(ColorRendererReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<ColorRendererExtensionConfig> value) {
			// return new ColorRendererExtensionReferenceNodeValue(value != null ? value.clone() : null);
			return new ColorRendererExtensionReferenceNodeValue(value);
		}
	}

	// private ExtensionReference getReference() {
	// if ((getNodeValue() != null) && (getNodeValue().getValue() != null)) {
	// return ((ColorRendererConfigElementNodeValue) getNodeValue()).getValue().getReference();
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
	 * @return the rendererElement
	 */
	public ColorRendererConfigElement getRendererElement() {
		return rendererElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((ColorRendererConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new RendererNodeEditor(this);
	}

	private static class RendererNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public RendererNodeEditor(final Node node) {
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
