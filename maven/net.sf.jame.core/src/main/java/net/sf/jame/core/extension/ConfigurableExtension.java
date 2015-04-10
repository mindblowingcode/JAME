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

/**
 * Interface of configurable extensions.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 * @param <V> the extension configuration type.
 */
public interface ConfigurableExtension<T extends ConfigurableExtensionRuntime<? extends V>, V extends ExtensionConfig> extends Extension<T> {
	/**
	 * Creates a new extension configuration with default values.
	 * 
	 * @return a new extension configuration.
	 * @throws ExtensionException if the configuration can't be created.
	 */
	public V createDefaultExtensionConfig() throws ExtensionException;

	/**
	 * Creates a new extension reference with a given configuration.
	 * 
	 * @param extensionConfig the extension configuration.
	 * @return a new extension reference.
	 */
	public ConfigurableExtensionReference<V> createConfigurableExtensionReference(V extensionConfig);

	/**
	 * Creates a new extension reference with the default configuration.
	 * 
	 * @return a new extension reference.
	 * @throws ExtensionException if the configuration can't be created.
	 */
	public ConfigurableExtensionReference<V> createConfigurableExtensionReference() throws ExtensionException;
}
