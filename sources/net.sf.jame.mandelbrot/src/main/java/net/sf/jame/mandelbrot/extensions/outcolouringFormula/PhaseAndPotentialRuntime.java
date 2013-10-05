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
package net.sf.jame.mandelbrot.extensions.outcolouringFormula;

import net.sf.jame.mandelbrot.renderer.RenderedPoint;

/**
 * @author Andrea Medeghini
 */
public class PhaseAndPotentialRuntime extends AbstractOutcolouringPaletteRuntime<PhaseAndPotentialConfig> {
	private static final double CONSTANT_2PI = 2d * Math.PI;

	/**
	 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteRuntime#renderIndex(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	protected int renderIndex(final RenderedPoint cp) {
		final double phase = Math.atan2(cp.zi, cp.zr) / PhaseAndPotentialRuntime.CONSTANT_2PI;
		if (phase > 0) {
			return (int) Math.rint((0.5d + 0.5d * phase) * (cp.time - 1));
		}
		else {
			return (int) Math.rint((0.5d + 0.5d * (1d + phase)) * (cp.time - 1));
		}
	}
}
