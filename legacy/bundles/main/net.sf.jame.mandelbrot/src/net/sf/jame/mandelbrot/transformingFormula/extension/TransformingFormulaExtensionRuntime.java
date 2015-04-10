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
package net.sf.jame.mandelbrot.transformingFormula.extension;

import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.core.math.Complex;

/**
 * @author Andrea Medeghini
 */
public abstract class TransformingFormulaExtensionRuntime<T extends TransformingFormulaExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @return true if horizontal symetry is allowed.
	 */
	public abstract boolean isHorizontalSymetryAllowed();

	/**
	 * @return true if vertical symetry is allowed.
	 */
	public abstract boolean isVerticalSymetryAllowed();

	/**
	 * Transform a point.
	 * 
	 * @param w the point to transform.
	 * @return the transformed point.
	 */
	public abstract Complex renderPoint(Complex w);
}
