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
package net.sf.jame.mandelbrot.extensions.color;

import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;
import net.sf.jame.twister.common.PercentageElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ConstantConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<ConstantConfig> createXMLExporter() {
		return new ConstantConfigXMLExporter();
	}

	private class ConstantConfigXMLExporter extends AbstractColorRendererConfigXMLExporter<ConstantConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.color.AbstractColorRendererConfigXMLExporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "ConstantConfig";
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		@Override
		protected void exportProperties(final ConstantConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportValue(config, createProperty(builder, element, "value"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportValue(final ConstantConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new PercentageElementXMLExporter().exportToElement(config.getValueElement(), builder));
		}
	}
}
