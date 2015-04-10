/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.mandelbrot.extensions.colorRenderer;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.twister.common.PercentageElement;

/**
 * @author Andrea Medeghini
 */
public class ConstantConfig extends AbstractColorRendererConfig {
	private static final long serialVersionUID = 1L;
	protected PercentageElement valueElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		valueElement = new PercentageElement(getDefaultValue());
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(valueElement);
		return elements;
	}

	/**
	 * @return
	 */
	public Integer getValue() {
		return valueElement.getValue();
	}

	/**
	 * @return
	 */
	public Integer getDefaultValue() {
		return new Integer(100);
	}

	/**
	 * @param value
	 */
	public void setValue(final Integer value) {
		valueElement.setValue(value);
	}

	/**
	 * @return the valueElement
	 */
	public PercentageElement getValueElement() {
		return valueElement;
	}

	/**
	 * @return
	 */
	@Override
	public ConstantConfig clone() {
		final ConstantConfig config = new ConstantConfig();
		config.setValue(getValue());
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
		final ConstantConfig other = (ConstantConfig) obj;
		if (valueElement == null) {
			if (other.valueElement != null) {
				return false;
			}
		}
		else if (!valueElement.equals(other.valueElement)) {
			return false;
		}
		return true;
	}
}
