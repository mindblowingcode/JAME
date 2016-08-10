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
package net.sf.jame.core.util;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.SingleConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.tree.*;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractConfigElementSingleNode<T extends ConfigElement> extends DefaultNode {
	protected ConfigElementListener listListener;
	protected SingleConfigElement<T> singleElement;

	/**
	 * Constructs a new list node.
	 * 
	 * @param singleElement the frame element.
	 */
	public AbstractConfigElementSingleNode(final String nodeId, final SingleConfigElement<T> singleElement) {
		super(nodeId);
		if (singleElement == null) {
			throw new IllegalArgumentException("singleElement is null");
		}
		this.singleElement = singleElement;
		listListener = new ConfigElementListener();
	}

	/**
	 * @param value
	 * @return
	 */
	protected abstract AbstractConfigElementNode<T> createChildNode(T value);

	/**
	 * @see net.sf.jame.core.tree.Node#dispose()
	 */
	@Override
	public void dispose() {
		if (singleElement != null) {
			singleElement.removeChangeListener(listListener);
		}
		singleElement = null;
		listListener = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.tree.Node#setSession(net.sf.jame.core.tree.NodeSession)
	 */
	@Override
	public void setSession(final NodeSession session) {
		if (session != null) {
			singleElement.addChangeListener(listListener);
		}
		else {
			singleElement.removeChangeListener(listListener);
		}
		super.setSession(session);
	}

	/**
	 * @see net.sf.jame.core.tree.Node#nodeAdded()
	 */
	@Override
	protected void nodeAdded() {
		createChildNodes();
	}

	/**
	 * @see net.sf.jame.core.tree.Node#nodeRemoved()
	 */
	@Override
	protected void nodeRemoved() {
	}

	protected void createChildNodes() {
		createConfigElementNodes(singleElement);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean equals(final Object o) {
		if (o instanceof AbstractConfigElementSingleNode) {
			return (singleElement == ((AbstractConfigElementSingleNode) o).singleElement);
		}
		return false;
	}

	/**
	 * Creates the nodes.
	 * 
	 * @param singleElement the frame element.
	 */
	protected void createConfigElementNodes(final SingleConfigElement<T> singleElement) {
		final Node configElementNode = createChildNode(singleElement.getValue());
		appendChildNode(configElementNode);
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ListNodeEditor();
	}

	/**
	 * @param value
	 * @return
	 */
	public abstract NodeValue<?> createNodeValue(final Object value);

	protected class ListNodeEditor extends NodeEditor {
		/**
		 * 
		 */
		public ListNodeEditor() {
			super(AbstractConfigElementSingleNode.this);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createChildNode(net.sf.jame.core.tree.NodeValue)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected Node createChildNode(final NodeValue value) {
			final T configElement = ((NodeValue<T>) value).getValue();
			configElement.setContext(getContext());
			return AbstractConfigElementSingleNode.this.createChildNode(configElement);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ConfigElementListNodeValue.class;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public NodeValue createNodeValue(final Object value) {
			return AbstractConfigElementSingleNode.this.createNodeValue(value);
		}
	}

	protected class ConfigElementListener implements ValueChangeListener {
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case SingleConfigElement.VALUE_CHANGED: {
					setNodeValue(createNodeValue(createChildNode((T) e.getParams()[0])));
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_SET_VALUE, e.getTimestamp(), getNodePath(), e.getParams()[0], e.getParams()[1]));
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
