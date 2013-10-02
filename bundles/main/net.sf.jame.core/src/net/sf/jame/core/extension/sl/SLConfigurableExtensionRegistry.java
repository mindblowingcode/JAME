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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.jame.core.extension.ConfigurableExtension;
import net.sf.jame.core.extension.ConfigurableExtensionRegistry;
import net.sf.jame.core.extension.ConfigurableExtensionRuntime;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionComparator;
import net.sf.jame.core.extension.ExtensionConfig;
import net.sf.jame.core.extension.ExtensionNotFoundException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;

/**
 * OSGi configurable extension registry.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 * @param <V> the extension configuration type.
 */
public class SLConfigurableExtensionRegistry<T extends ConfigurableExtensionRuntime<? extends V>, V extends ExtensionConfig> implements ConfigurableExtensionRegistry<T, V> {
	private static final ResourceBundle bundle = ResourceBundle.getBundle(SLConfigurableExtensionRegistry.class.getPackage().getName() + ".resources");
	private static final Logger logger = Logger.getLogger(SLConfigurableExtensionRegistry.class);
	private static final IExtensionRegistry registry = Platform.getExtensionRegistry();
	private final HashMap<String, ConfigurableExtension<T, V>> extensionMap = new HashMap<String, ConfigurableExtension<T, V>>();
	private String extensionPointName;
	private String cfgElementName;

	/**
	 * Constructs a new extension registry.
	 * 
	 * @param extensionPointName the extension point name.
	 * @param builder the extension builder.
	 */
	protected SLConfigurableExtensionRegistry(final String extensionPointName, final SLConfigurableExtensionBuilder<T, V> builder) {
		this.extensionPointName = extensionPointName;
		this.cfgElementName = builder.getCfgElementName();
		final List<ConfigurableExtension<T, V>> extensionList = this.createExtensionList(extensionPointName, builder);
		for (final ConfigurableExtension<T, V> extension : extensionList) {
			this.extensionMap.put(extension.getExtensionId(), extension);
		}
	}

	private List<ConfigurableExtension<T, V>> createExtensionList(final String extensionPointName, final SLConfigurableExtensionBuilder<T, V> builder) {
		final ArrayList<ConfigurableExtension<T, V>> extensionList = new ArrayList<ConfigurableExtension<T, V>>();
		final IExtensionPoint extensionPoint = SLConfigurableExtensionRegistry.registry.getExtensionPoint(extensionPointName);
		if (extensionPoint != null) {
			try {
				extensionList.addAll(this.processExtensionPoint(extensionPoint, builder));
			}
			catch (final InvalidRegistryObjectException e) {
				SLConfigurableExtensionRegistry.logger.error(MessageFormat.format(SLConfigurableExtensionRegistry.bundle.getString("extensionPoint.error.0"), extensionPointName), e);
			}
		}
		else {
			SLConfigurableExtensionRegistry.logger.warn(MessageFormat.format(SLConfigurableExtensionRegistry.bundle.getString("extensionPoint.error.1"), extensionPointName));
		}
		return extensionList;
	}

	private List<ConfigurableExtension<T, V>> processExtensionPoint(final IExtensionPoint extensionPoint, final SLConfigurableExtensionBuilder<T, V> builder) throws InvalidRegistryObjectException {
		final ArrayList<ConfigurableExtension<T, V>> extensionList = new ArrayList<ConfigurableExtension<T, V>>();
		final IExtension[] extensions = extensionPoint.getExtensions();
		if ((extensions != null) && (extensions.length > 0)) {
			for (final IExtension extension : extensions) {
				try {
					extensionList.addAll(this.processExtension(extension, builder));
				}
				catch (final InvalidRegistryObjectException e) {
					SLConfigurableExtensionRegistry.logger.error(MessageFormat.format(SLConfigurableExtensionRegistry.bundle.getString("extension.error.0"), extension.getLabel()), e);
				}
			}
			Collections.sort(extensionList, new ExtensionComparator());
		}
		else {
			SLConfigurableExtensionRegistry.logger.warn(MessageFormat.format(SLConfigurableExtensionRegistry.bundle.getString("extensionPoint.error.2"), extensionPoint.getLabel()));
		}
		return extensionList;
	}

	private List<ConfigurableExtension<T, V>> processExtension(final IExtension extension, final SLConfigurableExtensionBuilder<T, V> builder) throws InvalidRegistryObjectException {
		final ArrayList<ConfigurableExtension<T, V>> extensionList = new ArrayList<ConfigurableExtension<T, V>>();
		final IConfigurationElement[] cfgElements = extension.getConfigurationElements();
		if ((cfgElements != null) && (cfgElements.length > 0)) {
			for (final IConfigurationElement cfgElement : cfgElements) {
				try {
					if (builder.validate(cfgElement)) {
						final ConfigurableExtension<T, V> extensionListElement = builder.createExtension(cfgElement);
						if (extensionListElement != null) {
							extensionList.add(extensionListElement);
						}
					}
				}
				catch (final SLExtensionBuilderException e) {
					SLConfigurableExtensionRegistry.logger.error(MessageFormat.format(SLConfigurableExtensionRegistry.bundle.getString("extension.error.1"), extension.getLabel()), e);
				}
			}
		}
		else {
			SLConfigurableExtensionRegistry.logger.warn(MessageFormat.format(SLConfigurableExtensionRegistry.bundle.getString("extension.error.2"), extension.getLabel()));
		}
		return extensionList;
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
}
