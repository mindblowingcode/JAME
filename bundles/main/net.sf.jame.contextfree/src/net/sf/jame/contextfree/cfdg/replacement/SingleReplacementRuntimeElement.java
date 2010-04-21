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
 public class SingleReplacementRuntimeElement extends RuntimeElement {
	private SingleReplacementConfigElement singleReplacementElement;
	private String shape;
	private ShapeListener shapeListener;
	private ShapeAdjustmentListElementListener shapeAdjustmentListElementListener;

	/**
	 * Constructs a new SingleReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param SingleReplacementRuntimeElementElement
	 */
	public SingleReplacementRuntimeElement(final SingleReplacementConfigElement singleReplacementElement) {
		if (singleReplacementElement == null) {
			throw new IllegalArgumentException("singleReplacementElement is null");
		}
		this.singleReplacementElement = singleReplacementElement;
		setShape(singleReplacementElement.getShape());
		shapeListener = new ShapeListener();
		singleReplacementElement.getShapeElement().addChangeListener(shapeListener);
		shapeAdjustmentListElementListener = new ShapeAdjustmentListElementListener();
		singleReplacementElement.getShapeAdjustmentListElement().addChangeListener(shapeAdjustmentListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((singleReplacementElement != null) && (shapeListener != null)) {
			singleReplacementElement.getShapeElement().removeChangeListener(shapeListener);
		}
		shapeListener = null;
		if ((singleReplacementElement != null) && (shapeAdjustmentListElementListener != null)) {
			singleReplacementElement.getShapeAdjustmentListElement().removeChangeListener(shapeAdjustmentListElementListener);
		}
		shapeAdjustmentListElementListener = null;
		singleReplacementElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean singleReplacementChanged = false;
		return super.isChanged() || singleReplacementChanged;
	}

	/**
	 * @return the shape.
	 */
	public String getShape() {
		return shape;
	}

	private void setShape(final String shape) {
		this.shape = shape;
	}
	
	private class ShapeListener implements ValueChangeListener {
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
}
