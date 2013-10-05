/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
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

import net.sf.jame.core.common.DoubleElementNode;
import net.sf.jame.core.tree.Node;
import net.sf.jame.mandelbrot.extensions.MandelbrotExtensionResources;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ZAConfigNodeBuilderRuntime extends AbstractRenderingFormulaConfigNodeBuilderRuntime {
	/**
	 * @see net.sf.jame.mandelbrot.extensions.renderingFormula.AbstractRenderingFormulaConfigNodeBuilderRuntime#createNodes(net.sf.jame.core.tree.Node, net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig)
	 */
	@Override
	public void createNodes(final Node parentNode, final RenderingFormulaExtensionConfig config) {
		parentNode.appendChildNode(new ExponentNode((ZAConfig) config));
	}

	private class ExponentNode extends DoubleElementNode {
		/**
		 * @param config
		 */
		public ExponentNode(final ZAConfig config) {
			super(config.getExtensionId() + ".exponent", config.getExponentElement());
			setNodeLabel(MandelbrotExtensionResources.getInstance().getString("node.label.ExponentElement"));
		}
	}
}
