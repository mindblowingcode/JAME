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
package net.sf.jame.core.common;

import net.sf.jame.core.CoreResources;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.tree.*;

/**
 * @author Andrea Medeghini
 */
public abstract class ExtensionReferenceElementNode extends DefaultNode {
	protected ConfigElementListener elementListener;
	protected ExtensionReferenceElement referenceElement;

	/**
	 * Constructs a new filter node.
	 * 
	 * @param nodeId
	 * @param referenceElement
	 */
	public ExtensionReferenceElementNode(final String nodeId, final ExtensionReferenceElement referenceElement) {
		super(nodeId);
		if (referenceElement == null) {
			throw new IllegalArgumentException("referenceElement is null");
		}
		this.referenceElement = referenceElement;
		elementListener = new ConfigElementListener();
		setNodeValue(createNodeValue(referenceElement.getReference()));
		setNodeLabel(createNodeLabel());
		if (referenceElement.getReference() != null) {
			setExtensionId(referenceElement.getReference().getExtensionId());
		}
	}

	/**
	 * @param value
	 * @return
	 */
	protected abstract NodeValue<?> createNodeValue(ExtensionReference value);

	/**
	 * @see net.sf.jame.core.tree.Node#dispose()
	 */
	@Override
	public void dispose() {
		if (referenceElement != null) {
			referenceElement.removeChangeListener(elementListener);
		}
		referenceElement = null;
		elementListener = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.tree.Node#setSession(net.sf.jame.core.tree.NodeSession)
	 */
	@Override
	public void setSession(final NodeSession session) {
		if (session != null) {
			referenceElement.addChangeListener(elementListener);
		}
		else {
			referenceElement.removeChangeListener(elementListener);
		}
		super.setSession(session);
	}

	/**
	 * @see net.sf.jame.core.tree.Node#nodeAdded()
	 */
	@Override
	protected void nodeAdded() {
		setNodeValue(createNodeValue(referenceElement.getReference()));
	}

	/**
	 * @see net.sf.jame.core.tree.Node#nodeRemoved()
	 */
	@Override
	protected void nodeRemoved() {
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof ExtensionReferenceElementNode) {
			return (referenceElement == ((ExtensionReferenceElementNode) o).referenceElement);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private ExtensionReference getReference() {
		if ((getNodeValue() != null) && (getNodeValue().getValue() != null)) {
			return ((ExtensionReferenceElementNodeValue<ExtensionReference>) getNodeValue()).getValue();
		}
		return null;
	}

	private String createNodeLabel() {
		final ExtensionReference reference = getReference();
		if (reference != null) {
			return reference.getExtensionName() + " extension";
		}
		else {
			return "Extension not defined";
		}
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
	 * @return the filterElement
	 */
	public ExtensionReferenceElement getReferenceElement() {
		return referenceElement;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateNode()
	 */
	@Override
	protected void updateNode() {
		setNodeLabel(createNodeLabel());
		if (referenceElement.getReference() != null) {
			setExtensionId(referenceElement.getReference().getExtensionId());
		}
		super.updateNode();
	}

	/**
	 * @see net.sf.jame.core.tree.Node#updateNode()
	 */
	@Override
	protected void updateChildNodes() {
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ConfigElementNodeEditor(this);
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#getValueAsString()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getValueAsString() {
		return ((getNodeValue() != null) && (getNodeValue().getValue() != null)) ? ((ExtensionReferenceElementNodeValue<ExtensionReference>) getNodeValue()).getValue().getExtensionName() : CoreResources.getInstance().getString("label.undefined");
	}

	protected class ConfigElementNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public ConfigElementNodeEditor(final DefaultNode node) {
			super(node);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#doSetValue(net.sf.jame.core.tree.NodeValue)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void doSetValue(final NodeValue value) {
			referenceElement.removeChangeListener(elementListener);
			referenceElement.setReference(((ExtensionReferenceElementNodeValue<ExtensionReference>) value).getValue());
			referenceElement.addChangeListener(elementListener);
			setNodeLabel(createNodeLabel());
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createChildNode(java.lang.NodeValue)
		 */
		@Override
		protected Node createChildNode(final NodeValue<?> value) {
			return null;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ExtensionReferenceElementNodeValue.class;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return ExtensionReferenceElementNode.this.createNodeValue((ExtensionReference) value);
		}
	}

	protected class ConfigElementListener implements ValueChangeListener {
		public void valueChanged(final ValueChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					setNodeValue(new ExtensionReferenceElementNodeValue<ExtensionReference>((ExtensionReference) e.getParams()[0]));
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_SET_VALUE, e.getTimestamp(), getNodePath(), e.getParams()[0], e.getParams()[1]));
					break;
				}
				default: {
					break;
				}
			}
			setNodeLabel(createNodeLabel());
		}
	}
}
