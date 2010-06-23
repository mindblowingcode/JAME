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
package net.sf.jame.mandelbrot.extensions.fractal.rendering;

import net.sf.jame.core.math.Complex;
import net.sf.jame.core.util.DoubleVector2D;
import net.sf.jame.mandelbrot.fractal.orbittrap.extension.OrbitTrapExtensionRuntime;
import net.sf.jame.mandelbrot.fractal.processing.extension.ProcessingFormulaExtensionRuntime;
import net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig;
import net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractRenderingFormulaRuntime<T extends RenderingFormulaExtensionConfig> extends RenderingFormulaExtensionRuntime<T> {
	protected ProcessingFormulaExtensionRuntime formulaRuntime;
	protected OrbitTrapExtensionRuntime<?> orbitTrapRuntime;
	protected double threshold;
	protected int iterations;

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#prepareForRendering(net.sf.jame.mandelbrot.fractal.processing.extension.ProcessingFormulaExtensionRuntime, net.sf.jame.mandelbrot.fractal.orbittrap.extension.OrbitTrapExtensionRuntime)
	 */
	@Override
	public void prepareForRendering(final ProcessingFormulaExtensionRuntime formulaRuntime, final OrbitTrapExtensionRuntime<?> orbitTrapRuntime) {
		this.formulaRuntime = formulaRuntime;
		this.orbitTrapRuntime = orbitTrapRuntime;
		this.threshold = this.getThreshold();
		this.iterations = this.getIterations();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#isMandelbrotModeAllowed()
	 */
	@Override
	public boolean isMandelbrotModeAllowed() {
		return true;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getThreshold()
	 */
	@Override
	public double getThreshold() {
		return getConfig().getThreshold();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getIterations()
	 */
	@Override
	public int getIterations() {
		return getConfig().getIterations();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getDefaultCenter()
	 */
	@Override
	public DoubleVector2D getDefaultCenter() {
		return getConfig().getDefaultCenter();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getDefaultScale()
	 */
	@Override
	public DoubleVector2D getDefaultScale() {
		return getConfig().getDefaultScale();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getCenter()
	 */
	@Override
	public DoubleVector2D getCenter() {
		return getConfig().getCenter();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getScale()
	 */
	@Override
	public DoubleVector2D getScale() {
		return getConfig().getScale();
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getInitialPoint()
	 */
	@Override
	public Complex getInitialPoint() {
		return new Complex(0.0, 0.0);
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#isHorizontalSymetryAllowed()
	 */
	@Override
	public boolean isHorizontalSymetryAllowed() {
		return false;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#isVerticalSymetryAllowed()
	 */
	@Override
	public boolean isVerticalSymetryAllowed() {
		return false;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getHorizontalSymetryPoint()
	 */
	@Override
	public double getHorizontalSymetryPoint() {
		return 0.0;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionRuntime#getVerticalSymetryPoint()
	 */
	@Override
	public double getVerticalSymetryPoint() {
		return 0.0;
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
	}
}
