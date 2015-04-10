/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
 * Null configuration extension.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 */
@SuppressWarnings("unchecked")
public class NullConfigurableExtension implements ConfigurableExtension {
	private static NullConfigurableExtension instance = new NullConfigurableExtension();

	private NullConfigurableExtension() {
	}

	public ConfigurableExtensionRuntime<ExtensionConfig> createExtensionRuntime() throws ExtensionException {
		return null;
	}

	public String getExtensionId() {
		return "null";
	}

	public String getExtensionName() {
		return "-";
	}

	public ExtensionReference getExtensionReference() {
		return null;
	}

	public ConfigurableExtensionReference<ExtensionConfig> createConfigurableExtensionReference(final ExtensionConfig extensionConfig) {
		return null;
	}

	public ConfigurableExtensionReference<ExtensionConfig> createConfigurableExtensionReference() throws ExtensionException {
		return null;
	}

	public ExtensionConfig createDefaultExtensionConfig() throws ExtensionException {
		return null;
	}

	public static NullConfigurableExtension getInstance() {
		return instance;
	}
}
