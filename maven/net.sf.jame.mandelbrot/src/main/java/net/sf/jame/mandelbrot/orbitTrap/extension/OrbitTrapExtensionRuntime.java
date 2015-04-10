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
package net.sf.jame.mandelbrot.orbitTrap.extension;

import java.awt.Shape;

import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.core.util.DoubleVector2D;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;

/**
 * @author Andrea Medeghini
 */
public abstract class OrbitTrapExtensionRuntime<T extends OrbitTrapExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param center
	 */
	public abstract void prepareForProcessing(DoubleVector2D center);

	/**
	 * @param cp
	 * @return
	 */
	public abstract boolean processPoint(RenderedPoint cp);

	/**
	 * @param cp
	 */
	public abstract void renderPoint(RenderedPoint cp);

	/**
	 * @param sx
	 * @param sy
	 * @param theta
	 * @return
	 */
	public abstract Shape renderOrbitTrap(double sx, double sy, double theta);
}
