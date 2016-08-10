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
package net.sf.jame.contextfree.extensions.pathAdjustment;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class FlipPathAdjustmentConfig extends PathAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement angleElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		angleElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(angleElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getAngleElement() {
		return angleElement;
	}
	
	/**
	 * @return
	 */
	public Float getAngle() {
		return angleElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setAngle(final Float value) {
		angleElement.setValue(value);
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
		final FlipPathAdjustmentConfig other = (FlipPathAdjustmentConfig) obj;
		if (angleElement == null) {
			if (other.angleElement != null) {
				return false;
			}
		}
		else if (!angleElement.equals(other.angleElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public FlipPathAdjustmentConfig clone() {
		final FlipPathAdjustmentConfig config = new FlipPathAdjustmentConfig();
		config.setAngle(getAngle());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		if (angleElement.getValue() != null) {
			builder.append("flip ");
			builder.append(angleElement.getValue() * 180f / Math.PI);
		}
	}
}
