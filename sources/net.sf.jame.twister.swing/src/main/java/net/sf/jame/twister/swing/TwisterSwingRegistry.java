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
package net.sf.jame.twister.swing;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionRegistry;
import net.sf.jame.twister.swing.inputAdapter.extension.InputAdapterExtensionRegistry;
import net.sf.jame.twister.swing.inputAdapter.extension.InputAdapterExtensionRuntime;
import net.sf.jame.twister.swing.view.extension.ViewExtensionRegistry;
import net.sf.jame.twister.swing.view.extension.ViewExtensionRuntime;

/**
 * The twister registry.
 * 
 * @author Andrea Medeghini
 */
public class TwisterSwingRegistry {
	private ExtensionRegistry<ViewExtensionRuntime> viewRegistry;
	private ExtensionRegistry<InputAdapterExtensionRuntime> inputAdapterRegistry;

	private static class RegistryHolder {
		private static final TwisterSwingRegistry instance = new TwisterSwingRegistry();
	}

	private TwisterSwingRegistry() {
		setViewRegistry(new ViewExtensionRegistry());
		setInputAdapterRegistry(new InputAdapterExtensionRegistry());
	}

	/**
	 * @return
	 */
	public static TwisterSwingRegistry getInstance() {
		return RegistryHolder.instance;
	}

	/**
	 * Returns a view extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public Extension<ViewExtensionRuntime> getViewExtension(final String extensionId) throws ExtensionNotFoundException {
		return viewRegistry.getExtension(extensionId);
	}

	/**
	 * Returns a inputAdapter extension.
	 * 
	 * @param extensionId the extensionId.
	 * @return the extension.
	 * @throws ExtensionNotFoundException
	 */
	public Extension<InputAdapterExtensionRuntime> getInputAdapterExtension(final String extensionId) throws ExtensionNotFoundException {
		return inputAdapterRegistry.getExtension(extensionId);
	}

	private void setViewRegistry(final ExtensionRegistry<ViewExtensionRuntime> viewRegistry) {
		this.viewRegistry = viewRegistry;
	}

	private void setInputAdapterRegistry(final ExtensionRegistry<InputAdapterExtensionRuntime> inputAdapterRegistry) {
		this.inputAdapterRegistry = inputAdapterRegistry;
	}

	/**
	 * @return the viewRegistry
	 */
	public ExtensionRegistry<ViewExtensionRuntime> getViewRegistry() {
		return viewRegistry;
	}

	/**
	 * @return the inputAdapterRegistry
	 */
	public ExtensionRegistry<InputAdapterExtensionRuntime> getInputAdapterRegistry() {
		return inputAdapterRegistry;
	}
}
