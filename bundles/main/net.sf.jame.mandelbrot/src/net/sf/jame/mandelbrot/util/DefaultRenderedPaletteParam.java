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
package net.sf.jame.mandelbrot.util;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.mandelbrot.MandelbrotRegistry;
import net.sf.jame.mandelbrot.palette.formula.extension.PaletteRendererFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class DefaultRenderedPaletteParam extends RenderedPaletteParam {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PALETTE_RENDERER_FORMULA_EXTENSION_ID = "twister.mandelbrot.palette.renderer.formula.lin";

	/**
	 * @param size
	 */
	public DefaultRenderedPaletteParam(final double size) {
		super(DefaultRenderedPaletteParam.buildFormulas(), new int[] { 0xFF000000, 0xFFFFFFFF }, size);
	}

	/**
	 * @param colors
	 * @param size
	 */
	public DefaultRenderedPaletteParam(final int[] colors, final double size) {
		super(DefaultRenderedPaletteParam.buildFormulas(), colors, size);
	}

	/**
	 * @return
	 * @throws Error
	 */
	private static ExtensionReference[] buildFormulas() throws Error {
		final ExtensionReference[] formulas = new ExtensionReference[4];
		try {
			final Extension<PaletteRendererFormulaExtensionRuntime> extension = MandelbrotRegistry.getInstance().getPaletteRendererFormulaExtension(DefaultRenderedPaletteParam.DEFAULT_PALETTE_RENDERER_FORMULA_EXTENSION_ID);
			formulas[0] = extension.getExtensionReference();
			formulas[1] = extension.getExtensionReference();
			formulas[2] = extension.getExtensionReference();
			formulas[3] = extension.getExtensionReference();
		}
		catch (final Exception e) {
			throw new Error(e);
		}
		return formulas;
	}
}
