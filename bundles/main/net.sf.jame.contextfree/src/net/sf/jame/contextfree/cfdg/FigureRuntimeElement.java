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
package net.sf.jame.contextfree.cfdg;

import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
 public abstract class FigureRuntimeElement extends RuntimeElement {
	private FigureConfigElement figureElement;
	private String name;
	private NameListener nameListener;

	/**
	 * Constructs a new ReplacementRuntimeElement.
	 * 
	 * @param registry
	 * @param FigureRuntimeElementElement
	 */
	public FigureRuntimeElement(final FigureConfigElement figureElement) {
		if (figureElement == null) {
			throw new IllegalArgumentException("figureElement is null");
		}
		this.figureElement = figureElement;
		setName(figureElement.getName());
		nameListener = new NameListener();
		figureElement.getNameElement().addChangeListener(nameListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((figureElement != null) && (nameListener != null)) {
			figureElement.getNameElement().removeChangeListener(nameListener);
		}
		nameListener = null;
		figureElement = null;
		super.dispose();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean figureChanged = false;
		return super.isChanged() || figureChanged;
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
	 * @param contextFreeContext
	 */
	public abstract void draw(ContextFreeContext contextFreeContext);

	/**
	 * @return
	 */
	public FigureConfigElement getFigureElement() {
		return figureElement;
	}
}
