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
package net.sf.jame.mandelbrot.extensions.colorRenderer;

import net.sf.jame.mandelbrot.colorRendererFormula.ColorRendererFormulaRuntimeElement;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractPeriodicRuntime<T extends AbstractPeriodicConfig> extends AbstractColorRendererRuntime<T> {
	private static final double CONSTANT_2PI = 2d * Math.PI;
	protected ColorRendererFormulaRuntimeElement formulaElement;
	protected boolean absoluteEnabled;
	protected boolean timeEnabled;
	protected double amplitude;
	protected double frequency;
	protected double scale;
	protected double a;
	protected double w;
	protected double t;

	/**
	 * @see net.sf.jame.mandelbrot.colorRenderer.extension.ColorRendererExtensionRuntime#prepareForRendering()
	 */
	@Override
	public void prepareForRendering() {
		final T config = getConfig();
		scale = config.getScale().doubleValue();
		frequency = config.getFrequency().doubleValue();
		amplitude = config.getAmplitude().doubleValue();
		absoluteEnabled = config.getAbsoluteEnabled();
		timeEnabled = config.getTimeEnabled();
		w = (AbstractPeriodicRuntime.CONSTANT_2PI * frequency) / scale;
		a = amplitude / 100d;
	}

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean formulaChanged = (formulaElement != null) && formulaElement.isChanged();
		return super.isChanged() || formulaChanged;
	}

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRuntime#configReloaded()
	 */
	@Override
	public void configReloaded() {
		super.configReloaded();
		formulaElement = new ColorRendererFormulaRuntimeElement(getConfig().getColorRendererFormulaElement());
	}
}
