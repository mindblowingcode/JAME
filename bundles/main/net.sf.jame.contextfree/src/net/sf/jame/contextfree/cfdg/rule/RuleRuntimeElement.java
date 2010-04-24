/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ListConfigElement;
import net.sf.jame.core.config.ListRuntimeElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
 public class RuleRuntimeElement extends RuntimeElement {
	private RuleConfigElement ruleElement;
	private String name;
	private NameListener nameListener;
	private Float propability;
	private PropabilityListener propabilityListener;
	private ListRuntimeElement<ShapeReplacementRuntimeElement> shapeReplacementListElement;
	private ShapeReplacementListElementListener shapeReplacementListElementListener;

	/**
	 * Constructs a new RuleRuntimeElement.
	 * 
	 * @param registry
	 * @param RuleRuntimeElementElement
	 */
	public RuleRuntimeElement(final RuleConfigElement ruleElement) {
		if (ruleElement == null) {
			throw new IllegalArgumentException("ruleElement is null");
		}
		this.ruleElement = ruleElement;
		setName(ruleElement.getName());
		nameListener = new NameListener();
		ruleElement.getNameElement().addChangeListener(nameListener);
		setPropability(ruleElement.getPropability());
		propabilityListener = new PropabilityListener();
		ruleElement.getPropabilityElement().addChangeListener(propabilityListener);
		shapeReplacementListElement = new ListRuntimeElement<ShapeReplacementRuntimeElement>();
		for (int i = 0; i < ruleElement.getShapeReplacementConfigElementCount(); i++) {
			shapeReplacementListElement.appendElement(new ShapeReplacementRuntimeElement(ruleElement.getShapeReplacementConfigElement(i)));
		}
		shapeReplacementListElementListener = new ShapeReplacementListElementListener();
		ruleElement.getShapeReplacementListElement().addChangeListener(shapeReplacementListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((ruleElement != null) && (nameListener != null)) {
			ruleElement.getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((ruleElement != null) && (propabilityListener != null)) {
			ruleElement.getPropabilityElement().removeChangeListener(propabilityListener);
		}
		propabilityListener = null;
		if ((ruleElement != null) && (shapeReplacementListElementListener != null)) {
			ruleElement.getShapeReplacementListElement().removeChangeListener(shapeReplacementListElementListener);
		}
		shapeReplacementListElement.dispose();
		shapeReplacementListElementListener = null;
		ruleElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean ruleChanged = false;
		return super.isChanged() || ruleChanged;
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	private void setName(final String name) {
		this.name = name;
	}
	
	private class NameListener implements ValueChangeListener {
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
	 * @return the propability.
	 */
	public Float getPropability() {
		return propability;
	}

	private void setPropability(final Float propability) {
		this.propability = propability;
	}
	
	private class PropabilityListener implements ValueChangeListener {
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
	 * Returns a shapeReplacement element.
	 * 
	 * @param index the shapeReplacement index.
	 * @return the shapeReplacement.
	 */
	public ShapeReplacementRuntimeElement getShapeReplacementElement(final int index) {
		return shapeReplacementListElement.getElement(index);
	}

	/**
	 * Returns a shapeReplacement element index.
	 * 
	 * @param shapeReplacementElement the shapeReplacement element.
	 * @return the index.
	 */
	public int indexOfShapeReplacementElement(final ShapeReplacementRuntimeElement shapeReplacementElement) {
		return shapeReplacementListElement.indexOfElement(shapeReplacementElement);
	}

	/**
	 * Returns the number of shapeReplacement elements.
	 * 
	 * @return the number of shapeReplacement elements.
	 */
	public int getShapeReplacementElementCount() {
		return shapeReplacementListElement.getElementCount();
	}

	private void setShapeReplacementElement(final int index, ShapeReplacementRuntimeElement element) {
		shapeReplacementListElement.setElement(index, element);
	}

	private void appendShapeReplacementElement(final ShapeReplacementRuntimeElement shapeReplacementElement) {
		shapeReplacementListElement.appendElement(shapeReplacementElement);
	}

	private void insertShapeReplacementElementAfter(final int index, final ShapeReplacementRuntimeElement shapeReplacementElement) {
		shapeReplacementListElement.insertElementAfter(index, shapeReplacementElement);
	}

	private void insertShapeReplacementElementBefore(final int index, final ShapeReplacementRuntimeElement shapeReplacementElement) {
		shapeReplacementListElement.insertElementBefore(index, shapeReplacementElement);
	}

	private void removeShapeReplacementElement(final int index) {
		shapeReplacementListElement.removeElement(index);
	}

	private void moveUpShapeReplacementElement(final int index) {
		shapeReplacementListElement.moveElementUp(index);
	}

	private void moveDownShapeReplacementElement(final int index) {
		shapeReplacementListElement.moveElementDown(index);
	}
	
	private class ShapeReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					appendShapeReplacementElement(new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					insertShapeReplacementElementAfter(((Integer) e.getParams()[1]).intValue(), new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					insertShapeReplacementElementBefore(((Integer) e.getParams()[1]).intValue(), new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					removeShapeReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpShapeReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownShapeReplacementElement(((Integer) e.getParams()[1]).intValue());
					fireChanged();
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					setShapeReplacementElement(((Integer) e.getParams()[1]).intValue(), new ShapeReplacementRuntimeElement ((ShapeReplacementConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	
	public void draw(ContextFreeContext contextFreeContext) {
		for (int i = 0; i < shapeReplacementListElement.getElementCount(); i++) {
			ShapeReplacementRuntimeElement shapeReplacementRuntime = shapeReplacementListElement.getElement(i); 
			shapeReplacementRuntime.draw(contextFreeContext);
		}
	}
	
	public void prepare(ContextFreeContext contextFreeContext) {
		for (int i = 0; i < shapeReplacementListElement.getElementCount(); i++) {
			ShapeReplacementRuntimeElement shapeReplacementRuntime = shapeReplacementListElement.getElement(i); 
			shapeReplacementRuntime.prepare(contextFreeContext);
		}
	}
}
