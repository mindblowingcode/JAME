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
package net.sf.jame.core.extension;

import net.sf.jame.core.config.ValueChangeEvent;
import net.sf.jame.core.config.ValueChangeListener;

/**
 * Interface of extension runtimes.
 * 
 * @author Andrea Medeghini
 */
public abstract class ConfigurableExtensionRuntime<T extends ExtensionConfig> extends ExtensionRuntime implements ValueChangeListener {
	private T config;

	/**
	 * @see net.sf.jame.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if (this.config != null) {
			this.config.removeChangeListener(this);
			this.config = null;
		}
	}

	/**
	 * Sets the config.
	 * 
	 * @param config the config to set.
	 */
	public final void setConfig(final T config) {
		dispose();
		this.config = config;
		if (this.config != null) {
			this.config.addChangeListener(this);
		}
		this.configReloaded();
	}

	/**
	 * Returns the config.
	 * 
	 * @return the config.
	 */
	public final T getConfig() {
		return this.config;
	}

	/**
	 * Called when config is changed.
	 */
	public void configReloaded() {
	}

	/**
	 * @see net.sf.jame.core.config.ValueChangeListener#valueChanged(net.sf.jame.core.config.ValueChangeEvent)
	 */
	public void valueChanged(final ValueChangeEvent e) {
		fireChanged();
	}
}
