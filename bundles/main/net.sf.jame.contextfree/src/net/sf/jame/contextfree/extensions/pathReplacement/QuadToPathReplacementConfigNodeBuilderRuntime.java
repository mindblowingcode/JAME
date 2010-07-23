/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

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
public class QuadToPathReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.tree.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((QuadToPathReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<QuadToPathReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final QuadToPathReplacementConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new XElementNode(getConfig()));
			parentNode.appendChildNode(new YElementNode(getConfig()));
			parentNode.appendChildNode(new X1ElementNode(getConfig()));
			parentNode.appendChildNode(new Y1ElementNode(getConfig()));
		}

		private class XElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public XElementNode(final QuadToPathReplacementConfig config) {
				super(config.getExtensionId() + ".x", config.getXElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.XElement"));
			}
		}
		private class YElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public YElementNode(final QuadToPathReplacementConfig config) {
				super(config.getExtensionId() + ".y", config.getYElement());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.YElement"));
			}
		}
		private class X1ElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public X1ElementNode(final QuadToPathReplacementConfig config) {
				super(config.getExtensionId() + ".x1", config.getX1Element());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.X1Element"));
			}
		}
		private class Y1ElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public Y1ElementNode(final QuadToPathReplacementConfig config) {
				super(config.getExtensionId() + ".y1", config.getY1Element());
				setNodeLabel(ContextFreeResources.getInstance().getString("node.label.Y1Element"));
			}
		}
	}
}
