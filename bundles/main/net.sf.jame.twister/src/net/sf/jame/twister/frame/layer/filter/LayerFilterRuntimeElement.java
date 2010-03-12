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
package net.sf.jame.twister.frame.layer.filter;

import net.sf.jame.core.common.ExtensionReferenceElement;
import net.sf.jame.core.config.RuntimeElement;
import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;
import net.sf.jame.core.config.ValueConfigElement;
import net.sf.jame.core.extension.ConfigurableExtensionReference;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.frame.layer.filter.extension.LayerFilterExtensionConfig;
import net.sf.jame.twister.frame.layer.filter.extension.LayerFilterExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class LayerFilterRuntimeElement extends RuntimeElement {
	private LayerFilterExtensionRuntime<?> filterRuntime;
	private LayerFilterConfigElement filterElement;
	private ExtensionListener extensionListener;
	private EnabledListener enabledListener;
	private boolean enabled;

	/**
	 * Constructs a new filter.
	 * 
	 * @param registry
	 * @param filterElement
	 */
	public LayerFilterRuntimeElement(final LayerFilterConfigElement filterElement) {
		if (filterElement == null) {
			throw new IllegalArgumentException("filterElement is null");
		}
		this.filterElement = filterElement;
		createRuntime(filterElement.getReference());
		extensionListener = new ExtensionListener();
		filterElement.getExtensionElement().addChangeListener(extensionListener);
		setEnabled(filterElement.isEnabled());
		enabledListener = new EnabledListener();
		filterElement.getEnabledElement().addChangeListener(enabledListener);
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((filterElement != null) && (extensionListener != null)) {
			filterElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if ((filterElement != null) && (enabledListener != null)) {
			filterElement.getEnabledElement().removeChangeListener(enabledListener);
		}
		enabledListener = null;
		if (filterRuntime != null) {
			filterRuntime.dispose();
			filterRuntime = null;
		}
		filterElement = null;
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<LayerFilterExtensionConfig> reference) {
		try {
			if (reference != null) {
				final LayerFilterExtensionRuntime filterRuntime = TwisterRegistry.getInstance().getLayerFilterExtension(reference.getExtensionId()).createExtensionRuntime();
				final LayerFilterExtensionConfig filterConfig = reference.getExtensionConfig();
				filterRuntime.setConfig(filterConfig);
				setFilterRuntime(filterRuntime);
			}
			else {
				setFilterRuntime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean filterChanged = (filterRuntime != null) && filterRuntime.isChanged();
		return super.isChanged() || filterChanged;
	}

	/**
	 * @return the filterRuntime
	 */
	public LayerFilterExtensionRuntime<?> getFilterRuntime() {
		return filterRuntime;
	}

	private void setFilterRuntime(final LayerFilterExtensionRuntime<?> filterRuntime) {
		if (this.filterRuntime != null) {
			this.filterRuntime.dispose();
		}
		this.filterRuntime = filterRuntime;
	}

	/**
	 * @return the enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	private class ExtensionListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		@SuppressWarnings("unchecked")
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<LayerFilterExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class EnabledListener implements ValueChangeListener {
		/**
		 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setEnabled((Boolean) e.getParams()[0]);
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
