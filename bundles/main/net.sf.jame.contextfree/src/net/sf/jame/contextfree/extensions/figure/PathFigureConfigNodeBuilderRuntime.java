/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementNode;
import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
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
			parentNode.appendChildNode(new PathReplacementListElementNode(getConfig()));
		}

		private class NameElementNode extends StringElementNode {
			/**
			 * @param config
			 */
			public NameElementNode(final PathFigureConfig config) {
				super(config.getExtensionId() + ".name", config.getNameElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.NameElement"));
			}
		}
		private class PathReplacementListElementNode extends AbstractConfigElementListNode<PathReplacementConfigElement> {
			public static final String NODE_CLASS = "node.class.PathReplacementListElement";
			
			/**
			 * @param config
			 */
			public PathReplacementListElementNode(final PathFigureConfig config) {
				super(config.getExtensionId() + ".pathReplacementList", config.getPathReplacementListElement());
				setNodeClass(PathReplacementListElementNode.NODE_CLASS);
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.PathReplacementListElement"));
			}

			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
			 */
			@Override
			protected AbstractConfigElementNode<PathReplacementConfigElement> createChildNode(final PathReplacementConfigElement value) {
				return new PathReplacementConfigElementNode(value);
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
			 */
			@Override
			public Class<?> getChildValueType() {
				return PathReplacementConfigElementNodeValue.class;
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
			 */
			@Override
			public NodeValue<PathReplacementConfigElement> createNodeValue(final Object value) {
				return new PathReplacementConfigElementNodeValue((PathReplacementConfigElement) value);
			}
	
			private class PathReplacementConfigElementNodeValue extends ConfigElementListNodeValue<PathReplacementConfigElement> {
				private static final long serialVersionUID = 1L;
	
				/**
				 * @param value
				 */
				public PathReplacementConfigElementNodeValue(final PathReplacementConfigElement value) {
					super(value);
				}
			}
		}
	}
}
