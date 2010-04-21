/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.config.AbstractConfigElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;

/**
 * @author Andrea Medeghini
 */
public class SingleReplacementConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "SingleReplacement";
	private final StringElement shapeElement = new StringElement("");
	private final ListConfigElement<ShapeAdjustmentConfigElement> shapeAdjustmentListElement = new ListConfigElement<ShapeAdjustmentConfigElement>("shapeAdjustment");

	/**
	 * Constructs a new element.
	 */
	public SingleReplacementConfigElement() {
		super(SingleReplacementConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		shapeElement.setContext(context);
		shapeAdjustmentListElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public SingleReplacementConfigElement clone() {
		final SingleReplacementConfigElement element = new SingleReplacementConfigElement();
		element.setShape(getShape());
		element.shapeAdjustmentListElement.copyFrom(getShapeAdjustmentListElement());
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		SingleReplacementConfigElement singleReplacementElement = (SingleReplacementConfigElement) source;
		setShape(singleReplacementElement.getShape());
		shapeAdjustmentListElement.copyFrom(singleReplacementElement.getShapeAdjustmentListElement());
	}

	/**
	 * @return
	 */
	public StringElement getShapeElement() {
		return shapeElement;
	}
	
	/**
	 * @return
	 */
	public String getShape() {
		return shapeElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShape(final String value) {
		shapeElement.setValue(value);
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
		final SingleReplacementConfigElement other = (SingleReplacementConfigElement) obj;
		if (shapeElement == null) {
			if (other.shapeElement != null) {
				return false;
			}
		}
		else if (!shapeElement.equals(other.shapeElement)) {
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
		shapeElement.dispose();
		shapeAdjustmentListElement.dispose();
		super.dispose();
	}
}
