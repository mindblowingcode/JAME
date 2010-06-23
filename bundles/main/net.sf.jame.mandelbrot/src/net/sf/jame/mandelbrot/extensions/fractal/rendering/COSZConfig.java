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

/**
 * @author Andrea Medeghini
 */
public class COSZConfig extends AbstractRenderingFormulaConfig {
	private static final long serialVersionUID = 1L;

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getDefaultThreshold()
	 */
	@Override
	public Double getDefaultThreshold() {
		return 20.0;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getDefaultIterations()
	 */
	@Override
	public Integer getDefaultIterations() {
		return 200;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getDefaultCenter()
	 */
	@Override
	public DoubleVector2D getDefaultCenter() {
		return new DoubleVector2D(0, 0);
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getDefaultScale()
	 */
	@Override
	public DoubleVector2D getDefaultScale() {
		return new DoubleVector2D(6.0, 6.0);
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getDefaultConstant()
	 */
	@Override
	public Complex getDefaultConstant() {
		return new Complex(1.5, 0.0);
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getThresholdMin()
	 */
	@Override
	public Double getThresholdMin() {
		return 0.0;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getThresholdMax()
	 */
	@Override
	public Double getThresholdMax() {
		return 1000.0;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getThresholdStep()
	 */
	@Override
	public Double getThresholdStep() {
		return 0.1;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getIterationsMin()
	 */
	@Override
	public Integer getIterationsMin() {
		return 1;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getIterationsMax()
	 */
	@Override
	public Integer getIterationsMax() {
		return 10000;
	}

	/**
	 * @see net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig#getIterationsStep()
	 */
	@Override
	public Integer getIterationsStep() {
		return 1;
	}

	/**
	 * @return
	 */
	@Override
	public COSZConfig clone() {
		final COSZConfig config = new COSZConfig();
		config.setCenter(getCenter());
		config.setScale(getScale());
		config.setIterations(getIterations());
		config.setThreshold(getThreshold());
		return config;
	}
}
