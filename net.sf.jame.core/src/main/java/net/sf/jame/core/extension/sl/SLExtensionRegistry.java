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
package net.sf.jame.core.extension.sl;

import net.sf.jame.core.CoreResources;
import net.sf.jame.core.extension.*;

import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SL extension registry.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 */
public class SLExtensionRegistry<T extends ExtensionRuntime> implements ExtensionRegistry<T> {
	private static final Logger logger = Logger.getLogger(SLExtensionRegistry.class.getName());
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
			logger.fine(extensionDescriptor.getExtensionId());
			try {
				Extension<T> extension = builder.createExtension(extensionDescriptor);
				this.extensionMap.put(extension.getExtensionId(), extension);
			} catch (SLExtensionBuilderException e) {
				SLExtensionRegistry.logger.log(Level.WARNING, MessageFormat.format(CoreResources.getInstance().getString("extension.error"), extensionDescriptor.getExtensionName()), e);
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
