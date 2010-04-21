/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class MultiReplacementConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "multiReplacement";
	private final IntegerElement timesElement = new IntegerElement(1);
	private final ListConfigElement<ShapeReplacementConfigElement> shapeReplacementListElement = new ListConfigElement<ShapeReplacementConfigElement>("shapeReplacement");
	private final ListConfigElement<ShapeAdjustmentConfigElement> shapeAdjustmentListElement = new ListConfigElement<ShapeAdjustmentConfigElement>("shapeAdjustment");

	/**
	 * Constructs a new element.
	 */
	public MultiReplacementConfigElement() {
		super(MultiReplacementConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		timesElement.setContext(context);
		shapeReplacementListElement.setContext(context);
		shapeAdjustmentListElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public MultiReplacementConfigElement clone() {
		final MultiReplacementConfigElement element = new MultiReplacementConfigElement();
		element.setTimes(getTimes());
		element.shapeReplacementListElement.copyFrom(getShapeReplacementListElement());
		element.shapeAdjustmentListElement.copyFrom(getShapeAdjustmentListElement());
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		MultiReplacementConfigElement multiReplacementElement = (MultiReplacementConfigElement) source;
		setTimes(multiReplacementElement.getTimes());
		shapeReplacementListElement.copyFrom(multiReplacementElement.getShapeReplacementListElement());
		shapeAdjustmentListElement.copyFrom(multiReplacementElement.getShapeAdjustmentListElement());
	}

	/**
	 * @return
	 */
	public IntegerElement getTimesElement() {
		return timesElement;
	}
	
	/**
	 * @return
	 */
	public Integer getTimes() {
		return timesElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setTimes(final Integer value) {
		timesElement.setValue(value);
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
	 * @return
	 */
	public ListConfigElement<ShapeAdjustmentConfigElement> getShapeAdjustmentListElement() {
		return shapeAdjustmentListElement;
	}

	/**
	 * Returns a shapeAdjustment element.
	 * 
	 * @param index the shapeAdjustment index.
	 * @return the shapeAdjustment.
	 */
	public ShapeAdjustmentConfigElement getShapeAdjustmentConfigElement(final int index) {
		return shapeAdjustmentListElement.getElement(index);
	}

	/**
	 * Returns a shapeAdjustment element index.
	 * 
	 * @param shapeAdjustmentElement the shapeAdjustment element.
	 * @return the index.
	 */
	public int indexOfShapeAdjustmentConfigElement(final ShapeAdjustmentConfigElement shapeAdjustmentElement) {
		return shapeAdjustmentListElement.indexOfElement(shapeAdjustmentElement);
	}

	/**
	 * Returns the number of shapeAdjustment elements.
	 * 
	 * @return the number of shapeAdjustment elements.
	 */
	public int getShapeAdjustmentConfigElementCount() {
		return shapeAdjustmentListElement.getElementCount();
	}

	/**
	 * Adds a shapeAdjustment element.
	 * 
	 * @param shapeAdjustmentElement the shapeAdjustment to add.
	 */
	public void appendShapeAdjustmentConfigElement(final ShapeAdjustmentConfigElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.appendElement(shapeAdjustmentElement);
	}

	/**
	 * Adds a shapeAdjustment element.
	 * 
	 * @param index the index.
	 * @param shapeAdjustmentElement the shapeAdjustment to add.
	 */
	public void insertShapeAdjustmentConfigElementAfter(final int index, final ShapeAdjustmentConfigElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.insertElementAfter(index, shapeAdjustmentElement);
	}

	/**
	 * Adds a shapeAdjustment element.
	 * 
	 * @param index the index.
	 * @param shapeAdjustmentElement the shapeAdjustment to add.
	 */
	public void insertShapeAdjustmentConfigElementBefore(final int index, final ShapeAdjustmentConfigElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.insertElementBefore(index, shapeAdjustmentElement);
	}

	/**
	 * Removes a shapeAdjustment element.
	 * 
	 * @param index the element index to remove.
	 */
	public void removeShapeAdjustmentConfigElement(final int index) {
		shapeAdjustmentListElement.removeElement(index);
	}

	/**
	 * Removes a shapeAdjustment element.
	 * 
	 * @param shapeAdjustmentElement the shapeAdjustment to remove.
	 */
	public void removeShapeAdjustmentConfigElement(final ShapeAdjustmentConfigElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.removeElement(shapeAdjustmentElement);
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
		final MultiReplacementConfigElement other = (MultiReplacementConfigElement) obj;
		if (timesElement == null) {
			if (other.timesElement != null) {
				return false;
			}
		}
		else if (!timesElement.equals(other.timesElement)) {
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
		if (shapeAdjustmentListElement == null) {
			if (other.shapeAdjustmentListElement != null) {
				return false;
			}
		}
		else if (!shapeAdjustmentListElement.equals(other.shapeAdjustmentListElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		timesElement.dispose();
		shapeReplacementListElement.dispose();
		shapeAdjustmentListElement.dispose();
		super.dispose();
	}
}
