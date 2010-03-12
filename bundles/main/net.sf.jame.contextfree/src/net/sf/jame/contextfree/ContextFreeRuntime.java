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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.CFDGRuntimeElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeRuntime extends RuntimeElement {
	private ContextFreeConfig config;
	private CFDGRuntimeElement fractalElement;
	private FractalElementListener fractalListener;
	private ViewElementListener viewListener;
	private ElementListener elementListener;
	private boolean viewChanged;

	/**
	 * @param config
	 */
	public ContextFreeRuntime(final ContextFreeConfig config) {
		this.config = config;
		fractalElement = new CFDGRuntimeElement(config.getContextFreeFractal());
		fractalListener = new FractalElementListener();
		config.getFractalSingleElement().addChangeListener(fractalListener);
		viewListener = new ViewElementListener();
		config.getViewElement().addChangeListener(viewListener);
		elementListener = new ElementListener();
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		elementListener = null;
		if ((config != null) && (viewListener != null)) {
			config.getViewElement().removeChangeListener(viewListener);
		}
		viewListener = null;
		if ((config != null) && (fractalListener != null)) {
			config.getFractalSingleElement().removeChangeListener(fractalListener);
		}
		fractalListener = null;
		if (fractalElement != null) {
			fractalElement.dispose();
			fractalElement = null;
		}
		config = null;
		super.dispose();
	}

	/**
	 * @return
	 */
	public boolean isViewChanged() {
		final boolean fractalChanged = viewChanged;
		viewChanged = false;
		return fractalChanged;
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean fractalChanged = (fractalElement != null) && fractalElement.isChanged();
		return super.isChanged() || fractalChanged;
	}

	/**
	 * @return
	 */
	public CFDGRuntimeElement getContextFreeFractal() {
		return fractalElement;
	}

	private void setContextFreeFractal(final CFDGRuntimeElement fractalElement) {
		if (this.fractalElement != null) {
			this.fractalElement.dispose();
		}
		this.fractalElement = fractalElement;
	}

	private class FractalElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setContextFreeFractal(new CFDGRuntimeElement(config.getContextFreeFractal()));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class ViewElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					viewChanged = true;
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class ElementListener implements ValueChangeListener {
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
}
