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
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionComparator;
import net.sf.jame.core.extension.ExtensionDescriptor;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionRegistry;
import net.sf.jame.core.extension.ExtensionRuntime;

import org.apache.log4j.Logger;

/**
 * SL extension registry.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 */
public class SLExtensionRegistry<T extends ExtensionRuntime> implements ExtensionRegistry<T> {
	private static final ResourceBundle bundle = ResourceBundle.getBundle(SLExtensionRegistry.class.getPackage().getName() + ".resources");
	private static final Logger logger = Logger.getLogger(SLExtensionRegistry.class);
	private final Hashtable<String, Extension<T>> extensionMap = new Hashtable<String, Extension<T>>();
	private Class<? extends ExtensionDescriptor<T>> extensionDescriptorClass;
	private String extensionPointName;
	private String cfgElementName;
	
	/**
	 * Constructs a new extension registry.
	 * @param descriptorClass the extension descriptor class.
	 * @param extensionPointName the extension point name.
	 * @param builder the extension builder.
	 */
	protected SLExtensionRegistry(final Class<? extends ExtensionDescriptor<T>> extensionDescriptorClass, final String extensionPointName, final SLExtensionBuilder<T> builder) {
		this.extensionDescriptorClass = extensionDescriptorClass;
		this.extensionPointName = extensionPointName;
		this.cfgElementName = builder.getCfgElementName();
		final ServiceLoader<? extends ExtensionDescriptor<T>> serviceLoader = ServiceLoader.load(extensionDescriptorClass);
		for (ExtensionDescriptor<T> extensionDescriptor : serviceLoader) {
			System.out.println(extensionDescriptor.getExtensionId());//TODO da rimuovere
			try {
				Extension<T> extension = builder.createExtension(extensionDescriptor);
				this.extensionMap.put(extension.getExtensionId(), extension);
			} catch (SLExtensionBuilderException e) {
				SLExtensionRegistry.logger.error(MessageFormat.format(SLExtensionRegistry.bundle.getString("extension.error.1"), extensionDescriptor.getExtensionName()), e);
			}
		}
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRegistry#getExtensionList()
	 */
	public List<Extension<T>> getExtensionList() {
		final Enumeration<Extension<T>> elements = this.extensionMap.elements();
		final List<Extension<T>> list = new LinkedList<Extension<T>>();
		while (elements.hasMoreElements()) {
			list.add(elements.nextElement());
		}
		Collections.sort(list, new ExtensionComparator());
		return list;
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRegistry#getExtension(java.lang.String)
	 */
	public Extension<T> getExtension(final String extensionId) throws ExtensionNotFoundException {
		final Extension<T> extension = this.extensionMap.get(extensionId);
		if (extension == null) {
			throw new ExtensionNotFoundException("Can't find extension " + extensionId + " [cfgElementName = " + cfgElementName + ", extensionPointName = " + extensionPointName + "]");
		}
		return extension;
	}

	/**
	 * Returns the extension descriptor class.
	 * @return the extension descriptor class.
	 */
	protected Class<? extends ExtensionDescriptor<T>> getExtensionDescriptorClass() {
		return extensionDescriptorClass;
	}
}
