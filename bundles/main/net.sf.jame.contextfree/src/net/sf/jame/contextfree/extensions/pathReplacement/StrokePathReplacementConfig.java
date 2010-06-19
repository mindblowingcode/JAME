/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.extension.PathReplacementExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class StrokePathReplacementConfig extends PathReplacementExtensionConfig {
	private static final long serialVersionUID = 1L;
	private StringElement cupElement;
	private StringElement joinElement;
	private ListConfigElement<PathAdjustmentConfigElement> pathAdjustmentListElement;

	/**
	 * 
	 */
	@Override
	protected void createConfigElements() {
		cupElement = new StringElement("butt");
		joinElement = new StringElement("miter");
		pathAdjustmentListElement = new ListConfigElement<PathAdjustmentConfigElement>("pathAdjustment");
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionConfig#getConfigElements()
	 */
	@Override
	public List<ConfigElement> getConfigElements() {
		final List<ConfigElement> elements = new ArrayList<ConfigElement>(1);
		elements.add(cupElement);
		elements.add(joinElement);
		elements.add(pathAdjustmentListElement);
		return elements;
	}

	/**
	 * @return
	 */
	public StringElement getCupElement() {
		return cupElement;
	}
	
	/**
	 * @return
	 */
	public String getCup() {
		return cupElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setCup(final String value) {
		cupElement.setValue(value);
	}
	/**
	 * @return
	 */
	public StringElement getJoinElement() {
		return joinElement;
	}
	
	/**
	 * @return
	 */
	public String getJoin() {
		return joinElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setJoin(final String value) {
		joinElement.setValue(value);
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
		final StrokePathReplacementConfig other = (StrokePathReplacementConfig) obj;
		if (cupElement == null) {
			if (other.cupElement != null) {
				return false;
			}
		}
		else if (!cupElement.equals(other.cupElement)) {
			return false;
		}
		if (joinElement == null) {
			if (other.joinElement != null) {
				return false;
			}
		}
		else if (!joinElement.equals(other.joinElement)) {
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
	 * @return
	 */
	@Override
	public StrokePathReplacementConfig clone() {
		final StrokePathReplacementConfig config = new StrokePathReplacementConfig();
		config.setCup(getCup());
		config.setJoin(getJoin());
		config.pathAdjustmentListElement.copyFrom(getPathAdjustmentListElement());
		return config;
	}
}
