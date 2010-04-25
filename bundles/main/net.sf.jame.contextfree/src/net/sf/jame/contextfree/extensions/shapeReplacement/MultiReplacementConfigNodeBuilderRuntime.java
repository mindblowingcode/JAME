/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementNode;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementNode;
import net.sf.jame.core.common.IntegerElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class MultiReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((MultiReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<MultiReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final MultiReplacementConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new TimesElementNode(getConfig()));
		}

		private class TimesElementNode extends IntegerElementNode {
			/**
			 * @param config
			 */
			public TimesElementNode(final MultiReplacementConfig config) {
				super(config.getExtensionId() + ".times", config.getTimesElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.TimesElement"));
			}
		}
	}
}
