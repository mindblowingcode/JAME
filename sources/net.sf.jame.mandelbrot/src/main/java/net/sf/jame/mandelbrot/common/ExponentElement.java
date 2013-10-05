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
package net.sf.jame.mandelbrot.common;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ExponentElement extends ValueConfigElement<Integer> {
	public static final String CLASS_ID = "Exponent";
	private static final long serialVersionUID = 1L;
	private Integer minimum;
	private Integer maximum;
	private Integer step;

	/**
	 * @param defaultValue
	 */
	public ExponentElement(final Integer defaultValue) {
		super(ExponentElement.CLASS_ID, defaultValue);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElement#clone()
	 */
	@Override
	public ExponentElement clone() {
		ExponentElement exponentElement = new ExponentElement(getValue());
		exponentElement.setMaximum(getMaximum());
		exponentElement.setMinimum(getMinimum());
		exponentElement.setStep(getStep());
		return exponentElement;
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		final ExponentElement element = (ExponentElement) source;
		setMaximum(element.getMaximum());
		setMinimum(element.getMinimum());
		setValue(element.getValue());
	}

	/**
	 * @return the minimum
	 */
	public Integer getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(final Integer minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the maximum
	 */
	public Integer getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(final Integer maximum) {
		this.maximum = maximum;
	}

	/**
	 * @return the step
	 */
	public Integer getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(final Integer step) {
		this.step = step;
	}
}
