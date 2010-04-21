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
package net.sf.jame.contextfree.extensions.shapeReplacement;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.replacement.SingleReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 *
 */
public class SingleReplacementConfig extends ShapeReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private SingleReplacementConfigElement replacementElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		replacementElement = new SingleReplacementConfigElement();
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(replacementElement);
		return elements;
	}

	/**
	 * @return
	 */
	public SingleReplacementConfigElement getReplacementElement() {
		return replacementElement;
	}

	/**
	 * @return
	 */
	@Override
	public SingleReplacementConfig clone() {
		final SingleReplacementConfig config = new SingleReplacementConfig();
		config.getReplacementElement().copyFrom(replacementElement);
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
		final SingleReplacementConfig other = (SingleReplacementConfig) obj;
		if (!replacementElement.equals(other.replacementElement)) {
			return false;
		}
		return true;
	}
}
