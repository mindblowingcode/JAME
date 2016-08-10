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
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;
	private StringElement nameElement;
	private ListConfigElement<PathReplacementConfigElement> pathReplacementListElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		nameElement = new StringElement("");
		pathReplacementListElement = new ListConfigElement<PathReplacementConfigElement>("pathReplacementListElement");
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(nameElement);
		elements.add(pathReplacementListElement);
		return elements;
	}

	/**
	 * @return
	 */
	public StringElement getNameElement() {
		return nameElement;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return nameElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setName(final String value) {
		nameElement.setValue(value);
	}
	/**
	 * @return
	 */
	public ListConfigElement<PathReplacementConfigElement> getPathReplacementListElement() {
		return pathReplacementListElement;
	}

	/**
	 * Returns a pathReplacement element.
	 * 
	 * @param index the pathReplacement index.
	 * @return the pathReplacement.
	 */
	public PathReplacementConfigElement getPathReplacementConfigElement(final int index) {
		return pathReplacementListElement.getElement(index);
	}

	/**
	 * Returns a pathReplacement element index.
	 * 
	 * @param pathReplacementElement the pathReplacement element.
	 * @return the index.
	 */
	public int indexOfPathReplacementConfigElement(final PathReplacementConfigElement pathReplacementElement) {
		return pathReplacementListElement.indexOfElement(pathReplacementElement);
	}

	/**
	 * Returns the number of pathReplacement elements.
	 * 
	 * @return the number of pathReplacement elements.
	 */
	public int getPathReplacementConfigElementCount() {
		return pathReplacementListElement.getElementCount();
	}

	/**
	 * Adds a pathReplacement element.
	 * 
	 * @param pathReplacementElement the pathReplacement to add.
	 */
	public void appendPathReplacementConfigElement(final PathReplacementConfigElement pathReplacementElement) {
		pathReplacementListElement.appendElement(pathReplacementElement);
	}

	/**
	 * Adds a pathReplacement element.
	 * 
	 * @param index the index.
	 * @param pathReplacementElement the pathReplacement to add.
	 */
	public void insertPathReplacementConfigElementAfter(final int index, final PathReplacementConfigElement pathReplacementElement) {
		pathReplacementListElement.insertElementAfter(index, pathReplacementElement);
	}

	/**
	 * Adds a pathReplacement element.
	 * 
	 * @param index the index.
	 * @param pathReplacementElement the pathReplacement to add.
	 */
	public void insertPathReplacementConfigElementBefore(final int index, final PathReplacementConfigElement pathReplacementElement) {
		pathReplacementListElement.insertElementBefore(index, pathReplacementElement);
	}

	/**
	 * Removes a pathReplacement element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removePathReplacementConfigElement(final int index) {
		pathReplacementListElement.removeElement(index);
	}

	/**
	 * Removes a pathReplacement element.
	 * 
	 * @param pathReplacementElement the pathReplacement to remove.
	 */
	public void removePathReplacementConfigElement(final PathReplacementConfigElement pathReplacementElement) {
		pathReplacementListElement.removeElement(pathReplacementElement);
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
		if (nameElement == null) {
			if (other.nameElement != null) {
				return false;
			}
		}
		else if (!nameElement.equals(other.nameElement)) {
			return false;
		}
		if (pathReplacementListElement == null) {
			if (other.pathReplacementListElement != null) {
				return false;
			}
		}
		else if (!pathReplacementListElement.equals(other.pathReplacementListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public PathFigureConfig clone() {
		final PathFigureConfig config = new PathFigureConfig();
		config.setName(getName());
		config.pathReplacementListElement.copyFrom(getPathReplacementListElement());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		builder.append("path ");
		builder.append(nameElement.getValue());
		builder.append(" {\n");
		builder.addTab();
		for (int i = 0; i < pathReplacementListElement.getElementCount(); i++) {
			pathReplacementListElement.getElement(i).toCFDG(builder);
			builder.append("\n");
		}
		builder.removeTab();
		builder.append("}\n");
	}
}
