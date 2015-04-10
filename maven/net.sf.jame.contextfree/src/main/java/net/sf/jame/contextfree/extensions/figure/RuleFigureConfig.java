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
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.CFDGBuilder;
import net.sf.jame.contextfree.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class RuleFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;
	private StringElement nameElement;
	private FloatElement probabilityElement;
	private ListConfigElement<ShapeReplacementConfigElement> shapeReplacementListElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		nameElement = new StringElement("");
		probabilityElement = new FloatElement(1f);
		shapeReplacementListElement = new ListConfigElement<ShapeReplacementConfigElement>("shapeReplacementListElement");
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(nameElement);
		elements.add(probabilityElement);
		elements.add(shapeReplacementListElement);
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
	public FloatElement getProbabilityElement() {
		return probabilityElement;
	}
	
	/**
	 * @return
	 */
	public Float getProbability() {
		return probabilityElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setProbability(final Float value) {
		probabilityElement.setValue(value);
	}
	/**
	 * @return
	 */
	public ListConfigElement<ShapeReplacementConfigElement> getShapeReplacementListElement() {
		return shapeReplacementListElement;
	}

	/**
	 * Returns a shapeReplacement element.
	 * 
	 * @param index the shapeReplacement index.
	 * @return the shapeReplacement.
	 */
	public ShapeReplacementConfigElement getShapeReplacementConfigElement(final int index) {
		return shapeReplacementListElement.getElement(index);
	}

	/**
	 * Returns a shapeReplacement element index.
	 * 
	 * @param shapeReplacementElement the shapeReplacement element.
	 * @return the index.
	 */
	public int indexOfShapeReplacementConfigElement(final ShapeReplacementConfigElement shapeReplacementElement) {
		return shapeReplacementListElement.indexOfElement(shapeReplacementElement);
	}

	/**
	 * Returns the number of shapeReplacement elements.
	 * 
	 * @return the number of shapeReplacement elements.
	 */
	public int getShapeReplacementConfigElementCount() {
		return shapeReplacementListElement.getElementCount();
	}

	/**
	 * Adds a shapeReplacement element.
	 * 
	 * @param shapeReplacementElement the shapeReplacement to add.
	 */
	public void appendShapeReplacementConfigElement(final ShapeReplacementConfigElement shapeReplacementElement) {
		shapeReplacementListElement.appendElement(shapeReplacementElement);
	}

	/**
	 * Adds a shapeReplacement element.
	 * 
	 * @param index the index.
	 * @param shapeReplacementElement the shapeReplacement to add.
	 */
	public void insertShapeReplacementConfigElementAfter(final int index, final ShapeReplacementConfigElement shapeReplacementElement) {
		shapeReplacementListElement.insertElementAfter(index, shapeReplacementElement);
	}

	/**
	 * Adds a shapeReplacement element.
	 * 
	 * @param index the index.
	 * @param shapeReplacementElement the shapeReplacement to add.
	 */
	public void insertShapeReplacementConfigElementBefore(final int index, final ShapeReplacementConfigElement shapeReplacementElement) {
		shapeReplacementListElement.insertElementBefore(index, shapeReplacementElement);
	}

	/**
	 * Removes a shapeReplacement element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removeShapeReplacementConfigElement(final int index) {
		shapeReplacementListElement.removeElement(index);
	}

	/**
	 * Removes a shapeReplacement element.
	 * 
	 * @param shapeReplacementElement the shapeReplacement to remove.
	 */
	public void removeShapeReplacementConfigElement(final ShapeReplacementConfigElement shapeReplacementElement) {
		shapeReplacementListElement.removeElement(shapeReplacementElement);
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
		final RuleFigureConfig other = (RuleFigureConfig) obj;
		if (nameElement == null) {
			if (other.nameElement != null) {
				return false;
			}
		}
		else if (!nameElement.equals(other.nameElement)) {
			return false;
		}
		if (probabilityElement == null) {
			if (other.probabilityElement != null) {
				return false;
			}
		}
		else if (!probabilityElement.equals(other.probabilityElement)) {
			return false;
		}
		if (shapeReplacementListElement == null) {
			if (other.shapeReplacementListElement != null) {
				return false;
			}
		}
		else if (!shapeReplacementListElement.equals(other.shapeReplacementListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public RuleFigureConfig clone() {
		final RuleFigureConfig config = new RuleFigureConfig();
		config.setName(getName());
		config.setProbability(getProbability());
		config.shapeReplacementListElement.copyFrom(getShapeReplacementListElement());
		return config;
	}

	@Override
	public void toCFDG(CFDGBuilder builder) {
		builder.append("rule ");
		builder.append(nameElement.getValue());
		if (probabilityElement.getValue() != 0) {
			builder.append(" ");
			builder.append(probabilityElement.getValue());
		}
		builder.append(" {\n");
		builder.addTab();
		for (int i = 0; i < shapeReplacementListElement.getElementCount(); i++) {
			shapeReplacementListElement.getElement(i).toCFDG(builder);
			builder.append("\n");
		}
		builder.removeTab();
		builder.append("}\n");
	}
}
