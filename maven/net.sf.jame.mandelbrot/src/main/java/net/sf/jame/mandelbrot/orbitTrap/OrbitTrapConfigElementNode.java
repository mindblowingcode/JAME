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
package net.sf.jame.mandelbrot.orbitTrap;

import net.sf.jame.core.common.ComplexElementNode;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.mandelbrot.MandelbrotResources;
import net.sf.jame.mandelbrot.orbitTrap.extension.OrbitTrapExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class OrbitTrapConfigElementNode extends AbstractConfigElementNode<OrbitTrapConfigElement> {
	public static final String NODE_ID = OrbitTrapConfigElement.CLASS_ID;
	public static final String NODE_CLASS = "node.class.OrbitTrapElement";
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.OrbitTrapElement");
	private final OrbitTrapConfigElement trapElement;

	/**
	 * @param trapElement
	 */
	public OrbitTrapConfigElementNode(final OrbitTrapConfigElement trapElement) {
		super(OrbitTrapConfigElementNode.NODE_ID);
		if (trapElement == null) {
			throw new IllegalArgumentException("trapElement is null");
		}
		this.trapElement = trapElement;
		setNodeLabel(OrbitTrapConfigElementNode.NODE_LABEL);
		setNodeClass(OrbitTrapConfigElementNode.NODE_CLASS);
		setNodeValue(new OrbitTrapConfigElementNodeValue(trapElement));
	}

	protected void createChildNodes(final OrbitTrapConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new OrbitTrapReferenceNode(OrbitTrapConfigElementNode.NODE_ID + ".extension", value.getValue()));
		appendChildNode(new OrbitTrapCenterNode(OrbitTrapConfigElementNode.NODE_ID + ".center", value.getValue()));
	}

	private static class OrbitTrapReferenceNode extends ConfigurableExtensionReferenceElementNode<OrbitTrapExtensionConfig> {
		public static final String NODE_CLASS = "node.class.OrbitTrapReference";

		/**
		 * @param nodeId
		 * @param trapElement
		 */
		public OrbitTrapReferenceNode(final String nodeId, final OrbitTrapConfigElement trapElement) {
			super(nodeId, trapElement.getExtensionElement());
			setNodeClass(OrbitTrapReferenceNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementNode#createNodeValue(net.sf.jame.core.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<OrbitTrapExtensionConfig> value) {
			return new OrbitTrapExtensionReferenceNodeValue(value);
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
	public OrbitTrapConfigElement getConfigElement() {
		return trapElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateChildNodes()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((OrbitTrapConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new TrapNodeEditor(this);
	}

	private static class TrapNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public TrapNodeEditor(final Node node) {
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
			return new OrbitTrapConfigElementNodeValue((OrbitTrapConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return OrbitTrapConfigElementNodeValue.class;
		}
	}

	private static class OrbitTrapCenterNode extends ComplexElementNode {
		private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.CenterElement");

		/**
		 * @param nodeId
		 * @param trapElement
		 */
		public OrbitTrapCenterNode(final String nodeId, final OrbitTrapConfigElement trapElement) {
			super(nodeId, trapElement.getCenterElement());
			setNodeLabel(OrbitTrapCenterNode.NODE_LABEL);
		}
	}
}
