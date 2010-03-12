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
package net.sf.jame.contextfree.extensions.cfdg.path;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.path.operation.extension.PathOperationExtensionConfig;
import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 *
 */
public class LineToPathOperationConfig extends PathOperationExtensionConfig {
	private static final long serialVersionUID = 1L;
	private static final Double DEFAULT_X = 0.0;
	private static final Double DEFAULT_Y = 0.0;
	private DoubleElement xElement;
	private DoubleElement yElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		xElement = new DoubleElement(DEFAULT_X);
		yElement = new DoubleElement(DEFAULT_Y);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(2);
		elements.add(xElement);
		elements.add(yElement);
		return elements;
	}

	/**
	 * @param x
	 */
	public void setX(final Double x) {
		xElement.setValue(x);
	}

	/**
	 * @return the x.
	 */
	public Double getX() {
		return xElement.getValue();
	}

	/**
	 * @return the default x.
	 */
	public Double getDefaultX() {
		return xElement.getDefaultValue();
	}

	/**
	 * @param y
	 */
	public void setY(final Double y) {
		yElement.setValue(y);
	}

	/**
	 * @return the y.
	 */
	public Double getY() {
		return yElement.getValue();
	}

	/**
	 * @return the default y.
	 */
	public Double getDefaultY() {
		return yElement.getDefaultValue();
	}

	/**
	 * @return
	 */
	protected DoubleElement getXElement() {
		return xElement;
	}

	/**
	 * @return
	 */
	protected DoubleElement getYElement() {
		return yElement;
	}

	/**
	 * @return
	 */
	@Override
	public LineToPathOperationConfig clone() {
		final LineToPathOperationConfig config = new LineToPathOperationConfig();
		config.setX(getX());
		config.setY(getY());
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
		final LineToPathOperationConfig other = (LineToPathOperationConfig) obj;
		if (!xElement.equals(other.xElement)) {
			return false;
		}
		if (!yElement.equals(other.yElement)) {
			return false;
		}
		return true;
	}
}
