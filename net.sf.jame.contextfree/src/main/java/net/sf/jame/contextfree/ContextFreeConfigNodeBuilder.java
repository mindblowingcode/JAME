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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.CFDGConfigElementNode;
import net.sf.jame.contextfree.cfdg.CFDGConfigElementNodeValue;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeAction;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.twister.common.SpeedElementNode;
import net.sf.jame.twister.common.ViewElementNode;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigNodeBuilder implements NodeBuilder {
	private final ContextFreeConfig config;

	/**
	 * Constructs a new tree builder.
	 * 
	 * @param config
	 *        the config.
	 */
	public ContextFreeConfigNodeBuilder(final ContextFreeConfig config) {
		this.config = config;
	}

	/**
	 * Creates the nodes.
	 * 
	 * @param parentNode
	 */
	public void createNodes(final Node parentNode) {
//		parentNode.appendChildNode(new ViewNode(config));
//		parentNode.appendChildNode(new SpeedNode(config));
		parentNode.appendChildNode(new CFDGElementNode(config.getCFDG()));
	}

	private static class SpeedNode extends SpeedElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.SpeedElement");

		/**
		 * @param config
		 */
		public SpeedNode(final ContextFreeConfig config) {
			super("attribute.speed", config.getSpeedElement());
			setNodeLabel(SpeedNode.NODE_LABEL);
		}
	}

	private static class ViewNode extends ViewElementNode {
		private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.ViewElement");

		/**
		 * @param config
		 */
		public ViewNode(final ContextFreeConfig config) {
			super("attribute.view", config.getViewElement());
			setNodeLabel(ViewNode.NODE_LABEL);
		}
	}

	private class CFDGElementNode extends CFDGConfigElementNode {
		private final ConfigListener listener;

		/**
		 * @param frameElement
		 */
		public CFDGElementNode(final CFDGConfigElement imgeElement) {
			super(imgeElement);
			listener = new ConfigListener();
		}

		/**
		 * @see net.sf.jame.core.tree.Node#isEditable()
		 */
		@Override
		public boolean isEditable() {
			return true;
		}

		/**
		 * @see net.sf.jame.core.tree.Node#dispose()
		 */
		@Override
		public void dispose() {
			if (config.getCFDGSingleElement() != null) {
				config.getCFDGSingleElement().removeChangeListener(listener);
			}
			super.dispose();
		}

		/**
		 * @see net.sf.jame.core.tree.Node#nodeAdded()
		 */
		@Override
		protected void nodeAdded() {
			setNodeValue(new CFDGConfigElementNodeValue(getConfigElement()));
			config.getCFDGSingleElement().addChangeListener(listener);
		}

		/**
		 * @see net.sf.jame.core.tree.Node#nodeRemoved()
		 */
		@Override
		protected void nodeRemoved() {
			config.getCFDGSingleElement().removeChangeListener(listener);
		}

		/**
		 * @see net.sf.jame.core.tree.DefaultNode#createNodeEditor()
		 */
		@Override
		protected NodeEditor createNodeEditor() {
			return new CFDGNodeEditor(this);
		}

		protected class CFDGNodeEditor extends NodeEditor {
			/**
			 * @param node
			 */
			public CFDGNodeEditor(final Node node) {
				super(node);
			}

			/**
			 * @see net.sf.jame.core.tree.NodeEditor#doSetValue(java.lang.NodeValue)
			 */
			@Override
			protected void doSetValue(final NodeValue<?> value) {
				config.getCFDGSingleElement().removeChangeListener(listener);
				config.setCFDG(((CFDGConfigElementNodeValue) value).getValue());
				config.getCFDGSingleElement().addChangeListener(listener);
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
				return CFDGConfigElementNodeValue.class;
			}

			/**
			 * @see net.sf.jame.core.tree.NodeEditor#createNodeValue(Object)
			 */
			@Override
			public NodeValue<?> createNodeValue(final Object value) {
				// return new CFDGConfigElementNodeValue((CFDGConfigElement) value != null ? ((CFDGConfigElement) value).clone() : null);
				return new CFDGConfigElementNodeValue((CFDGConfigElement) value);
			}
		}

		protected class ConfigListener implements ValueChangeListener {
			public void valueChanged(final ValueChangeEvent e) {
				cancel();
				switch (e.getEventType()) {
					case ValueConfigElement.VALUE_CHANGED: {
						setNodeValue(new CFDGConfigElementNodeValue((CFDGConfigElement) e.getParams()[0]));
						getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_SET_VALUE, e.getTimestamp(), getNodePath(), e.getParams()[0] != null ? ((CFDGConfigElement) e.getParams()[0]).clone() : null, e.getParams()[1] != null ? ((CFDGConfigElement) e.getParams()[1]).clone() : null));
						break;
					}
					default: {
						break;
					}
				}
			}
		}
	}
}
