/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.core.common.FloatElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class SkewShapeAdjustmentConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((SkewShapeAdjustmentConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<SkewShapeAdjustmentConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final SkewShapeAdjustmentConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new ShearXElementNode(getConfig()));
			parentNode.appendChildNode(new ShearYElementNode(getConfig()));
		}

		private class ShearXElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ShearXElementNode(final SkewShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".shearX", config.getShearXElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ShearXElement"));
			}
		}
		private class ShearYElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ShearYElementNode(final SkewShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".shearY", config.getShearYElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ShearYElement"));
			}
		}
	}
}
