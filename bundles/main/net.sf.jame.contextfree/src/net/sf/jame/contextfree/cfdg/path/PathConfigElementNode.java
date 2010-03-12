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
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.path.operation.PathOperationConfigElement;
import net.sf.jame.contextfree.cfdg.path.operation.PathOperationConfigElementNode;
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
public class PathConfigElementNode extends AbstractConfigElementNode<PathConfigElement> {
	private static final String NODE_ID = PathConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.PathElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PathElement");
	private final PathConfigElement path;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param path the path element.
	 */
	public PathConfigElementNode(final PathConfigElement path) {
		super(PathConfigElementNode.NODE_ID);
		if (path == null) {
			throw new IllegalArgumentException("path is null");
		}
		this.path = path;
		setNodeLabel(PathConfigElementNode.NODE_LABEL);
		setNodeClass(PathConfigElementNode.NODE_CLASS);
		setNodeValue(new PathConfigElementNodeValue(path));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof PathConfigElementNode) {
			return (path == ((PathConfigElementNode) o).path);
		}
		return false;
	}

	/**
	 * @see net.sf.jame.core.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PathConfigElement getConfigElement() {
		return path;
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
		createChildNodes((PathConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new PathNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final PathConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new NameElementNode(PathConfigElementNode.NODE_ID + ".name", value.getValue()));
		appendChildNode(new PathOperationListElementNode(PathConfigElementNode.NODE_ID + ".pathOperationList", value.getValue()));
	}

	private static class PathNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public PathNodeEditor(final Node node) {
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
			return new PathConfigElementNodeValue((PathConfigElement) value);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PathConfigElementNodeValue.class;
		}
	}

	private static class NameElementNode extends StringElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.NameElement");

		/**
		 * @param nodeId
		 * @param path
		 */
		public NameElementNode(final String nodeId, final PathConfigElement path) {
			super(nodeId, path.getNameElement());
			setNodeLabel(NameElementNode.NODE_LABEL);
		}
	}

	private static class PathOperationListElementNode extends AbstractConfigElementListNode<PathOperationConfigElement> {
		public static final String NODE_CLASS = "node.class.PathOperationListElement";

		/**
		 * @param nodeId
		 * @param path
		 */
		public PathOperationListElementNode(final String nodeId, final PathConfigElement path) {
			super(nodeId, path.getPathOperationListElement());
			setNodeClass(PathOperationListElementNode.NODE_CLASS);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
		 */
		@Override
		protected AbstractConfigElementNode<PathOperationConfigElement> createChildNode(final PathOperationConfigElement value) {
			return new PathOperationConfigElementNode(value);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
		 */
		@Override
		public Class<?> getChildValueType() {
			return PathOperationConfigElementNodeValue.class;
		}

		/**
		 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
		 */
		@Override
		public NodeValue<PathOperationConfigElement> createNodeValue(final Object value) {
			return new PathOperationConfigElementNodeValue((PathOperationConfigElement) value);
		}

		private class PathOperationConfigElementNodeValue extends ConfigElementListNodeValue<PathOperationConfigElement> {
			private static final long serialVersionUID = 1L;

			/**
			 * @param value
			 */
			public PathOperationConfigElementNodeValue(final PathOperationConfigElement value) {
				super(value);
			}
		}
	}
}
