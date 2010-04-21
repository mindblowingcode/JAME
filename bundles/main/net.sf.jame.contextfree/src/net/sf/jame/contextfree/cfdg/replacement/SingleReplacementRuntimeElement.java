/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import java.lang.String;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentRuntimeElement;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
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
	private ListRuntimeElement<ShapeAdjustmentRuntimeElement> shapeAdjustmentListElement;
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
		shapeAdjustmentListElement = new ListRuntimeElement<ShapeAdjustmentRuntimeElement>();
		for (int i = 0; i < singleReplacementElement.getShapeAdjustmentConfigElementCount(); i++) {
			shapeAdjustmentListElement.appendElement(new ShapeAdjustmentRuntimeElement(singleReplacementElement.getShapeAdjustmentConfigElement(i)));
		}
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
		shapeAdjustmentListElement.dispose();
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
	/**
	 * Returns a shapeAdjustment element.
	 * 
	 * @param index the shapeAdjustment index.
	 * @return the shapeAdjustment.
	 */
	public ShapeAdjustmentRuntimeElement getShapeAdjustmentElement(final int index) {
		return shapeAdjustmentListElement.getElement(index);
	}

	/**
	 * Returns a shapeAdjustment element index.
	 * 
	 * @param shapeAdjustmentElement the shapeAdjustment element.
	 * @return the index.
	 */
	public int indexOfShapeAdjustmentElement(final ShapeAdjustmentRuntimeElement shapeAdjustmentElement) {
		return shapeAdjustmentListElement.indexOfElement(shapeAdjustmentElement);
	}

	/**
	 * Returns the number of shapeAdjustment elements.
	 * 
	 * @return the number of shapeAdjustment elements.
	 */
	public int getShapeAdjustmentElementCount() {
		return shapeAdjustmentListElement.getElementCount();
	}

	private void setShapeAdjustmentElement(final int index, ShapeAdjustmentRuntimeElement element) {
		shapeAdjustmentListElement.setElement(index, element);
	}

	private void appendShapeAdjustmentElement(final ShapeAdjustmentRuntimeElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.appendElement(shapeAdjustmentElement);
	}

	private void insertShapeAdjustmentElementAfter(final int index, final ShapeAdjustmentRuntimeElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.insertElementAfter(index, shapeAdjustmentElement);
	}

	private void insertShapeAdjustmentElementBefore(final int index, final ShapeAdjustmentRuntimeElement shapeAdjustmentElement) {
		shapeAdjustmentListElement.insertElementBefore(index, shapeAdjustmentElement);
	}

	private void removeShapeAdjustmentElement(final int index) {
		shapeAdjustmentListElement.removeElement(index);
	}

	private void moveUpShapeAdjustmentElement(final int index) {
		shapeAdjustmentListElement.moveElementUp(index);
	}

	private void moveDownShapeAdjustmentElement(final int index) {
		shapeAdjustmentListElement.moveElementDown(index);
	}
	
	private class ShapeAdjustmentListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendShapeAdjustmentElement(new ShapeAdjustmentRuntimeElement ((ShapeAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertShapeAdjustmentElementAfter(((Integer) e.getParams()[1]).intValue(), new ShapeAdjustmentRuntimeElement ((ShapeAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertShapeAdjustmentElementBefore(((Integer) e.getParams()[1]).intValue(), new ShapeAdjustmentRuntimeElement ((ShapeAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removeShapeAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpShapeAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownShapeAdjustmentElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setShapeAdjustmentElement(((Integer) e.getParams()[1]).intValue(), new ShapeAdjustmentRuntimeElement ((ShapeAdjustmentConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
