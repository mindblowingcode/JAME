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
package net.sf.jame.twister.layer;

import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.twister.image.ImageConfigElementXMLExporter;
import net.sf.jame.twister.layerFilter.LayerFilterConfigElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ImageLayerConfigElementXMLExporter extends AbstractLayerConfigElementXMLExporter<ImageLayerConfigElement> {
	/**
	 * @see net.sf.jame.twister.layer.AbstractLayerConfigElementXMLExporter#exportProperties(net.sf.jame.twister.layer.LayerConfigElement, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportProperties(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		super.exportProperties(configElement, element, builder);
		exportFilters(configElement, createProperty(builder, element, "filterList"), builder);
		exportImage(configElement, createProperty(builder, element, "image"), builder);
		exportLabel(configElement, createProperty(builder, element, "label"), builder);
	}

	private void exportFilters(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final LayerFilterConfigElementXMLExporter filterExporter = new LayerFilterConfigElementXMLExporter();
		for (int i = 0; i < configElement.getFilterConfigElementCount(); i++) {
			element.appendChild(filterExporter.exportToElement(configElement.getFilterConfigElement(i), builder));
		}
	}

	private void exportImage(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ImageConfigElementXMLExporter().exportToElement(configElement.getImageConfigElement(), builder));
	}

	private void exportLabel(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getLabelElement(), builder));
	}
}
