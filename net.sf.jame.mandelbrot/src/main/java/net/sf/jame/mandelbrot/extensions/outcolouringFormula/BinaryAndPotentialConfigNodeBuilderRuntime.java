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
package net.sf.jame.mandelbrot.extensions.outcolouringFormula;

import net.sf.jame.core.tree.Node;
import net.sf.jame.mandelbrot.extensions.MandelbrotExtensionResources;
import net.sf.jame.twister.common.PercentageElementNode;

/**
 * @author Andrea Medeghini
 */
public class BinaryAndPotentialConfigNodeBuilderRuntime extends AbstractOutcolouringPaletteConfigNodeBuilderRuntime {
	/**
	 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteConfigNodeBuilderRuntime#createNodes(net.sf.jame.core.tree.Node, net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteConfig)
	 */
	@Override
	public void createNodes(final Node parentNode, final AbstractOutcolouringPaletteConfig config) {
		parentNode.appendChildNode(new OffsetElementNode((BinaryAndPotentialConfig) config));
	}

	private static class OffsetElementNode extends PercentageElementNode {
		public static final String NODE_LABEL = MandelbrotExtensionResources.getInstance().getString("node.label.OffsetElement");

		/**
		 * @param config
		 */
		public OffsetElementNode(final BinaryAndPotentialConfig config) {
			super(config.getExtensionId() + ".offset", config.getOffsetElement());
			setNodeLabel(OffsetElementNode.NODE_LABEL);
		}
	}
}
