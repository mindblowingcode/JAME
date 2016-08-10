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
package net.sf.jame.mandelbrot.extensions.constructor;

import net.sf.jame.core.constructor.extension.ConstructorExtensionRuntime;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.scripting.JSException;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.util.RenderedPaletteParam;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteParamConstructorRuntime extends ConstructorExtensionRuntime {
	/**
	 * @see net.sf.jame.core.constructor.extension.ConstructorExtensionRuntime#create(java.lang.Object[])
	 */
	@Override
	public Object create(final Object... args) throws JSException {
		try {
			ExtensionReference[] formulas = new ExtensionReference[4];
			formulas[0] = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension((String) args[0]).getExtensionReference();
			formulas[1] = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension((String) args[1]).getExtensionReference();
			formulas[2] = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension((String) args[2]).getExtensionReference();
			formulas[3] = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension((String) args[3]).getExtensionReference();
			int[] colors = new int[2];
			colors[0] = Color32bit.valueOf((String) args[4]).getARGB();
			colors[1] = Color32bit.valueOf((String) args[5]).getARGB();
			double size = toDouble(args[6]);
			return new RenderedPaletteParam(formulas, colors, size);
		}
		catch (Exception e) {
			throw new JSException("RenderedPaletteParam constructor requires seven arguments: #1 of type String, #2 of type String, #3 of type String, #4 of type String, #5 of type String, #6 of type String, #7 of type Number", e);
		}
	}
}
