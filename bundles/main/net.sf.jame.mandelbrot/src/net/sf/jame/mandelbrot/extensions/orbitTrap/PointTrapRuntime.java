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
package net.sf.jame.mandelbrot.extensions.orbitTrap;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import net.sf.jame.core.util.DoubleVector2D;
import net.sf.jame.mandelbrot.common.CriteriaElement;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;

/**
 * @author Andrea Medeghini
 */
public class PointTrapRuntime extends AbstractOrbitTrapRuntime<PointTrapConfig> {
	private double m;
	private double d;
	private int c;

	/**
	 * @see net.sf.jame.mandelbrot.extensions.orbitTrap.AbstractOrbitTrapRuntime#prepareForProcessing(net.sf.jame.core.util.DoubleVector2D)
	 */
	@Override
	public void prepareForProcessing(final DoubleVector2D center) {
		super.prepareForProcessing(center);
		d = getConfig().getSize() / 2d;
		c = getConfig().getCriteria();
	}

	/**
	 * @see net.sf.jame.mandelbrot.orbitTrap.extension.OrbitTrapExtensionRuntime#processPoint(net.sf.jame.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public boolean processPoint(final RenderedPoint cp) {
		cp.dr = cp.zr - center.getX();
		cp.di = cp.zi + center.getY();
		m = Math.sqrt(cp.dr * cp.dr + cp.di * cp.di);
		if (c == CriteriaElement.FIRST_OUT) {
			return m > d;
		}
		else {
			return m < d;
		}
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
		Ellipse2D.Double ellipse = new Ellipse2D.Double((-getConfig().getSize() / 2d) * sx, (-getConfig().getSize() / 2d) * sy, getConfig().getSize() * sx, getConfig().getSize() * sy);
		return ellipse;
	}
}
