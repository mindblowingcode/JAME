/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.ArrayList;
import java.util.List;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathFigureConfig extends FigureExtensionConfig {
	private static final long serialVersionUID = 1L;
	private StringElement nameElement;
	private ListConfigElement<PathOperationConfigElement> pathOperationListElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		nameElement = new StringElement("");
		pathOperationListElement = new ListConfigElement<PathOperationConfigElement>("pathOperation");
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(nameElement);
		elements.add(pathOperationListElement);
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
		final PathFigureConfig other = (PathFigureConfig) obj;
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
	 * @return
	 */
	@Override
	public PathFigureConfig clone() {
		final PathFigureConfig config = new PathFigureConfig();
		config.setName(getName());
		config.pathOperationListElement.copyFrom(getPathOperationListElement());
		return config;
	}
}
