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
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.shapeAdjustment.extension.ShapeAdjustmentExtensionConfig;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SkewShapeAdjustmentConfig extends ShapeAdjustmentExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement shearXElement;
	private FloatElement shearYElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		shearXElement = new FloatElement(0f);
		shearYElement = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(shearXElement);
		elements.add(shearYElement);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getShearXElement() {
		return shearXElement;
	}
	
	/**
	 * @return
	 */
	public Float getShearX() {
		return shearXElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShearX(final Float value) {
		shearXElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getShearYElement() {
		return shearYElement;
	}
	
	/**
	 * @return
	 */
	public Float getShearY() {
		return shearYElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShearY(final Float value) {
		shearYElement.setValue(value);
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
		final SkewShapeAdjustmentConfig other = (SkewShapeAdjustmentConfig) obj;
		if (shearXElement == null) {
			if (other.shearXElement != null) {
				return false;
			}
		}
		else if (!shearXElement.equals(other.shearXElement)) {
			return false;
		}
		if (shearYElement == null) {
			if (other.shearYElement != null) {
				return false;
			}
		}
		else if (!shearYElement.equals(other.shearYElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SkewShapeAdjustmentConfig clone() {
		final SkewShapeAdjustmentConfig config = new SkewShapeAdjustmentConfig();
		config.setShearX(getShearX());
		config.setShearY(getShearY());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		if (shearXElement.getValue() != null && shearYElement.getValue() != null) {
			builder.append("skew ");
			builder.append(shearXElement.getValue() * 180f / Math.PI);
			builder.append(" ");
			builder.append(shearYElement.getValue() * 180f / Math.PI);
		}
	}
}

