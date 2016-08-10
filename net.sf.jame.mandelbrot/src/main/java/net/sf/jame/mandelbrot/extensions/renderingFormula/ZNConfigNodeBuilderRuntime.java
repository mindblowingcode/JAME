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
package net.sf.jame.mandelbrot.extensions.renderingFormula;

import net.sf.jame.core.tree.Node;
import net.sf.jame.mandelbrot.common.ExponentElementNode;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ZNConfigNodeBuilderRuntime extends AbstractRenderingFormulaConfigNodeBuilderRuntime {
	/**
	 * @see net.sf.jame.mandelbrot.extensions.renderingFormula.AbstractRenderingFormulaConfigNodeBuilderRuntime#createNodes(net.sf.jame.core.tree.Node, net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig)
	 */
	@Override
	public void createNodes(final Node parentNode, final RenderingFormulaExtensionConfig config) {
		parentNode.appendChildNode(new ExponentNode((ZNConfig) config));
	}

	private class ExponentNode extends ExponentElementNode {
		/**
		 * @param config
		 */
		public ExponentNode(final ZNConfig config) {
			super(config.getExtensionId() + ".exponent", config.getExponentElement());
		}
	}
}
