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
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.contextfree.cfdg.FigureConfigElement;
import net.sf.jame.contextfree.cfdg.path.operation.PathOperationConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathConfigElement extends FigureConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "Path";
	private final ListConfigElement<PathOperationConfigElement> pathOperationListElement = new ListConfigElement<PathOperationConfigElement>("pathOperation");

	/**
	 * Constructs a new element.
	 */
	public PathConfigElement() {
		super(PathConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		nameElement.setContext(context);
		pathOperationListElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public PathConfigElement clone() {
		final PathConfigElement element = new PathConfigElement();
		element.setName(getName());
		element.pathOperationListElement.copyFrom(getPathOperationListElement());
		return element;
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		PathConfigElement pathElement = (PathConfigElement) source;
		setName(pathElement.getName());
		pathOperationListElement.copyFrom(pathElement.getPathOperationListElement());
	}

	/**
	 * @return
	 */
	public ListConfigElement<PathOperationConfigElement> getPathOperationListElement() {
		return pathOperationListElement;
	}

	/**
	 * Returns a pathOperation element.
	 * 
	 * @param index the pathOperation index.
	 * @return the pathOperation.
	 */
	public PathOperationConfigElement getPathOperationConfigElement(final int index) {
		return pathOperationListElement.getElement(index);
	}

	/**
	 * Returns a pathOperation element index.
	 * 
	 * @param pathOperationElement the pathOperation element.
	 * @return the index.
	 */
	public int indexOfPathOperationConfigElement(final PathOperationConfigElement pathOperationElement) {
		return pathOperationListElement.indexOfElement(pathOperationElement);
	}

	/**
	 * Returns the number of pathOperation elements.
	 * 
	 * @return the number of pathOperation elements.
	 */
	public int getPathOperationConfigElementCount() {
		return pathOperationListElement.getElementCount();
	}

	/**
	 * Adds a pathOperation element.
	 * 
	 * @param pathOperationElement the pathOperation to add.
	 */
	public void appendPathOperationConfigElement(final PathOperationConfigElement pathOperationElement) {
		pathOperationListElement.appendElement(pathOperationElement);
	}

	/**
	 * Adds a pathOperation element.
	 * 
	 * @param index the index.
	 * @param pathOperationElement the pathOperation to add.
	 */
	public void insertPathOperationConfigElementAfter(final int index, final PathOperationConfigElement pathOperationElement) {
		pathOperationListElement.insertElementAfter(index, pathOperationElement);
	}

	/**
	 * Adds a pathOperation element.
	 * 
	 * @param index the index.
	 * @param pathOperationElement the pathOperation to add.
	 */
	public void insertPathOperationConfigElementBefore(final int index, final PathOperationConfigElement pathOperationElement) {
		pathOperationListElement.insertElementBefore(index, pathOperationElement);
	}

	/**
	 * Removes a pathOperation element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removePathOperationConfigElement(final int index) {
		pathOperationListElement.removeElement(index);
	}

	/**
	 * Removes a pathOperation element.
	 * 
	 * @param pathOperationElement the pathOperation to remove.
	 */
	public void removePathOperationConfigElement(final PathOperationConfigElement pathOperationElement) {
		pathOperationListElement.removeElement(pathOperationElement);
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
		final PathConfigElement other = (PathConfigElement) obj;
		if (nameElement == null) {
			if (other.nameElement != null) {
				return false;
			}
		}
		else if (!nameElement.equals(other.nameElement)) {
			return false;
		}
		if (pathOperationListElement == null) {
			if (other.pathOperationListElement != null) {
				return false;
			}
		}
		else if (!pathOperationListElement.equals(other.pathOperationListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		nameElement.dispose();
		pathOperationListElement.dispose();
		super.dispose();
	}
}
