/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.core.common.FloatElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class Size3ShapeAdjustmentConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((Size3ShapeAdjustmentConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<Size3ShapeAdjustmentConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final Size3ShapeAdjustmentConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new ScaleXElementNode(getConfig()));
			parentNode.appendChildNode(new ScaleYElementNode(getConfig()));
			parentNode.appendChildNode(new ScaleZElementNode(getConfig()));
		}

		private class ScaleXElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ScaleXElementNode(final Size3ShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".scaleX", config.getScaleXElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.ScaleXElement"));
			}
		}
		private class ScaleYElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ScaleYElementNode(final Size3ShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".scaleY", config.getScaleYElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.ScaleYElement"));
			}
		}
		private class ScaleZElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ScaleZElementNode(final Size3ShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".scaleZ", config.getScaleZElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.ScaleZElement"));
			}
		}
	}
}
