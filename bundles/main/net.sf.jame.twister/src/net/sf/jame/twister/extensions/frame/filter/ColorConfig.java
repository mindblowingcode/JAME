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
package net.sf.jame.twister.extensions.frame.filter;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ColorConfig extends FrameFilterExtensionConfig {
	private static final Color32bit DEFAULT_COLOR = Color32bit.BLACK;
	private static final long serialVersionUID = 1L;
	private ColorElement colorElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		colorElement = new ColorElement(getDefaultColor());
		colorElement.addChangeListener(new ValueChangeEventDispatcher());
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(colorElement);
		return elements;
	}

	/**
	 * @return the color.
	 */
	public Color32bit getColor() {
		return colorElement.getValue();
	}

	/**
	 * @return the default color.
	 */
	public Color32bit getDefaultColor() {
		return ColorConfig.DEFAULT_COLOR;
	}

	/**
	 * @param color the color to set.
	 */
	public void setColor(final Color32bit color) {
		colorElement.setValue(color);
	}

	/**
	 * @return
	 */
	protected ColorElement getColorElement() {
		return colorElement;
	}

	/**
	 * @return
	 */
	@Override
	public ColorConfig clone() {
		final ColorConfig config = new ColorConfig();
		config.setColor(getColor());
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
		final ColorConfig other = (ColorConfig) obj;
		if (colorElement == null) {
			if (other.colorElement != null) {
				return false;
			}
		}
		else if (!colorElement.equals(other.colorElement)) {
			return false;
		}
		return true;
	}

	private class ValueChangeEventDispatcher implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireValueChanged(e);
		}
	}
}
