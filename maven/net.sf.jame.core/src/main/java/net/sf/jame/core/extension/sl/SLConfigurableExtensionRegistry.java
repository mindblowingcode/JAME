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
 * SL configurable extension registry.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 * @param <V> the extension configuration type.
 */
public class SLConfigurableExtensionRegistry<T extends ConfigurableExtensionRuntime<? extends V>, V extends ExtensionConfig> implements ConfigurableExtensionRegistry<T, V> {
	private static final Logger logger = Logger.getLogger(SLConfigurableExtensionRegistry.class.getName());
	private final HashMap<String, ConfigurableExtension<T, V>> extensionMap = new HashMap<String, ConfigurableExtension<T, V>>();
	private Class<? extends ConfigurableExtensionDescriptor<T, V>> extensionDescriptorClass;
	private String extensionPointName;
	private String cfgElementName;

	/**
	 * Constructs a new extension registry.
	 * 
	 * @param extensionPointName the extension point name.
	 * @param builder the extension builder.
	 */
	protected SLConfigurableExtensionRegistry(final Class<? extends ConfigurableExtensionDescriptor<T, V>> extensionDescriptorClass, final String extensionPointName, final SLConfigurableExtensionBuilder<T, V> builder) {
		this.extensionDescriptorClass = extensionDescriptorClass;
		this.extensionPointName = extensionPointName;
		this.cfgElementName = builder.getCfgElementName();
		final ServiceLoader<? extends ConfigurableExtensionDescriptor<T, V>> serviceLoader = ServiceLoader.load(extensionDescriptorClass);
		for (ConfigurableExtensionDescriptor<T, V> extensionDescriptor : serviceLoader) {
			logger.fine(extensionDescriptor.getExtensionId());
			try {
				ConfigurableExtension<T, V> extension = builder.createExtension(extensionDescriptor);
				this.extensionMap.put(extension.getExtensionId(), extension);
			} catch (SLExtensionBuilderException e) {
				SLConfigurableExtensionRegistry.logger.log(Level.WARNING, MessageFormat.format(CoreResources.getInstance().getString("extension.error"), extensionDescriptor.getExtensionName()), e);
			}
		}
	}

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRegistry#getConfigurableExtensionList()
	 */
	public List<ConfigurableExtension<T, V>> getConfigurableExtensionList() {
		final Collection<ConfigurableExtension<T, V>> extensions = this.extensionMap.values();
		final List<ConfigurableExtension<T, V>> list = new LinkedList<ConfigurableExtension<T, V>>();
		for (final ConfigurableExtension<T, V> extension : extensions) {
			list.add(extension);
		}
		Collections.sort(list, new ExtensionComparator());
		return list;
	}

	/**
	 * @see net.sf.jame.core.extension.ConfigurableExtensionRegistry#getConfigurableExtension(java.lang.String)
	 */
	public ConfigurableExtension<T, V> getConfigurableExtension(final String extensionId) throws ExtensionNotFoundException {
		final ConfigurableExtension<T, V> extension = this.extensionMap.get(extensionId);
		if (extension == null) {
			throw new ExtensionNotFoundException("Can't find extension " + extensionId + " [cfgElementName = " + cfgElementName + ", extensionPointName = " + extensionPointName + "]");
		}
		return extension;
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRegistry#getExtensionList()
	 */
	public List<Extension<T>> getExtensionList() {
		final Collection<ConfigurableExtension<T, V>> extensions = this.extensionMap.values();
		final List<Extension<T>> list = new LinkedList<Extension<T>>();
		for (final ConfigurableExtension<T, V> extension : extensions) {
			list.add(extension);
		}
		Collections.sort(list, new ExtensionComparator());
		return list;
	}

	/**
	 * @see net.sf.jame.core.extension.ExtensionRegistry#getExtension(java.lang.String)
	 */
	public Extension<T> getExtension(final String extensionId) throws ExtensionNotFoundException {
		return this.getConfigurableExtension(extensionId);
	}

	/**
	 * Returns the extension descriptor class.
	 * @return the extension descriptor class.
	 */
	protected Class<? extends ConfigurableExtensionDescriptor<T, V>> getExtensionDescriptorClass() {
		return extensionDescriptorClass;
	}
}
