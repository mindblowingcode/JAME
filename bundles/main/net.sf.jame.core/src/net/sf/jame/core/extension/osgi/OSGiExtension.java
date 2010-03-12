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
package net.sf.jame.core.extension.osgi;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extension.ExtensionReference;
import net.sf.jame.core.extension.ExtensionRuntime;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * OSGi extension.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 */
public class OSGiExtension<T extends ExtensionRuntime> implements Extension<T> {
	/**
	 * the name of the extensionId property.
	 */
	public static final String EXTENSION_ID_PROPERTY_NAME = "id";
	/**
	 * the name of the extensionName property.
	 */
	public static final String EXTENSION_NAME_PROPERTY_NAME = "name";
	/**
	 * the name of the extension runtime class property.
	 */
	public static final String EXTENSION_RUNTIME_CLASS_PROPERTY_NAME = "runtimeClass";
	private final IConfigurationElement cfgElement;
	private final ExtensionReference reference;

	/**
	 * Constructs a new extension from a configuration element.
	 * 
	 * @param cfgElement the configuration element.
	 * @throws ExtensionException if the extension can't be created.
	 */
	protected OSGiExtension(final IConfigurationElement cfgElement) throws ExtensionException {
		this.cfgElement = cfgElement;
		final String extensionId = cfgElement.getAttribute(OSGiExtension.EXTENSION_ID_PROPERTY_NAME);
		final String extensionName = cfgElement.getAttribute(OSGiExtension.EXTENSION_NAME_PROPERTY_NAME);
		this.reference = new ExtensionReference(extensionId, extensionName);
	}

	/**
	 * @see net.sf.jame.core.extension.Extension#createExtensionRuntime()
	 */
	@SuppressWarnings("unchecked")
	public final T createExtensionRuntime() throws ExtensionException {
		return (T) this.createExecutableExtension(OSGiExtension.EXTENSION_RUNTIME_CLASS_PROPERTY_NAME);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param propertyName the property name.
	 * @return the new instance.
	 * @throws ExtensionException if the instance can't be created.
	 */
	protected Object createExecutableExtension(final String propertyName) throws ExtensionException {
		try {
			return this.cfgElement.createExecutableExtension(propertyName);
		}
		catch (final CoreException e) {
			throw new ExtensionException(e);
		}
	}

	/**
	 * @see net.sf.jame.core.extension.Extension#getExtensionReference()
	 */
	public ExtensionReference getExtensionReference() {
		return this.reference;
	}

	/**
	 * @see net.sf.jame.core.extension.Extension#getExtensionId()
	 */
	public String getExtensionId() {
		return this.reference.getExtensionId();
	}

	/**
	 * @see net.sf.jame.core.extension.Extension#getExtensionName()
	 */
	public String getExtensionName() {
		return this.reference.getExtensionName();
	}
}
