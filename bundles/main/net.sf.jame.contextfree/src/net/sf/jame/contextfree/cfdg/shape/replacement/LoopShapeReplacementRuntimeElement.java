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

import net.sf.jame.contextfree.cfdg.shape.ReplacementRuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
 public class LoopShapeReplacementRuntimeElement extends ReplacementRuntimeElement {
	private LoopShapeReplacementConfigElement loopShapeReplacementElement;
	private Integer times;
	private TimesListener timesListener;
	private ShapeAdjustmentListElementListener shapeAdjustmentListElementListener;
	private ShapeSingleElementListener shapeSingleElementListener;

	/**
	 * Constructs a new LoopShapeReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param LoopShapeReplacementRuntimeElementElement
	 */
	public LoopShapeReplacementRuntimeElement(final LoopShapeReplacementConfigElement loopShapeReplacementElement) {
		super(loopShapeReplacementElement);
		if (loopShapeReplacementElement == null) {
			throw new IllegalArgumentException("loopShapeReplacementElement is null");
		}
		this.loopShapeReplacementElement = loopShapeReplacementElement;
		setTimes(loopShapeReplacementElement.getTimes());
		timesListener = new TimesListener();
		loopShapeReplacementElement.getTimesElement().addChangeListener(timesListener);
		shapeAdjustmentListElementListener = new ShapeAdjustmentListElementListener();
		loopShapeReplacementElement.getShapeAdjustmentListElement().addChangeListener(shapeAdjustmentListElementListener);
		shapeSingleElementListener = new ShapeSingleElementListener();
		loopShapeReplacementElement.getShapeSingleElement().addChangeListener(shapeSingleElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((loopShapeReplacementElement != null) && (timesListener != null)) {
			loopShapeReplacementElement.getTimesElement().removeChangeListener(timesListener);
		}
		timesListener = null;
		if ((loopShapeReplacementElement != null) && (shapeAdjustmentListElementListener != null)) {
			loopShapeReplacementElement.getShapeAdjustmentListElement().removeChangeListener(shapeAdjustmentListElementListener);
		}
		shapeAdjustmentListElementListener = null;
		if ((loopShapeReplacementElement != null) && (shapeSingleElementListener != null)) {
			loopShapeReplacementElement.getShapeSingleElement().removeChangeListener(shapeSingleElementListener);
		}
		shapeSingleElementListener = null;
		loopShapeReplacementElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean loopShapeReplacementChanged = false;
		return super.isChanged() || loopShapeReplacementChanged;
	}

	/**
	 * @return the times.
	 */
	public Integer getTimes() {
		return times;
	}

	private void setTimes(final Integer times) {
		this.times = times;
	}
	
	private class TimesListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	private class ShapeAdjustmentListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
	private class ShapeSingleElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
}
