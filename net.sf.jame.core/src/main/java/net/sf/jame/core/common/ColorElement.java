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
package net.sf.jame.core.common;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.util.Color32bit;

/**
 * @author Andrea Medeghini
 */
public class ColorElement extends ValueConfigElement<Color32bit> {
	public static final String CLASS_ID = "Color";
	private static final long serialVersionUID = 1L;

	/**
	 * @param defaultValue
	 */
	public ColorElement(final Color32bit defaultValue) {
		super(ColorElement.CLASS_ID, defaultValue);
	}

	/**
	 * @return
	 */
	@Override
	public ColorElement clone() {
		return new ColorElement(getValue());
	}

	/**
	 * @see net.sf.jame.core.config.ConfigElement#copyFrom(net.sf.jame.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		final ColorElement element = (ColorElement) source;
		setValue(element.getValue());
	}
}
