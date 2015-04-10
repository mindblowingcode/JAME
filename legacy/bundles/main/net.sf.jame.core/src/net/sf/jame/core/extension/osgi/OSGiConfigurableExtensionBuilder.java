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
package net.sf.jame.core.extension.osgi;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.core.extension.ExtensionConfig;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * OSGi configurable extension builder.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 * @param <V> the extension configuration type.
 */
public class OSGiConfigurableExtensionBuilder<T extends ConfigurableExtensionRuntime<? extends V>, V extends ExtensionConfig> {
	private final ResourceBundle bundle = ResourceBundle.getBundle(OSGiConfigurableExtensionBuilder.class.getPackage().getName() + ".resources");
	private final String cfgElementName;

	/**
	 * Constructs a new builder.
	 * 
	 * @param cfgElementName the element name.
	 */
	public OSGiConfigurableExtensionBuilder(final String cfgElementName) {
		if (cfgElementName == null) {
			throw new IllegalArgumentException("cfgElementName is null");
		}
		this.cfgElementName = cfgElementName;
	}

	/**
	 * Creates an extension from a configuration element.
	 * 
	 * @param cfgElement the configuration element.
	 * @return the extension.
	 * @throws OSGiExtensionBuilderException if the extension can't be created.
	 */
	public ConfigurableExtension<T, V> createExtension(final IConfigurationElement cfgElement) throws OSGiExtensionBuilderException {
		return this.createExtension(cfgElement, this.cfgElementName);
	}

	private ConfigurableExtension<T, V> createExtension(final IConfigurationElement cfgElement, final String cfgElementName) throws OSGiExtensionBuilderException {
		try {
			if (cfgElement.isValid() && cfgElementName.equalsIgnoreCase(cfgElement.getName())) {
				return new OSGiConfigurableExtension<T, V>(cfgElement);
			}
		}
		catch (final Exception e) {
			throw new OSGiExtensionBuilderException(e);
		}
		throw new OSGiExtensionBuilderException(MessageFormat.format(this.bundle.getString("builder.error"), new Object[] { cfgElement.getName(), cfgElement.getDeclaringExtension().getLabel() }));
	}

	/**
	 * Validates the element.
	 * 
	 * @param cfgElement the element.
	 * @return true if the element is valid.
	 */
	public boolean validate(final IConfigurationElement cfgElement) {
		return cfgElement.isValid() && this.cfgElementName.equalsIgnoreCase(cfgElement.getName());
	}

	public String getCfgElementName() {
		return cfgElementName;
	}
}
