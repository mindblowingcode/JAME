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

import net.sf.jame.mandelbrot.util.RenderedPalette;
import net.sf.jame.twister.common.PaletteElement;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteElement extends PaletteElement<RenderedPalette> {
	public static final String CLASS_ID = "RenderedPalette";
	private static final long serialVersionUID = 1L;

	/**
	 * @param defaultValue
	 */
	public RenderedPaletteElement(final RenderedPalette defaultValue) {
		super(RenderedPaletteElement.CLASS_ID, defaultValue);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElement#clone()
	 */
	@Override
	public PaletteElement<RenderedPalette> clone() {
		return new RenderedPaletteElement(getValue());
	}
}
