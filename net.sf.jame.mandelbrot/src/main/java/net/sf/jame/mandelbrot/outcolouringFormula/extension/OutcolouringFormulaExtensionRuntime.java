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
package net.sf.jame.mandelbrot.outcolouringFormula.extension;

import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.mandelbrot.renderer.RenderedPoint;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class OutcolouringFormulaExtensionRuntime<T extends OutcolouringFormulaExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @return true if solid guess is allowed.
	 */
	public abstract boolean isSolidGuessAllowed();

	/**
	 * @return true if horizontal symetry is allowed.
	 */
	public abstract boolean isHorizontalSymetryAllowed();

	/**
	 * @return true if vertical symetry is allowed.
	 */
	public abstract boolean isVerticalSymetryAllowed();

	/**
	 * Sets the iterations.
	 * 
	 * @param maxColors the number of colours.
	 */
	public abstract void prepareForRendering(RenderingFormulaExtensionRuntime<?> formulaRuntime, int maxColors);

	/**
	 * @param cp
	 * @return the color.
	 */
	public abstract int renderColor(RenderedPoint cp);

	/**
	 * @param cp
	 * @param shift
	 * @return the color.
	 */
	public abstract int renderColor(RenderedPoint cp, int shift);
}
