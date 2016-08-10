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
package net.sf.jame.mandelbrot.extensions.transformingFormula;

import net.sf.jame.core.math.Complex;

/**
 * @author Andrea Medeghini
 */
public class URuntime extends AbstractTransformingFormulaRuntime<UConfig> {
	/**
	 * @see net.sf.jame.mandelbrot.transformingFormula.extension.TransformingFormulaExtensionRuntime#isHorizontalSymetryAllowed()
	 */
	@Override
	public boolean isHorizontalSymetryAllowed() {
		return true;
	}

	/**
	 * @see net.sf.jame.mandelbrot.transformingFormula.extension.TransformingFormulaExtensionRuntime#isVerticalSymetryAllowed()
	 */
	@Override
	public boolean isVerticalSymetryAllowed() {
		return true;
	}

	/**
	 * @see net.sf.jame.mandelbrot.transformingFormula.extension.TransformingFormulaExtensionRuntime#renderPoint(net.sf.jame.core.math.Complex)
	 */
	@Override
	public Complex renderPoint(final Complex w) {
		final double d = w.r * w.r + w.i * w.i;
		if (Math.abs(d) > 0.000000001) {
			final double m = 1 / ((w.r * w.r) + (w.i * w.i));
			w.r = +w.r * m;
			w.i = -w.i * m;
		}
		else {
			w.r = 1000000000000000.0;
			w.i = 0;
		}
		return w;
	}
}
