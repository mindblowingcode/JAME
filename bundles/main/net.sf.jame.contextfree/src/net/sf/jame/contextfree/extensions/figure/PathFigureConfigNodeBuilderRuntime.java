/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElement;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.NodeValue;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractConfigElementListNode;
import net.sf.jame.core.util.AbstractConfigElementNode;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;
import net.sf.jame.core.util.ConfigElementListNodeValue;

/**
 * @author Andrea Medeghini
 */
public class PathFigureConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((PathFigureConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<PathFigureConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final PathFigureConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new NameElementNode(getConfig()));
			parentNode.appendChildNode(new PathOperationListElementNode(getConfig()));
		}

		private class NameElementNode extends StringElementNode {
			/**
			 * @param config
			 */
			public NameElementNode(final PathFigureConfig config) {
				super(config.getExtensionId() + ".name", config.getNameElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.NameElement"));
			}
		}
		private class PathOperationListElementNode extends AbstractConfigElementListNode<PathOperationConfigElement> {
			public static final String NODE_CLASS = "node.class.PathOperationListElement";
			
			/**
			 * @param config
			 */
			public PathOperationListElementNode(final PathFigureConfig config) {
				super(config.getExtensionId() + ".pathOperations", config.getPathOperationListElement());
				setNodeClass(PathOperationListElementNode.NODE_CLASS);
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.PathOperationListElement"));
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
}
