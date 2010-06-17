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
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 *
 */
public class TargetHueShapeAdjustmentConfig extends ShapeAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private static final Double DEFAULT_VALUE = 0.0;
	private DoubleElement valueElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		valueElement = new DoubleElement(DEFAULT_VALUE);
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
	 * @param value
	 */
	public void setValue(final Double value) {
		valueElement.setValue(value);
	}

	/**
	 * @return the value.
	 */
	public Double getValue() {
		return valueElement.getValue();
	}

	/**
	 * @return the default value.
	 */
	public Double getDefaultValue() {
		return valueElement.getDefaultValue();
	}

	/**
	 * @return
	 */
	protected DoubleElement getValueElement() {
		return valueElement;
	}

	/**
	 * @return
	 */
	@Override
	public TargetHueShapeAdjustmentConfig clone() {
		final TargetHueShapeAdjustmentConfig config = new TargetHueShapeAdjustmentConfig();
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
		final TargetHueShapeAdjustmentConfig other = (TargetHueShapeAdjustmentConfig) obj;
		if (!valueElement.equals(other.valueElement)) {
			return false;
		}
		return true;
	}
}
