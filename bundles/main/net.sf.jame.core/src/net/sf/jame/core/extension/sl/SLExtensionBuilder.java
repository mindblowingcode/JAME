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
package net.sf.jame.core.extension.sl;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionDescriptor;
import net.sf.jame.core.extension.ExtensionRuntime;

/**
 * SL extension builder.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 */
public class SLExtensionBuilder<T extends ExtensionRuntime> {
	private final ResourceBundle bundle = ResourceBundle.getBundle(SLExtensionBuilder.class.getPackage().getName() + ".resources");
	private final String cfgElementName;

	/**
	 * Constructs a new builder.
	 * 
	 * @param cfgElementName the element name.
	 */
	public SLExtensionBuilder(final String cfgElementName) {
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
	 * @throws SLExtensionBuilderException if the extension can't be created.
	 */
	public Extension<T> createExtension(final ExtensionDescriptor<T> extensionDescriptor) throws SLExtensionBuilderException {
		return this.createExtension(extensionDescriptor, this.cfgElementName);
	}

	private Extension<T> createExtension(final ExtensionDescriptor<T> extensionDescriptor, final String cfgElementName) throws SLExtensionBuilderException {
		try {
			if (validate(extensionDescriptor)) {
				return new SLExtension<T>(extensionDescriptor);
			}
		}
		catch (final Exception e) {
			throw new SLExtensionBuilderException(e);
		}
		throw new SLExtensionBuilderException(MessageFormat.format(this.bundle.getString("builder.error"), new Object[] { cfgElementName, ""/*TODO*/}));
	}

	/**
	 * Validates the element.
	 * 
	 * @param extensionDescriptor the extension descriptor.
	 * @return true if the element is valid.
	 */
	public boolean validate(final ExtensionDescriptor<T> extensionDescriptor) {
		return true;
	}

	public String getCfgElementName() {
		return cfgElementName;
	}
}
