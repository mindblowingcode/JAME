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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionComparator;
import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionRegistry;
import net.sf.jame.core.extension.ExtensionRuntime;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;

/**
 * OSGi extension registry.
 * 
 * @author Andrea Medeghini
 * @param <T> the extension runtime type.
 */
public class OSGiExtensionRegistry<T extends ExtensionRuntime> implements ExtensionRegistry<T> {
	private static final ResourceBundle bundle = ResourceBundle.getBundle(OSGiExtensionRegistry.class.getPackage().getName() + ".resources");
	private static final Logger logger = Logger.getLogger(OSGiExtensionRegistry.class);
	private static final IExtensionRegistry registry = Platform.getExtensionRegistry();
	private final Hashtable<String, Extension<T>> extensionMap = new Hashtable<String, Extension<T>>();
	private String extensionPointName;
	private String cfgElementName;
	
	/**
	 * Constructs a new extension registry.
	 * 
	 * @param extensionPointName the extension point name.
	 * @param builder the extension builder.
	 */
	protected OSGiExtensionRegistry(final String extensionPointName, final OSGiExtensionBuilder<T> builder) {
		this.extensionPointName = extensionPointName;
		this.cfgElementName = builder.getCfgElementName();
		final List<Extension<T>> extensionList = this.createExtensionList(extensionPointName, builder);
		for (final Extension<T> extension : extensionList) {
			this.extensionMap.put(extension.getExtensionId(), extension);
		}
	}

	private List<Extension<T>> createExtensionList(final String extensionPointName, final OSGiExtensionBuilder<T> builder) {
		final ArrayList<Extension<T>> extensionList = new ArrayList<Extension<T>>();
		final IExtensionPoint extensionPoint = OSGiExtensionRegistry.registry.getExtensionPoint(extensionPointName);
		if (extensionPoint != null) {
			try {
				extensionList.addAll(this.processExtensionPoint(extensionPoint, builder));
			}
			catch (final InvalidRegistryObjectException e) {
				OSGiExtensionRegistry.logger.error(MessageFormat.format(OSGiExtensionRegistry.bundle.getString("extensionPoint.error.0"), extensionPointName), e);
			}
		}
		else {
			OSGiExtensionRegistry.logger.warn(MessageFormat.format(OSGiExtensionRegistry.bundle.getString("extensionPoint.error.1"), extensionPointName));
		}
		return extensionList;
	}

	private List<Extension<T>> processExtensionPoint(final IExtensionPoint extensionPoint, final OSGiExtensionBuilder<T> builder) throws InvalidRegistryObjectException {
		final ArrayList<Extension<T>> extensionList = new ArrayList<Extension<T>>();
		final IExtension[] extensions = extensionPoint.getExtensions();
		if ((extensions != null) && (extensions.length > 0)) {
			for (final IExtension extension : extensions) {
				try {
					extensionList.addAll(this.processExtension(extension, builder));
				}
				catch (final InvalidRegistryObjectException e) {
					OSGiExtensionRegistry.logger.error(MessageFormat.format(OSGiExtensionRegistry.bundle.getString("extension.error.0"), extension.getLabel()), e);
				}
			}
			Collections.sort(extensionList, new ExtensionComparator());
		}
		else {
			OSGiExtensionRegistry.logger.warn(MessageFormat.format(OSGiExtensionRegistry.bundle.getString("extensionPoint.error.2"), extensionPoint.getLabel()));
		}
		return extensionList;
	}

	private List<Extension<T>> processExtension(final IExtension extension, final OSGiExtensionBuilder<T> builder) throws InvalidRegistryObjectException {
		final ArrayList<Extension<T>> extensionList = new ArrayList<Extension<T>>();
		final IConfigurationElement[] cfgElements = extension.getConfigurationElements();
		if ((cfgElements != null) && (cfgElements.length > 0)) {
			for (final IConfigurationElement cfgElement : cfgElements) {
				try {
					if (builder.validate(cfgElement)) {
						final Extension<T> extensionListElement = builder.createExtension(cfgElement);
						if (extensionListElement != null) {
							extensionList.add(extensionListElement);
						}
					}
				}
				catch (final OSGiExtensionBuilderException e) {
					OSGiExtensionRegistry.logger.error(MessageFormat.format(OSGiExtensionRegistry.bundle.getString("extension.error.1"), extension.getLabel()), e);
				}
			}
		}
		else {
			OSGiExtensionRegistry.logger.warn(MessageFormat.format(OSGiExtensionRegistry.bundle.getString("extension.error.2"), extension.getLabel()));
		}
		return extensionList;
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
}
