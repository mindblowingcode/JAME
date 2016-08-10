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
package net.sf.jame.twister.common;

import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.tree.DefaultNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeAction;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeSession;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.twister.util.View;

/**
 * @author Andrea Medeghini
 */
public class ViewElementNode extends DefaultNode {
	public static final String NODE_CLASS = "node.class.ViewElement";
	private final ConfigElementListener listener;
	private final ValueConfigElement<View> configElement;

	/**
	 * @param nodeId
	 */
	public ViewElementNode(final String nodeId, final ValueConfigElement<View> configElement) {
		super(nodeId);
		setNodeClass(ViewElementNode.NODE_CLASS);
		this.configElement = configElement;
		listener = new ConfigElementListener();
		// appendChildNode(new StatusNode());
		// appendChildNode(new PositionNode());
		// appendChildNode(new RotationNode());
		setNodeValue(new ViewElementNodeValue(configElement.getValue()));
	}

	/**
	 * @see net.sf.jame.core.tree.Node#isHighFrequency()
	 */
	@Override
	public boolean isHighFrequency() {
		return true;
	}

	/**
	 * @see net.sf.jame.core.tree.Node#dispose()
	 */
	@Override
	public void dispose() {
		if (configElement != null) {
			configElement.removeChangeListener(listener);
		}
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.tree.Node#setSession(net.sf.jame.core.tree.NodeSession)
	 */
	@Override
	public void setSession(final NodeSession session) {
		if (session != null) {
			configElement.addChangeListener(listener);
		}
		else {
			configElement.removeChangeListener(listener);
		}
		super.setSession(session);
	}

	/**
	 * @see net.sf.jame.core.tree.Node#nodeAdded()
	 */
	@Override
	protected void nodeAdded() {
	}

	/**
	 * @see net.sf.jame.core.tree.Node#nodeRemoved()
	 */
	@Override
	protected void nodeRemoved() {
	}

	/**
	 * @see net.sf.jame.core.tree.Node#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ViewNodeEditor(this);
	}

	/**
	 * @see net.sf.jame.core.tree.DefaultNode#getValueAsString()
	 */
	@Override
	public String getValueAsString() {
		return "";
	}

	// /**
	// * @see net.sf.jame.core.tree.Node#updateChildNodes()
	// */
	// @Override
	// protected void updateChildNodes() {
	// ((PositionNode) getChildNode(0)).update();
	// ((RotationNode) getChildNode(1)).update();
	// }
	protected class ViewNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public ViewNodeEditor(final Node node) {
			super(node);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#isRefreshRequired()
		 */
		@Override
		public boolean isRefreshRequired() {
			return false;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#doSetValue(java.lang.NodeValue)
		 */
		@Override
		protected void doSetValue(final NodeValue<?> value) {
			configElement.removeChangeListener(listener);
			configElement.setValue(((ViewElementNodeValue) value).getValue());
			configElement.addChangeListener(listener);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createChildNode(net.sf.jame.core.tree.NodeValue)
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
			return ViewElementNodeValue.class;
		}

		/**
		 * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new ViewElementNodeValue((View) value);
		}
	}

	protected class ConfigElementListener implements ValueChangeListener {
		public void valueChanged(final ValueChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setNodeValue(new ViewElementNodeValue((View) e.getParams()[0]));
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_SET_VALUE, e.getTimestamp(), false, getNodePath(), e.getParams()[0], e.getParams()[1]));
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	// private class PositionNode extends AttributeNode {
	// public static final String NODE_CLASS = "node.class.RotationElement";
	//
	// /**
	// * @param configElement
	// */
	// public PositionNode() {
	// super("attribute.position");
	// setNodeClass(PositionNode.NODE_CLASS);
	// setNodeLabel(TwisterResources.getInstance().getString("node.label.PositionElement"));
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.Node#isEditable()
	// */
	// @Override
	// public boolean isEditable() {
	// return false;
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.Node#getNodeValue()
	// */
	// @Override
	// public NodeValue<?> getNodeValue() {
	// return new ViewVector4DNodeValue(configElement.getValue().getPosition());
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.DefaultNode#getValueAsString()
	// */
	// @Override
	// public String getValueAsString() {
	// if (configElement.getValue() != null) {
	// return String.valueOf(configElement.getValue().getPosition());
	// }
	// else {
	// return "";
	// }
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	// */
	// @Override
	// protected NodeEditor createNodeEditor() {
	// return new ViewVector4DNodeEditor(this);
	// }
	//
	// /**
	// *
	// */
	// public void update() {
	// fireNodeChanged();
	// }
	// }
	//
	// private class RotationNode extends AttributeNode {
	// public static final String NODE_CLASS = "node.class.RotationElement";
	//
	// /**
	// * @param configElement
	// */
	// public RotationNode() {
	// super("attribute.rotation");
	// setNodeClass(RotationNode.NODE_CLASS);
	// setNodeLabel(TwisterResources.getInstance().getString("node.label.RotationElement"));
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.Node#isEditable()
	// */
	// @Override
	// public boolean isEditable() {
	// return false;
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.Node#getNodeValue()
	// */
	// @Override
	// public NodeValue<?> getNodeValue() {
	// return new ViewVector4DNodeValue(configElement.getValue().getRotation());
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.DefaultNode#getValueAsString()
	// */
	// @Override
	// public String getValueAsString() {
	// if (configElement.getValue() != null) {
	// return String.valueOf(configElement.getValue().getRotation());
	// }
	// else {
	// return "";
	// }
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
	// */
	// @Override
	// protected NodeEditor createNodeEditor() {
	// return new ViewVector4DNodeEditor(this);
	// }
	//
	// /**
	// *
	// */
	// public void update() {
	// fireNodeChanged();
	// }
	// }
	// protected class ViewVector4DNodeEditor extends NodeEditor {
	// /**
	// * @param node
	// */
	// public ViewVector4DNodeEditor(final Node node) {
	// super(node);
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.NodeEditor#createChildNode(net.sf.jame.core.tree.NodeValue)
	// */
	// @Override
	// protected Node createChildNode(final NodeValue<?> value) {
	// return null;
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.NodeEditor#getNodeValueType()
	// */
	// @Override
	// public Class<?> getNodeValueType() {
	// return ViewVector4DNodeValue.class;
	// }
	//
	// /**
	// * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
	// */
	// @Override
	// public NodeValue<?> createNodeValue(final Object value) {
	// return new ViewVector4DNodeValue((DoubleVector4D) value);
	// }
	// }
}
