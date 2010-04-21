/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
 public class MultiReplacementRuntimeElement extends RuntimeElement {
	private MultiReplacementConfigElement multiReplacementElement;
	private Integer times;
	private TimesListener timesListener;
	private ShapeReplacementListElementListener shapeReplacementListElementListener;
	private ShapeAdjustmentListElementListener shapeAdjustmentListElementListener;

	/**
	 * Constructs a new MultiReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param MultiReplacementRuntimeElementElement
	 */
	public MultiReplacementRuntimeElement(final MultiReplacementConfigElement multiReplacementElement) {
		if (multiReplacementElement == null) {
			throw new IllegalArgumentException("multiReplacementElement is null");
		}
		this.multiReplacementElement = multiReplacementElement;
		setTimes(multiReplacementElement.getTimes());
		timesListener = new TimesListener();
		multiReplacementElement.getTimesElement().addChangeListener(timesListener);
		shapeReplacementListElementListener = new ShapeReplacementListElementListener();
		multiReplacementElement.getShapeReplacementListElement().addChangeListener(shapeReplacementListElementListener);
		shapeAdjustmentListElementListener = new ShapeAdjustmentListElementListener();
		multiReplacementElement.getShapeAdjustmentListElement().addChangeListener(shapeAdjustmentListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((multiReplacementElement != null) && (timesListener != null)) {
			multiReplacementElement.getTimesElement().removeChangeListener(timesListener);
		}
		timesListener = null;
		if ((multiReplacementElement != null) && (shapeReplacementListElementListener != null)) {
			multiReplacementElement.getShapeReplacementListElement().removeChangeListener(shapeReplacementListElementListener);
		}
		shapeReplacementListElementListener = null;
		if ((multiReplacementElement != null) && (shapeAdjustmentListElementListener != null)) {
			multiReplacementElement.getShapeAdjustmentListElement().removeChangeListener(shapeAdjustmentListElementListener);
		}
		shapeAdjustmentListElementListener = null;
		multiReplacementElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean multiReplacementChanged = false;
		return super.isChanged() || multiReplacementChanged;
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
	private class ShapeReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
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
}
