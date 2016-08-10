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
package net.sf.jame.mandelbrot.extensions.image;

import net.sf.jame.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.MandelbrotConfigXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotImageConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<MandelbrotImageConfig> createXMLExporter() {
		return new MandelbrotImageConfigXMLExporter();
	}

	public class MandelbrotImageConfigXMLExporter extends XMLExporter<MandelbrotImageConfig> {
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final MandelbrotImageConfig config, final XMLNodeBuilder builder) throws XMLExportException {
			final Element element = this.createElement(builder, MandelbrotImageConfig.CLASS_ID);
			exportProperties(config, element, builder);
			return element;
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportProperties(final MandelbrotImageConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportMandelbrotConfig(config, createProperty(builder, element, "mandelbrotConfig"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportMandelbrotConfig(final MandelbrotImageConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new MandelbrotConfigXMLExporter().exportToElement(config.getMandelbrotConfig(), builder));
		}
	}
}
