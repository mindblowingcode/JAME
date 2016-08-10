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
package net.sf.jame.contextfree.common;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElement extends ValueConfigElement<Float> {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "StrokeWidth";
	private Float minimum;
	private Float maximum;
	private Float step;

	/**
	 * @param defaultValue
	 */
	public StrokeWidthElement(final Float defaultValue) {
		super(StrokeWidthElement.CLASS_ID, defaultValue);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElement#clone()
	 */
	@Override
	public StrokeWidthElement clone() {
		StrokeWidthElement StrokeWidthElement = new StrokeWidthElement(getValue());
		StrokeWidthElement.setMaximum(getMaximum());
		StrokeWidthElement.setMinimum(getMinimum());
		StrokeWidthElement.setStep(getStep());
		return StrokeWidthElement;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		final StrokeWidthElement element = (StrokeWidthElement) source;
		setMaximum(element.getMaximum());
		setMinimum(element.getMinimum());
		setValue(element.getValue());
	}

	/**
	 * @return the minimum
	 */
	public Float getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(final Float minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the maximum
	 */
	public Float getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(final Float maximum) {
		this.maximum = maximum;
	}

	/**
	 * @return the step
	 */
	public Float getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(final Float step) {
		this.step = step;
	}
}
