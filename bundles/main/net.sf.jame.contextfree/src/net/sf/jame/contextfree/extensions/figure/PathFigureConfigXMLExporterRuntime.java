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
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.path.PathConfigElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class PathFigureConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<PathFigureConfig> createXMLExporter() {
		return new FigureConfigXMLExporter();
	}

	private class FigureConfigXMLExporter extends AbstractFigureConfigXMLExporter<PathFigureConfig> {
		/**
		 * @see net.sf.jame.twister.extensions.frame.layer.filter.AbstractLayerFilterConfigXMLExporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "PathFigureConfig";
		}

		@Override
		public Element exportToElement(PathFigureConfig config, XMLNodeBuilder builder) throws XMLExportException {
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
		protected void exportProperties(final PathFigureConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportPath(config, createProperty(builder, element, "path"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportPath(final PathFigureConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new PathConfigElementXMLExporter().exportToElement(config.getPathElement(), builder));
		}
	}
}
