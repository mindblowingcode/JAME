/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.extensions.ContextFreeExtensionResources;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.AttributeNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.tree.NodeEditor;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class TriangleFigureConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((TriangleFigureConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<TriangleFigureConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final TriangleFigureConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new NameElementNode(getConfig()));
		}

		private class NameElementNode extends AttributeNode {
			/**
			 * @param config
			 */
			public NameElementNode(final TriangleFigureConfig config) {
				super(config.getExtensionId() + ".name");
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.NameElement"));
			}

			@Override
			protected NodeEditor createNodeEditor() {
				return null;
			}

			@Override
			public String getValueAsString() {
				return "TRIANGLE";
			}
		}
	}
}
