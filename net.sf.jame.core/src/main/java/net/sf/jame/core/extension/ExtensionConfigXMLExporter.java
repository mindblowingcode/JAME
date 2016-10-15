/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
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

import net.sf.jame.core.CoreRegistry;
import net.sf.jame.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ExtensionConfigXMLExporter<V extends ExtensionConfig> extends XMLExporter<V> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final V config, final XMLNodeBuilder builder) throws XMLExportException {
		final String extensionId = config.getExtensionId();
		final Element element = this.createElement(builder, "reference", extensionId);
		try {
			exportProperties(config, element, builder, extensionId);
		}
		catch (final ExtensionException e) {
			throw new XMLExportException(e);
		}
		return element;
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @param extensionId
	 * @throws ExtensionException
	 * @throws XMLExportException
	 */
	protected void exportProperties(final V config, final Element element, final XMLNodeBuilder builder, final String extensionId) throws ExtensionException, XMLExportException {
		this.exportReference(config, createProperty(builder, element, "extensionConfig"), builder, extensionId);
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @param extensionId
	 * @throws ExtensionException
	 * @throws XMLExportException
	 */
	@SuppressWarnings("unchecked")
	protected void exportReference(final V config, final Element element, final XMLNodeBuilder builder, final String extensionId) throws ExtensionException, XMLExportException {
		final Extension<ExtensionConfigXMLExporterExtensionRuntime> exporterExtension = CoreRegistry.getInstance().getXMLExtensionConfigExporterExtension(extensionId);
		final XMLExporter<V> exporter = (XMLExporter<V>) (exporterExtension.createExtensionRuntime()).createXMLExporter();
		element.appendChild(exporter.exportToElement(config, builder));
	}
}
