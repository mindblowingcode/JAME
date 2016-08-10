/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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
	private CFDGRuntimeElement cfdgElement;
	private CFDGElementListener cfdgListener;
	private ViewElementListener viewListener;
	private ElementListener elementListener;
	private boolean viewChanged;

	/**
	 * @param config
	 */
	public ContextFreeRuntime(final ContextFreeConfig config) {
		this.config = config;
		cfdgElement = new CFDGRuntimeElement(config.getCFDG());
		cfdgListener = new CFDGElementListener();
		config.getCFDGSingleElement().addChangeListener(cfdgListener);
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
		if ((config != null) && (cfdgListener != null)) {
			config.getCFDGSingleElement().removeChangeListener(cfdgListener);
		}
		cfdgListener = null;
		if (cfdgElement != null) {
			cfdgElement.dispose();
			cfdgElement = null;
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
		final boolean fractalChanged = (cfdgElement != null) && cfdgElement.isChanged();
		return super.isChanged() || fractalChanged;
	}

	/**
	 * @return
	 */
	public CFDGRuntimeElement getCFDG() {
		return cfdgElement;
	}

	private void setCDFG(final CFDGRuntimeElement cfdgElement) {
		if (this.cfdgElement != null) {
			this.cfdgElement.dispose();
		}
		this.cfdgElement = cfdgElement;
	}

	private class CFDGElementListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setCDFG(new CFDGRuntimeElement(config.getCFDG()));
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
