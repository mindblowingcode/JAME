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
package net.sf.jame.twister.frame;

import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.twister.frame.filter.FrameFilterConfigElementXMLExporter;
import net.sf.jame.twister.frame.layer.GroupLayerConfigElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class FrameConfigElementXMLExporter extends XMLExporter<FrameConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final FrameConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, FrameConfigElement.CLASS_ID);
		exportProperties(configElement, element, builder);
		return element;
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final FrameConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportFilters(configElement, createProperty(builder, element, "filterList"), builder);
		exportLayers(configElement, createProperty(builder, element, "layerList"), builder);
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportFilters(final FrameConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final FrameFilterConfigElementXMLExporter filterExporter = new FrameFilterConfigElementXMLExporter();
		for (int i = 0; i < configElement.getFilterConfigElementCount(); i++) {
			element.appendChild(filterExporter.exportToElement(configElement.getFilterConfigElement(i), builder));
		}
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportLayers(final FrameConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final GroupLayerConfigElementXMLExporter groupLayerExporter = new GroupLayerConfigElementXMLExporter();
		for (int i = 0; i < configElement.getLayerConfigElementCount(); i++) {
			element.appendChild(groupLayerExporter.exportToElement(configElement.getLayerConfigElement(i), builder));
		}
	}
}
