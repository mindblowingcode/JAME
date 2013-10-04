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
package net.sf.jame.mandelbrot.extensions.processingFormula;

import net.sf.jame.core.math.Complex;
import net.sf.jame.mandelbrot.processingFormula.extension.ProcessingFormulaExtensionRuntime;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;

/**
 * @author Andrea Medeghini
 */
public class AverageFormulaRuntime extends ProcessingFormulaExtensionRuntime {
	private final Complex z = new Complex();
	private double sumMod;
	private double sumArg;
	private double avgMod;
	private double avgArg;
	private int k;
	private double m;
	private double c;

	/**
	 * @see net.sf.jame.mandelbrot.processingFormula.extension.ProcessingFormulaExtensionRuntime#prepareForProcessing()
	 */
	@Override
	public void prepareForProcessing() {
		k = 0;
		z.r = 0;
		z.i = 0;
		sumMod = 0;
		sumArg = 0;
		avgMod = 0;
		avgArg = 0;
	}

	/**
	 * @see net.sf.jame.mandelbrot.processingFormula.extension.ProcessingFormulaExtensionRuntime#processPoint(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public void processPoint(final RenderedPoint cp) {
		k += 1;
		z.r = cp.zr;
		z.i = cp.zi;
		m = Complex.mod(z);
		c = Math.abs(Complex.arg(z));
		sumMod += m;
		sumArg += c;
	}

	/**
	 * @see net.sf.jame.mandelbrot.processingFormula.extension.ProcessingFormulaExtensionRuntime#renderPoint(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public void renderPoint(final RenderedPoint cp) {
		if (k > 0) {
			avgMod = sumMod / k;
			avgArg = sumArg / k;
		}
		cp.tr = avgMod * Math.cos(avgArg);
		cp.ti = avgMod * Math.sin(avgArg);
	}
}
