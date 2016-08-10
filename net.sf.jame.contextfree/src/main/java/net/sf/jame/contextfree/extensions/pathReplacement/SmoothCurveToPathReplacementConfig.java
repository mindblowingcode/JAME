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
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SmoothCurveToPathReplacementConfig extends PathReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private FloatElement xElement;
	private FloatElement yElement;
	private FloatElement x2Element;
	private FloatElement y2Element;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		xElement = new FloatElement(0f);
		yElement = new FloatElement(0f);
		x2Element = new FloatElement(0f);
		y2Element = new FloatElement(0f);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(xElement);
		elements.add(yElement);
		elements.add(x2Element);
		elements.add(y2Element);
		return elements;
	}

	/**
	 * @return
	 */
	public FloatElement getXElement() {
		return xElement;
	}
	
	/**
	 * @return
	 */
	public Float getX() {
		return xElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setX(final Float value) {
		xElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getYElement() {
		return yElement;
	}
	
	/**
	 * @return
	 */
	public Float getY() {
		return yElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setY(final Float value) {
		yElement.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getX2Element() {
		return x2Element;
	}
	
	/**
	 * @return
	 */
	public Float getX2() {
		return x2Element.getValue();
	}

	/**
	 * @param value
	 */
	public void setX2(final Float value) {
		x2Element.setValue(value);
	}
	/**
	 * @return
	 */
	public FloatElement getY2Element() {
		return y2Element;
	}
	
	/**
	 * @return
	 */
	public Float getY2() {
		return y2Element.getValue();
	}

	/**
	 * @param value
	 */
	public void setY2(final Float value) {
		y2Element.setValue(value);
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
		final SmoothCurveToPathReplacementConfig other = (SmoothCurveToPathReplacementConfig) obj;
		if (xElement == null) {
			if (other.xElement != null) {
				return false;
			}
		}
		else if (!xElement.equals(other.xElement)) {
			return false;
		}
		if (yElement == null) {
			if (other.yElement != null) {
				return false;
			}
		}
		else if (!yElement.equals(other.yElement)) {
			return false;
		}
		if (x2Element == null) {
			if (other.x2Element != null) {
				return false;
			}
		}
		else if (!x2Element.equals(other.x2Element)) {
			return false;
		}
		if (y2Element == null) {
			if (other.y2Element != null) {
				return false;
			}
		}
		else if (!y2Element.equals(other.y2Element)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public SmoothCurveToPathReplacementConfig clone() {
		final SmoothCurveToPathReplacementConfig config = new SmoothCurveToPathReplacementConfig();
		config.setX(getX());
		config.setY(getY());
		config.setX2(getX2());
		config.setY2(getY2());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		builder.appendTabs();
		builder.append("CURVETO {");
		if (xElement.getValue() != null) {
			builder.append(" x ");
			builder.append(xElement.getValue());
		}
		if (yElement.getValue() != null) {
			builder.append(" y ");
			builder.append(yElement.getValue());
		}
		if (x2Element.getValue() != null) {
			builder.append(" x2 ");
			builder.append(x2Element.getValue());
		}
		if (y2Element.getValue() != null) {
			builder.append(" y2 ");
			builder.append(y2Element.getValue());
		}
		builder.append(" }");
	}
}
