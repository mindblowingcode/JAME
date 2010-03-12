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
package net.sf.jame.contextfree.cfdg.shape.replacement;

import net.sf.jame.contextfree.cfdg.shape.ReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElement;
import net.sf.jame.contextfree.cfdg.shape.adjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.config.ConfigContext;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.SingleConfigElement;

/**
 * @author Andrea Medeghini
 */
public class LoopShapeReplacementConfigElement extends ReplacementConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "LoopShapeReplacement";
	private final IntegerElement timesElement = new IntegerElement(1);
	private final ListConfigElement<ShapeAdjustmentConfigElement> shapeAdjustmentListElement = new ListConfigElement<ShapeAdjustmentConfigElement>("shapeAdjustment");
	private final SingleConfigElement<ShapeConfigElement> shapeSingleElement = new SingleConfigElement<ShapeConfigElement>("shape");

	/**
	 * Constructs a new element.
	 */
	public LoopShapeReplacementConfigElement() {
		super(LoopShapeReplacementConfigElement.CLASS_ID);
	}

	/**
	 * @see net.sf.jame.core.config.AbstractConfigElement#setContext(net.sf.jame.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		timesElement.setContext(context);
		shapeAdjustmentListElement.setContext(context);
		shapeSingleElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public LoopShapeReplacementConfigElement clone() {
		final LoopShapeReplacementConfigElement element = new LoopShapeReplacementConfigElement();
		element.setTimes(getTimes());
		element.shapeAdjustmentListElement.copyFrom(getShapeAdjustmentListElement());
		element.shapeSingleElement.copyFrom(getShapeSingleElement());
		return element;
	}
	
	/**
	 *
	 */
	public void copyFrom(ConfigElement source) {
		LoopShapeReplacementConfigElement loopShapeReplacementElement = (LoopShapeReplacementConfigElement) source;
		setTimes(loopShapeReplacementElement.getTimes());
		shapeAdjustmentListElement.copyFrom(loopShapeReplacementElement.getShapeAdjustmentListElement());
		shapeSingleElement.copyFrom(loopShapeReplacementElement.getShapeSingleElement());
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
	 * @return
	 */
	public SingleConfigElement<ShapeConfigElement> getShapeSingleElement() {
		return shapeSingleElement;
	}

	/**
	 * @return
	 */
	public ShapeConfigElement getShapeConfigElement() {
		return shapeSingleElement.getValue();
	}

	/**
	 * @param value
	 */
	public void setShapeConfigElement(ShapeConfigElement element) {
		shapeSingleElement.setValue(element);
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
		final LoopShapeReplacementConfigElement other = (LoopShapeReplacementConfigElement) obj;
		if (timesElement == null) {
			if (other.timesElement != null) {
				return false;
			}
		}
		else if (!timesElement.equals(other.timesElement)) {
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
		if (shapeSingleElement == null) {
			if (other.shapeSingleElement != null) {
				return false;
			}
		}
		else if (!shapeSingleElement.equals(other.shapeSingleElement)) {
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
		shapeAdjustmentListElement.dispose();
		shapeSingleElement.dispose();
		super.dispose();
	}
}
