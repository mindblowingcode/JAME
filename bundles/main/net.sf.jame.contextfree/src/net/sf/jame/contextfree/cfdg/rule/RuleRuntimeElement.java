/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
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
	private class ShapeReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
	
	public void draw(ContextFreeContext contextFreeContext) {
		// TODO Auto-generated method stub
		
	}
}
