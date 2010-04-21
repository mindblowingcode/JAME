/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class PathOperationConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "PathOperation";
	private final ConfigurableExtensionReferenceElement<PathOperationExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<PathOperationExtensionConfig>();
	private final ListConfigElement<PathAdjustmentConfigElement> pathAdjustmentListElement = new ListConfigElement<PathAdjustmentConfigElement>("pathAdjustment");

	/**
	 * Constructs a new element.
	 */
	public PathOperationConfigElement() {
		super(PathOperationConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		extensionElement.setContext(context);
		pathAdjustmentListElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public PathOperationConfigElement clone() {
		final PathOperationConfigElement element = new PathOperationConfigElement();
		if (getExtensionReference() != null) {
			element.setExtensionReference(getExtensionReference().clone());
		}
		element.pathAdjustmentListElement.copyFrom(getPathAdjustmentListElement());
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		PathOperationConfigElement pathOperationElement = (PathOperationConfigElement) source;
		if (pathOperationElement.getExtensionReference() != null) {
			setExtensionReference(pathOperationElement.getExtensionReference().clone());
		}
		pathAdjustmentListElement.copyFrom(pathOperationElement.getPathAdjustmentListElement());
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<PathOperationExtensionConfig> getExtensionElement() {
		return extensionElement;
	}
	
	/**
	 * @return
	 */
	public ConfigurableExtensionReference<PathOperationExtensionConfig> getExtensionReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setExtensionReference(final ConfigurableExtensionReference<PathOperationExtensionConfig> reference) {
		extensionElement.setReference(reference);
	}
	/**
	 * @return
	 */
	public ListConfigElement<PathAdjustmentConfigElement> getPathAdjustmentListElement() {
		return pathAdjustmentListElement;
	}

	/**
	 * Returns a pathAdjustment element.
	 * 
	 * @param index the pathAdjustment index.
	 * @return the pathAdjustment.
	 */
	public PathAdjustmentConfigElement getPathAdjustmentConfigElement(final int index) {
		return pathAdjustmentListElement.getElement(index);
	}

	/**
	 * Returns a pathAdjustment element index.
	 * 
	 * @param pathAdjustmentElement the pathAdjustment element.
	 * @return the index.
	 */
	public int indexOfPathAdjustmentConfigElement(final PathAdjustmentConfigElement pathAdjustmentElement) {
		return pathAdjustmentListElement.indexOfElement(pathAdjustmentElement);
	}

	/**
	 * Returns the number of pathAdjustment elements.
	 * 
	 * @return the number of pathAdjustment elements.
	 */
	public int getPathAdjustmentConfigElementCount() {
		return pathAdjustmentListElement.getElementCount();
	}

	/**
	 * Adds a pathAdjustment element.
	 * 
	 * @param pathAdjustmentElement the pathAdjustment to add.
	 */
	public void appendPathAdjustmentConfigElement(final PathAdjustmentConfigElement pathAdjustmentElement) {
		pathAdjustmentListElement.appendElement(pathAdjustmentElement);
	}

	/**
	 * Adds a pathAdjustment element.
	 * 
	 * @param index the index.
	 * @param pathAdjustmentElement the pathAdjustment to add.
	 */
	public void insertPathAdjustmentConfigElementAfter(final int index, final PathAdjustmentConfigElement pathAdjustmentElement) {
		pathAdjustmentListElement.insertElementAfter(index, pathAdjustmentElement);
	}

	/**
	 * Adds a pathAdjustment element.
	 * 
	 * @param index the index.
	 * @param pathAdjustmentElement the pathAdjustment to add.
	 */
	public void insertPathAdjustmentConfigElementBefore(final int index, final PathAdjustmentConfigElement pathAdjustmentElement) {
		pathAdjustmentListElement.insertElementBefore(index, pathAdjustmentElement);
	}

	/**
	 * Removes a pathAdjustment element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removePathAdjustmentConfigElement(final int index) {
		pathAdjustmentListElement.removeElement(index);
	}

	/**
	 * Removes a pathAdjustment element.
	 * 
	 * @param pathAdjustmentElement the pathAdjustment to remove.
	 */
	public void removePathAdjustmentConfigElement(final PathAdjustmentConfigElement pathAdjustmentElement) {
		pathAdjustmentListElement.removeElement(pathAdjustmentElement);
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
		final PathOperationConfigElement other = (PathOperationConfigElement) obj;
		if (extensionElement == null) {
			if (other.extensionElement != null) {
				return false;
			}
		}
		else if (!extensionElement.equals(other.extensionElement)) {
			return false;
		}
		if (pathAdjustmentListElement == null) {
			if (other.pathAdjustmentListElement != null) {
				return false;
			}
		}
		else if (!pathAdjustmentListElement.equals(other.pathAdjustmentListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		extensionElement.dispose();
		pathAdjustmentListElement.dispose();
		super.dispose();
	}
}
