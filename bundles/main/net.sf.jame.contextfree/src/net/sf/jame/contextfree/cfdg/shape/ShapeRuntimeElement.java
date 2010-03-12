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
package net.sf.jame.contextfree.cfdg.shape;

import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * @author Andrea Medeghini
 */
public class ShapeRuntimeElement extends RuntimeElement {
	private ShapeConfigElement shapeElement;
	private ReplacementListElementListener replacementListElementListener;

	/**
	 * Constructs a new ShapeRuntimeElement.
	 * 
	 * @param registry
	 * @param ShapeRuntimeElementElement
	 */
	public ShapeRuntimeElement(final ShapeConfigElement shapeElement) {
		if (shapeElement == null) {
			throw new IllegalArgumentException("shapeElement is null");
		}
		this.shapeElement = shapeElement;
		replacementListElementListener = new ReplacementListElementListener();
		shapeElement.getReplacementListElement().addChangeListener(replacementListElementListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((shapeElement != null) && (replacementListElementListener != null)) {
			shapeElement.getReplacementListElement().removeChangeListener(replacementListElementListener);
		}
		replacementListElementListener = null;
		shapeElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean shapeChanged = false;
		return super.isChanged() || shapeChanged;
	}

	private class ReplacementListElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			fireChanged();
		}
	}
}
