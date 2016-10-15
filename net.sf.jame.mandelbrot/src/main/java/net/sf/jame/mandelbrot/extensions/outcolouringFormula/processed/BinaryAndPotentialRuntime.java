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
package net.sf.jame.mandelbrot.extensions.outcolouringFormula.processed;

import net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteRuntime;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class BinaryAndPotentialRuntime extends AbstractOutcolouringPaletteRuntime<BinaryAndPotentialConfig> {
	private static final double CONSTANT_2PI = 2d * Math.PI;
	private int offset;

	/**
	 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteRuntime#prepareForRendering(int)
	 */
	@Override
	public void prepareForRendering(final RenderingFormulaExtensionRuntime<?> formulaRuntime, final int iterations) {
		super.prepareForRendering(formulaRuntime, iterations);
		final BinaryAndPotentialConfig config = getConfig();
		offset = (int) Math.rint((config.getOffset().intValue() * colorTable.length) / 100);
	}

	/**
	 * @see net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionRuntime#renderIndex(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	protected int renderIndex(final RenderedPoint cp) {
		final double phase = Math.atan2(cp.ti, cp.tr) / BinaryAndPotentialRuntime.CONSTANT_2PI;
		if (phase > 0) {
			return ((cp.time - 1) + offset) % colorTable.length;
		}
		else {
			return (cp.time - 1) % colorTable.length;
		}
	}
}
