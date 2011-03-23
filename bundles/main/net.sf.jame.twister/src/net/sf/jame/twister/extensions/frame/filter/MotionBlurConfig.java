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

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.twister.common.PercentageElement;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class MotionBlurConfig extends FrameFilterExtensionConfig {
	private static final Integer DEFAULT_OPACITY = new Integer(70);
	private static final long serialVersionUID = 1L;
	private PercentageElement opacityElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		opacityElement = new PercentageElement(getDefaultOpacity());
		opacityElement.addChangeListener(new ValueChangeEventDispatcher());
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(opacityElement);
		return elements;
	}

	/**
	 * @return the color.
	 */
	public Integer getOpacity() {
		return opacityElement.getValue();
	}

	/**
	 * @return the default color.
	 */
	public Integer getDefaultOpacity() {
		return MotionBlurConfig.DEFAULT_OPACITY;
	}

	/**
	 * @param opacity the color to set.
	 */
	public void setOpacity(final Integer opacity) {
		opacityElement.setValue(opacity);
	}

	/**
	 * @return
	 */
	public PercentageElement getOpacityElement() {
		return opacityElement;
	}

	/**
	 * @return
	 */
	@Override
	public MotionBlurConfig clone() {
		final MotionBlurConfig config = new MotionBlurConfig();
		config.setOpacity(getOpacity());
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
		final MotionBlurConfig other = (MotionBlurConfig) obj;
		if (opacityElement == null) {
			if (other.opacityElement != null) {
				return false;
			}
		}
		else if (!opacityElement.equals(other.opacityElement)) {
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
