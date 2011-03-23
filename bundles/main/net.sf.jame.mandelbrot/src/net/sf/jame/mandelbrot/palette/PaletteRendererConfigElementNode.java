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
package net.sf.jame.mandelbrot.palette;

import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.mandelbrot.MandelbrotResources;
import net.sf.jame.mandelbrot.palette.extension.PaletteRendererExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class PaletteRendererConfigElementNode extends AbstractConfigElementNode<PaletteRendererConfigElement> {
	public static final String NODE_ID = PaletteRendererConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.PaletteRendererElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.PaletteRendererElement");
	private final PaletteRendererConfigElement rendererElement;

	/**
	 * @param rendererElement
	 */
	public PaletteRendererConfigElementNode(final PaletteRendererConfigElement rendererElement) {
		super(PaletteRendererConfigElementNode.NODE_ID);
		if (rendererElement == null) {
			throw new IllegalArgumentException("rendererElement is null");
		}
		this.rendererElement = rendererElement;
		setNodeLabel(PaletteRendererConfigElementNode.NODE_LABEL);
		setNodeClass(PaletteRendererConfigElementNode.NODE_CLASS);
		setNodeValue(new PaletteRendererConfigElementNodeValue(rendererElement));
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PaletteRendererConfigElement getConfigElement() {
		return rendererElement;
	}

	protected void createChildNodes(final PaletteRendererConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new PaletteRendererReferenceNode(PaletteRendererConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class PaletteRendererReferenceNode extends ConfigurableExtensionReferenceElementNode<PaletteRendererExtensionConfig> {
		public static final String NODE_CLASS = "node.class.PaletteRendererReference";

		/**
		 * @param nodeId
		 * @param rendererElement
		 */
		public PaletteRendererReferenceNode(final String nodeId, final PaletteRendererConfigElement rendererElement) {
			super(nodeId, rendererElement.getExtensionElement());
			setNodeClass(PaletteRendererReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PaletteRendererExtensionConfig> value) {
			// return new PaletteRendererExtensionReferenceNodeValue(value != null ? value.clone() : null);
			return new PaletteRendererExtensionReferenceNodeValue(value);
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
	// return ((PaletteRendererConfigElementNodeValue) getNodeValue()).getValue().getReference();
	// }
	// return null;
	// }
	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((PaletteRendererConfigElementNodeValue) getNodeValue());
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
			// return new PaletteRendererConfigElementNodeValue((PaletteRendererConfigElement) value != null ? ((PaletteRendererConfigElement) value).clone() : null);
			return new PaletteRendererConfigElementNodeValue((PaletteRendererConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PaletteRendererConfigElementNodeValue.class;
		}
	}
}
