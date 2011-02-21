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
package net.sf.jame.mandelbrot.extensions.palette.formula;

import net.sf.jame.mandelbrot.palette.formula.extension.PaletteRendererFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class COSRuntime extends PaletteRendererFormulaExtensionRuntime {
	private static final double CONSTANT_PI2 = 0.5d * Math.PI;

	/**
	 * @see net.sf.jame.mandelbrot.palette.formula.extension.PaletteRendererFormulaExtensionRuntime#renderPalette(int)
	 */
	@Override
	public double[] renderPalette(final int size) {
		final double[] table = new double[size];
		final double delta = COSRuntime.CONSTANT_PI2 / size;
		double value = 0;
		for (int i = 0; i < size; i++) {
			table[i] = 1d - Math.cos(value);
			value += delta;
		}
		return table;
	}
}
