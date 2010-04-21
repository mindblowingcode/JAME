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
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.replacement.MultiReplacementConfigElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MultiReplacementConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<MultiReplacementConfig> createXMLExporter() {
		return new ReplacementConfigXMLExporter();
	}

	private class ReplacementConfigXMLExporter extends AbstractReplacementConfigXMLExporter<MultiReplacementConfig> {
		/**
		 * @see net.sf.jame.twister.extensions.frame.layer.filter.AbstractLayerFilterConfigXMLExporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "MultiReplacementConfig";
		}

		@Override
		public Element exportToElement(MultiReplacementConfig config, XMLNodeBuilder builder) throws XMLExportException {
			Element element = super.exportToElement(config, builder);
			exportProperties(config, element, builder);
			return element;
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportProperties(final MultiReplacementConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportReplacement(config, createProperty(builder, element, "replacement"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportReplacement(final MultiReplacementConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new MultiReplacementConfigElementXMLExporter().exportToElement(config.getReplacementElement(), builder));
		}
	}
}
