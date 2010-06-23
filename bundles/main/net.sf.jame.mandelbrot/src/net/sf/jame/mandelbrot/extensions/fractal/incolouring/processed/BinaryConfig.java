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
package net.sf.jame.mandelbrot.extensions.fractal.incolouring.processed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfig;

/**
 * @author Andrea Medeghini
 */
public class BinaryConfig extends AbstractIncolouringFormulaConfig {
	private static final long serialVersionUID = 1L;
	private static final Color32bit[] DEFAULT_COLORS = new Color32bit[] { new Color32bit(0xFF000000), new Color32bit(0xFFFFFFFF) };
	private ColorElement[] colorElements;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		colorElements = new ColorElement[2];
		colorElements[0] = new ColorElement(getDefaultColors()[0]);
		colorElements[1] = new ColorElement(getDefaultColors()[1]);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(colorElements[0]);
		elements.add(colorElements[1]);
		return elements;
	}

	/**
	 * @param colors
	 */
	public void setColors(final Color32bit[] colors) {
		colorElements[0].setValue(colors[0]);
		colorElements[1].setValue(colors[1]);
	}

	/**
	 * @return the colors.
	 */
	public Color32bit[] getColors() {
		return new Color32bit[] { colorElements[0].getValue(), colorElements[1].getValue() };
	}

	/**
	 * @return the default colors.
	 */
	public Color32bit[] getDefaultColors() {
		return BinaryConfig.DEFAULT_COLORS;
	}

	/**
	 * @param index
	 * @return
	 */
	protected ColorElement getColorElement(final int index) {
		return colorElements[index];
	}

	/**
	 * @return
	 */
	@Override
	public BinaryConfig clone() {
		final BinaryConfig config = new BinaryConfig();
		config.setColors(getColors());
		return config;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final BinaryConfig other = (BinaryConfig) obj;
		if (!Arrays.equals(colorElements, other.colorElements)) {
			return false;
		}
		return true;
	}
}
