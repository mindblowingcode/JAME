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
 public class ShapeReplacementRuntimeElement extends ReplacementRuntimeElement {
	private ShapeReplacementConfigElement shapeReplacementElement;
	private String name;
	private NameListener nameListener;
	private ShapeAdjustmentListElementListener shapeAdjustmentListElementListener;

	/**
	 * Constructs a new ShapeReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param ShapeReplacementRuntimeElementElement
	 */
	public ShapeReplacementRuntimeElement(final ShapeReplacementConfigElement shapeReplacementElement) {
		super(shapeReplacementElement);
		if (shapeReplacementElement == null) {
			throw new IllegalArgumentException("shapeReplacementElement is null");
		}
		this.shapeReplacementElement = shapeReplacementElement;
		setName(shapeReplacementElement.getName());
		nameListener = new NameListener();
		shapeReplacementElement.getNameElement().addChangeListener(nameListener);
		shapeAdjustmentListElementListener = new ShapeAdjustmentListElementListener();
		shapeReplacementElement.getShapeAdjustmentListElement().addChangeListener(shapeAdjustmentListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((shapeReplacementElement != null) && (nameListener != null)) {
			shapeReplacementElement.getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		if ((shapeReplacementElement != null) && (shapeAdjustmentListElementListener != null)) {
			shapeReplacementElement.getShapeAdjustmentListElement().removeChangeListener(shapeAdjustmentListElementListener);
		}
		shapeAdjustmentListElementListener = null;
		shapeReplacementElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean shapeReplacementChanged = false;
		return super.isChanged() || shapeReplacementChanged;
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
	private class ShapeAdjustmentListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
}
