/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.ContextFreeResources;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElementNode;
import net.sf.jame.core.common.StringElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

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
	}
}
