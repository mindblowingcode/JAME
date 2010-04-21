/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class PathConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "Path";
	private final StringElement nameElement = new StringElement("");
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
