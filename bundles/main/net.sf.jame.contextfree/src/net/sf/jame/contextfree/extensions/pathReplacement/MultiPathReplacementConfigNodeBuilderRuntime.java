/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementNode;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementNode;
import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.core.common.IntegerElementNode;
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
public class MultiPathReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((MultiPathReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<MultiPathReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final MultiPathReplacementConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new TimesElementNode(getConfig()));
			parentNode.appendChildNode(new PathReplacementListElementNode(getConfig()));
			parentNode.appendChildNode(new PathAdjustmentListElementNode(getConfig()));
		}

		private class TimesElementNode extends IntegerElementNode {
			/**
			 * @param config
			 */
			public TimesElementNode(final MultiPathReplacementConfig config) {
				super(config.getExtensionId() + ".times", config.getTimesElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.TimesElement"));
			}
		}
		private class PathReplacementListElementNode extends AbstractConfigElementListNode<PathReplacementConfigElement> {
			public static final String NODE_CLASS = "node.class.PathReplacementListElement";
			
			/**
			 * @param config
			 */
			public PathReplacementListElementNode(final MultiPathReplacementConfig config) {
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
		private class PathAdjustmentListElementNode extends AbstractConfigElementListNode<PathAdjustmentConfigElement> {
			public static final String NODE_CLASS = "node.class.PathAdjustmentListElement";
			
			/**
			 * @param config
			 */
			public PathAdjustmentListElementNode(final MultiPathReplacementConfig config) {
				super(config.getExtensionId() + ".pathAdjustmentList", config.getPathAdjustmentListElement());
				setNodeClass(PathAdjustmentListElementNode.NODE_CLASS);
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.PathAdjustmentListElement"));
			}

			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createChildNode(net.sf.jame.core.config.ConfigElement)
			 */
			@Override
			protected AbstractConfigElementNode<PathAdjustmentConfigElement> createChildNode(final PathAdjustmentConfigElement value) {
				return new PathAdjustmentConfigElementNode(value);
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#getChildValueType()
			 */
			@Override
			public Class<?> getChildValueType() {
				return PathAdjustmentConfigElementNodeValue.class;
			}
	
			/**
			 * @see net.sf.jame.core.util.AbstractConfigElementListNode#createNodeValue(Object)
			 */
			@Override
			public NodeValue<PathAdjustmentConfigElement> createNodeValue(final Object value) {
				return new PathAdjustmentConfigElementNodeValue((PathAdjustmentConfigElement) value);
			}
	
			private class PathAdjustmentConfigElementNodeValue extends ConfigElementListNodeValue<PathAdjustmentConfigElement> {
				private static final long serialVersionUID = 1L;
	
				/**
				 * @param value
				 */
				public PathAdjustmentConfigElementNodeValue(final PathAdjustmentConfigElement value) {
					super(value);
				}
			}
		}
	}
}
