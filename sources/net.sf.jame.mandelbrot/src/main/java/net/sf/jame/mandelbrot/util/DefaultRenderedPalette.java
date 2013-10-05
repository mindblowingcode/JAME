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
package net.sf.jame.mandelbrot.util;

import java.awt.Color;

/**
 * @author Andrea Medeghini
 */
public class DefaultRenderedPalette extends RenderedPalette {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DefaultRenderedPalette() {
		// this(Color.WHITE, Color.BLACK);
		super(DefaultRenderedPalette.buildParams());
	}

	/**
	 * @param firstColor
	 * @param lastColor
	 */
	public DefaultRenderedPalette(final Color firstColor, final Color lastColor) {
		super(DefaultRenderedPalette.buildParams(firstColor.getRGB(), lastColor.getRGB()));
	}

	/**
	 * @param firstColor
	 * @param lastColor
	 */
	public DefaultRenderedPalette(final int firstColor, final int lastColor) {
		super(DefaultRenderedPalette.buildParams(firstColor, lastColor));
	}

	/**
	 * @param firstColor
	 * @param lastColor
	 * @return the params.
	 * @throws Error
	 */
	private static RenderedPaletteParam[] buildParams(final int firstColor, final int lastColor) {
		final RenderedPaletteParam[] params = new RenderedPaletteParam[1];
		params[0] = new DefaultRenderedPaletteParam(new int[] { firstColor, lastColor }, 100);
		return params;
	}

	/**
	 * @return the params.
	 * @throws Error
	 */
	private static RenderedPaletteParam[] buildParams() {
		final RenderedPaletteParam[] params = new RenderedPaletteParam[3];
		params[0] = new DefaultRenderedPaletteParam(new int[] { 0xFF440088, 0xFFFFFF00 }, 20);
		params[1] = new DefaultRenderedPaletteParam(new int[] { 0xFFFFFF00, 0xFFFFFFFF }, 40);
		params[2] = new DefaultRenderedPaletteParam(new int[] { 0xFFFFFFFF, 0xFF000000 }, 40);
		return params;
	}
}
