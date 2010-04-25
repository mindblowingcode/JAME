/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
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
	private FloatElement propabilityElement;
	private ListConfigElement<ShapeReplacementConfigElement> shapeReplacementListElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		nameElement = new StringElement("");
		propabilityElement = new FloatElement(1f);
		shapeReplacementListElement = new ListConfigElement<ShapeReplacementConfigElement>("shapeReplacement");
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(nameElement);
		elements.add(propabilityElement);
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
	public FloatElement getPropabilityElement() {
		return propabilityElement;
	}
	
	/**
	 * @return
	 */
	public Float getPropability() {
		return propabilityElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setPropability(final Float value) {
		propabilityElement.setValue(value);
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
		if (propabilityElement == null) {
			if (other.propabilityElement != null) {
				return false;
			}
		}
		else if (!propabilityElement.equals(other.propabilityElement)) {
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
		config.setPropability(getPropability());
		config.shapeReplacementListElement.copyFrom(getShapeReplacementListElement());
		return config;
	}
}
