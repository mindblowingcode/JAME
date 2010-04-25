/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementNode;
import net.sf.jame.core.common.FloatElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class RuleFigureConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((RuleFigureConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<RuleFigureConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final RuleFigureConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new NameElementNode(getConfig()));
			parentNode.appendChildNode(new PropabilityElementNode(getConfig()));
		}

		private class NameElementNode extends StringElementNode {
			/**
			 * @param config
			 */
			public NameElementNode(final RuleFigureConfig config) {
				super(config.getExtensionId() + ".name", config.getNameElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.NameElement"));
			}
		}
		private class PropabilityElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public PropabilityElementNode(final RuleFigureConfig config) {
				super(config.getExtensionId() + ".propability", config.getPropabilityElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.PropabilityElement"));
			}
		}
	}
}
