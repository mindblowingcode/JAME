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
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.contextfree.cfdg.FigureRuntimeElement;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class RuleRuntimeElement extends FigureRuntimeElement {
	private RuleConfigElement ruleElement;
	private Float propability;
	private PropabilityListener propabilityListener;
	private ShapeSingleElementListener shapeSingleElementListener;

	/**
	 * Constructs a new RuleRuntimeElement.
	 * 
	 * @param registry
	 * @param RuleRuntimeElementElement
	 */
	public RuleRuntimeElement(final RuleConfigElement ruleElement) {
		super(ruleElement);
		this.ruleElement = ruleElement;
		setPropability(ruleElement.getPropability());
		propabilityListener = new PropabilityListener();
		ruleElement.getPropabilityElement().addChangeListener(propabilityListener);
		shapeSingleElementListener = new ShapeSingleElementListener();
		ruleElement.getShapeSingleElement().addChangeListener(shapeSingleElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((ruleElement != null) && (propabilityListener != null)) {
			ruleElement.getPropabilityElement().removeChangeListener(propabilityListener);
		}
		propabilityListener = null;
		if ((ruleElement != null) && (shapeSingleElementListener != null)) {
			ruleElement.getShapeSingleElement().removeChangeListener(shapeSingleElementListener);
		}
		shapeSingleElementListener = null;
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

	private class ShapeSingleElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}

	/**
	 * @param contextFreeContext
	 */
	public void draw(ContextFreeContext contextFreeContext) {
	}
}
