/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.mandelbrot.extensions.renderingFormula;

import net.sf.jame.core.common.ComplexElementNode;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;
import net.sf.jame.mandelbrot.common.IterationsElementNode;
import net.sf.jame.mandelbrot.common.ThresholdElementNode;
import net.sf.jame.mandelbrot.extensions.MandelbrotExtensionResources;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractRenderingFormulaConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((RenderingFormulaExtensionConfig) config);
	}

	/**
	 * @param parentNode
	 */
	public void createNodes(final Node parentNode, final RenderingFormulaExtensionConfig config) {
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<RenderingFormulaExtensionConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final RenderingFormulaExtensionConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.tree.NodeBuilder#createNodes(Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new IterationsNode(getConfig()));
			parentNode.appendChildNode(new ThresholdNode(getConfig()));
			parentNode.appendChildNode(new CenterNode(getConfig()));
			parentNode.appendChildNode(new ScaleNode(getConfig()));
			AbstractRenderingFormulaConfigNodeBuilderRuntime.this.createNodes(parentNode, getConfig());
		}

		protected class IterationsNode extends IterationsElementNode {
			/**
			 * @param config
			 */
			public IterationsNode(final RenderingFormulaExtensionConfig config) {
				super(config.getExtensionId() + ".iterations", config.getIterationsElement());
			}
		}

		private class ThresholdNode extends ThresholdElementNode {
			/**
			 * @param config
			 */
			public ThresholdNode(final RenderingFormulaExtensionConfig config) {
				super(config.getExtensionId() + ".threshold", config.getThresholdElement());
			}
		}

		private class CenterNode extends ComplexElementNode {
			/**
			 * @param config
			 */
			public CenterNode(final RenderingFormulaExtensionConfig config) {
				super(config.getExtensionId() + ".center", config.getCenterElement());
				setNodeLabel(MandelbrotExtensionResources.getInstance().getString("node.label.CenterElement"));
			}
		}

		private class ScaleNode extends ComplexElementNode {
			/**
			 * @param config
			 */
			public ScaleNode(final RenderingFormulaExtensionConfig config) {
				super(config.getExtensionId() + ".scale", config.getScaleElement());
				setNodeLabel(MandelbrotExtensionResources.getInstance().getString("node.label.ScaleElement"));
			}
		}
	}
}
