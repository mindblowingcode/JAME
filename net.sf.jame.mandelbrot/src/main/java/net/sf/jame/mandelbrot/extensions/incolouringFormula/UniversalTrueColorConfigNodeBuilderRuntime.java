/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
package net.sf.jame.mandelbrot.extensions.incolouringFormula;

import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import net.sf.jame.core.tree.Node;
import net.sf.jame.core.tree.NodeBuilder;
import net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder;
import net.sf.jame.mandelbrot.colorRenderer.ColorRendererConfigElement;
import net.sf.jame.mandelbrot.colorRenderer.ColorRendererConfigElementNode;
import net.sf.jame.mandelbrot.extensions.MandelbrotExtensionResources;

/**
 * @author Andrea Medeghini
 */
public class UniversalTrueColorConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see net.sf.jame.core.nodeBuilder.extension.NodeBuilderExtensionRuntime#createNodeBuilder(net.sf.jame.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((UniversalTrueColorConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<UniversalTrueColorConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final UniversalTrueColorConfig config) {
			super(config);
		}

		/**
		 * @see net.sf.jame.core.util.AbstractExtensionConfigNodeBuilder#createNodes(net.sf.jame.core.tree.Node)
		 */
		@Override
		public void createNodes(final Node parentNode) {
			parentNode.appendChildNode(new ColorRendererNode(MandelbrotExtensionResources.getInstance().getString("node.label.RedColorRendererElement"), getConfig().getColorRendererElements()[0]));
			parentNode.appendChildNode(new ColorRendererNode(MandelbrotExtensionResources.getInstance().getString("node.label.GreenColorRendererElement"), getConfig().getColorRendererElements()[1]));
			parentNode.appendChildNode(new ColorRendererNode(MandelbrotExtensionResources.getInstance().getString("node.label.BlueColorRendererElement"), getConfig().getColorRendererElements()[2]));
			parentNode.appendChildNode(new ColorRendererNode(MandelbrotExtensionResources.getInstance().getString("node.label.AlphaColorRendererElement"), getConfig().getColorRendererElements()[3]));
		}
	}

	private class ColorRendererNode extends ColorRendererConfigElementNode {
		/**
		 * @param label
		 * @param rendererElement
		 */
		public ColorRendererNode(final String label, final ColorRendererConfigElement rendererElement) {
			super(rendererElement);
			setNodeLabel(label);
		}
	}
}
