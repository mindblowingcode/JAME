/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class SingleReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((SingleReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<SingleReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final SingleReplacementConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new ShapeElementNode(getConfig()));
		}

		private class ShapeElementNode extends StringElementNode {
			/**
			 * @param config
			 */
			public ShapeElementNode(final SingleReplacementConfig config) {
				super(config.getExtensionId() + ".shape", config.getShapeElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.ShapeElement"));
			}
		}
	}
}
