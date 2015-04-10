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
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.pathReplacement.extension.PathReplacementExtensionConfig;
import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.config.ConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ClosePolyPathReplacementConfig extends PathReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private BooleanElement alignElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		alignElement = new BooleanElement(false);
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(alignElement);
		return elements;
	}

	/**
	 * @return
	 */
	public BooleanElement getAlignElement() {
		return alignElement;
	}
	
	/**
	 * @return
	 */
	public Boolean isAlign() {
		return alignElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setAlign(final Boolean value) {
		alignElement.setValue(value);
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
		final ClosePolyPathReplacementConfig other = (ClosePolyPathReplacementConfig) obj;
		if (alignElement == null) {
			if (other.alignElement != null) {
				return false;
			}
		}
		else if (!alignElement.equals(other.alignElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public ClosePolyPathReplacementConfig clone() {
		final ClosePolyPathReplacementConfig config = new ClosePolyPathReplacementConfig();
		config.setAlign(isAlign());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		builder.appendTabs();
		builder.append("CLOSEPOLY {");
		if (alignElement.getValue() != null) {
			builder.append(" ");
			builder.append(alignElement.getValue() ? "p align" : "");
		}
		builder.append(" }");
	}
}
