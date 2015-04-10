/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.mandelbrot.extensions.orbitTrap;

import java.awt.Shape;

import net.sf.jame.core.util.DoubleVector2D;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;

/**
 * @author Andrea Medeghini
 */
public class DeltaTrapRuntime extends AbstractOrbitTrapRuntime<DeltaTrapConfig> {
	private double zr0;
	private double zi0;
	private double zr1;
	private double zi1;
	private double m;
	private double t;
	private int k;

	/**
	 * @see net.sf.jame.mandelbrot.extensions.orbitTrap.AbstractOrbitTrapRuntime#prepareForProcessing(net.sf.jame.core.util.DoubleVector2D)
	 */
	@Override
	public void prepareForProcessing(final DoubleVector2D center) {
		super.prepareForProcessing(center);
		zr0 = 0;
		zi0 = 0;
		zr1 = 0;
		zi1 = 0;
		k = 0;
		t = getConfig().getThreshold() / 2d;
	}

	/**
	 * @see net.sf.jame.mandelbrot.orbitTrap.extension.OrbitTrapExtensionRuntime#processPoint(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public boolean processPoint(final RenderedPoint cp) {
		k += 1;
		zr0 = zr1;
		zi0 = zi1;
		zr1 = cp.zr;
		zi1 = cp.zi;
		cp.dr = zr1 - zr0;
		cp.di = zi1 - zi0;
		if (k > 2) {
			m = Math.sqrt(cp.dr * cp.dr + cp.di * cp.di);
			return m < t;
		}
		return false;
	}

	/**
	 * @see net.sf.jame.mandelbrot.orbitTrap.extension.OrbitTrapExtensionRuntime#renderPoint(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public void renderPoint(final RenderedPoint cp) {
		cp.tr = cp.dr;
		cp.ti = cp.di;
	}

	/**
	 * @see net.sf.jame.mandelbrot.orbitTrap.extension.OrbitTrapExtensionRuntime#renderOrbitTrap(double, double)
	 */
	@Override
	public Shape renderOrbitTrap(final double sx, final double sy, final double theta) {
		return null;
	}
}
