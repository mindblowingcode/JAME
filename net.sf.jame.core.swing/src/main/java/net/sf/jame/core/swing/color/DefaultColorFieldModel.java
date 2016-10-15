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
package net.sf.jame.core.swing.color;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Andrea Medeghini
 */
public class DefaultColorFieldModel implements ColorFieldModel {
	private ArrayList<ColorChangeListener> listeners = new ArrayList<ColorChangeListener>();
	private Color color;

	/**
	 * 
	 */
	public DefaultColorFieldModel() {
		color = new Color(255, 255, 255);
	}

	/**
	 * @param color
	 */
	public DefaultColorFieldModel(final Color color) {
		if (color == null) {
			throw new NullPointerException("color == null");
		}
		this.color = color;
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		color = null;
		listeners.clear();
		listeners = null;
		super.finalize();
	}

	/**
	 * @see net.sf.jame.core.swing.color.ColorFieldModel#addColorChangeListener(net.sf.jame.core.swing.color.ColorChangeListener)
	 */
	public void addColorChangeListener(final ColorChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * @see net.sf.jame.core.swing.color.ColorFieldModel#removeColorChangeListener(net.sf.jame.core.swing.color.ColorChangeListener)
	 */
	public void removeColorChangeListener(final ColorChangeListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @param e
	 */
	protected void fireColorChangeEvent(final ColorChangeEvent e) {
		for (final ColorChangeListener listener : listeners) {
			listener.colorChanged(e);
		}
	}

	/**
	 * @see net.sf.jame.core.swing.color.ColorFieldModel#setColor(java.awt.Color, boolean)
	 */
	public void setColor(final Color color, final boolean isAdjusting) {
		if (color == null) {
			throw new NullPointerException("color == null");
		}
		if ((this.color != color) && (this.color.getRGB() != color.getRGB())) {
			this.color = color;
			fireColorChangeEvent(new ColorChangeEvent(this, isAdjusting));
		}
	}

	/**
	 * @see net.sf.jame.core.swing.color.ColorFieldModel#getColor()
	 */
	public Color getColor() {
		return color;
	}
}
