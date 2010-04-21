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
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 *
 */
public class PathFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;
	private PathConfigElement pathElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		pathElement = new PathConfigElement();
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(pathElement);
		return elements;
	}

	/**
	 * @return
	 */
	public PathConfigElement getPathElement() {
		return pathElement;
	}

	/**
	 * @return
	 */
	@Override
	public PathFigureConfig clone() {
		final PathFigureConfig config = new PathFigureConfig();
		config.getPathElement().copyFrom(pathElement);
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
		final PathFigureConfig other = (PathFigureConfig) obj;
		if (!pathElement.equals(other.pathElement)) {
			return false;
		}
		return true;
	}
}
