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
package net.sf.jame.twister.extensions.effect;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.twister.common.PercentageElement;
import net.sf.jame.twister.effect.extension.EffectExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class FireConfig extends EffectExtensionConfig {
	private static final long serialVersionUID = 1L;
	private PercentageElement intensityElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		intensityElement = new PercentageElement(100);
		intensityElement.addChangeListener(new ValueChangeEventDispatcher());
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(intensityElement);
		return elements;
	}

	/**
	 * @return the intensity.
	 */
	public Integer getIntensity() {
		return intensityElement.getValue();
	}

	/**
	 * @param intensity the intensity to set.
	 */
	public void setIntensity(final Integer intensity) {
		intensityElement.setValue(intensity);
	}

	/**
	 * @return
	 */
	protected PercentageElement getIntensityElement() {
		return intensityElement;
	}

	/**
	 * @return
	 */
	@Override
	public FireConfig clone() {
		final FireConfig config = new FireConfig();
		config.setIntensity(getIntensity());
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
		final FireConfig other = (FireConfig) obj;
		if (intensityElement == null) {
			if (other.intensityElement != null) {
				return false;
			}
		}
		else if (!intensityElement.equals(other.intensityElement)) {
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
