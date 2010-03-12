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
package net.sf.jame.contextfree.cfdg.shape;

import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ShapeConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "Shape";
	private final ListConfigElement<ReplacementConfigElement> replacementListElement = new ListConfigElement<ReplacementConfigElement>("Replacement");

	/**
	 * Constructs a new element.
	 */
	public ShapeConfigElement() {
		super(ShapeConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		replacementListElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public ShapeConfigElement clone() {
		final ShapeConfigElement element = new ShapeConfigElement();
		element.replacementListElement.copyFrom(getReplacementListElement());
		return element;
	}

	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		ShapeConfigElement shapeElement = (ShapeConfigElement) source;
		replacementListElement.copyFrom(shapeElement.getReplacementListElement());
	}

	/**
	 * @return
	 */
	public ListConfigElement<ReplacementConfigElement> getReplacementListElement() {
		return replacementListElement;
	}

	/**
	 * Returns a Replacement element.
	 * 
	 * @param index the Replacement index.
	 * @return the Replacement.
	 */
	public ReplacementConfigElement getReplacementConfigElement(final int index) {
		return replacementListElement.getElement(index);
	}

	/**
	 * Returns a Replacement element index.
	 * 
	 * @param replacementElement the Replacement element.
	 * @return the index.
	 */
	public int indexOfReplacementConfigElement(final ReplacementConfigElement replacementElement) {
		return replacementListElement.indexOfElement(replacementElement);
	}

	/**
	 * Returns the number of Replacement elements.
	 * 
	 * @return the number of Replacement elements.
	 */
	public int getReplacementConfigElementCount() {
		return replacementListElement.getElementCount();
	}

	/**
	 * Adds a Replacement element.
	 * 
	 * @param replacementElement the Replacement to add.
	 */
	public void appendReplacementConfigElement(final ReplacementConfigElement replacementElement) {
		replacementListElement.appendElement(replacementElement);
	}

	/**
	 * Adds a Replacement element.
	 * 
	 * @param index the index.
	 * @param replacementElement the Replacement to add.
	 */
	public void insertReplacementConfigElementAfter(final int index, final ReplacementConfigElement replacementElement) {
		replacementListElement.insertElementAfter(index, replacementElement);
	}

	/**
	 * Adds a Replacement element.
	 * 
	 * @param index the index.
	 * @param replacementElement the Replacement to add.
	 */
	public void insertReplacementConfigElementBefore(final int index, final ReplacementConfigElement replacementElement) {
		replacementListElement.insertElementBefore(index, replacementElement);
	}

	/**
	 * Removes a Replacement element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removeReplacementConfigElement(final int index) {
		replacementListElement.removeElement(index);
	}

	/**
	 * Removes a Replacement element.
	 * 
	 * @param replacementElement the Replacement to remove.
	 */
	public void removeReplacementConfigElement(final ReplacementConfigElement replacementElement) {
		replacementListElement.removeElement(replacementElement);
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
		final ShapeConfigElement other = (ShapeConfigElement) obj;
		if (replacementListElement == null) {
			if (other.replacementListElement != null) {
				return false;
			}
		}
		else if (!replacementListElement.equals(other.replacementListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		replacementListElement.dispose();
		super.dispose();
	}
}
