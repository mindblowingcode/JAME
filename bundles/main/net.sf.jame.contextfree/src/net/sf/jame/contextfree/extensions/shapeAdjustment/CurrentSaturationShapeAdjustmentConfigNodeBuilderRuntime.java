/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.core.common.BooleanElementNode;
import net.sf.jame.core.common.FloatElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class CurrentSaturationShapeAdjustmentConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((CurrentSaturationShapeAdjustmentConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<CurrentSaturationShapeAdjustmentConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final CurrentSaturationShapeAdjustmentConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new ValueElementNode(getConfig()));
			parentNode.appendChildNode(new TargetElementNode(getConfig()));
		}

		private class ValueElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ValueElementNode(final CurrentSaturationShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".value", config.getValueElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ValueElement"));
			}
		}
		private class TargetElementNode extends BooleanElementNode {
			/**
			 * @param config
			 */
			public TargetElementNode(final CurrentSaturationShapeAdjustmentConfig config) {
				super(config.getExtensionId() + ".target", config.getTargetElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.TargetElement"));
			}
		}
	}
}
