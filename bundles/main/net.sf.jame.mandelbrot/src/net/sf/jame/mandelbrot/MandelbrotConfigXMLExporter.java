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
package net.sf.jame.mandelbrot;

import net.sf.jame.core.common.BooleanElementXMLExporter;
import net.sf.jame.core.common.ComplexElementXMLExporter;
import net.sf.jame.core.common.IntegerElementXMLExporter;
import net.sf.jame.core.common.RectangleElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.fractal.MandelbrotFractalConfigElementXMLExporter;
import net.sf.jame.twister.common.SpeedElementXMLExporter;
import net.sf.jame.twister.common.ViewElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotConfigXMLExporter extends XMLExporter<MandelbrotConfig> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final MandelbrotConfig config, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, MandelbrotConfig.CLASS_ID, 3, 0);
		exportProperties(config, element, builder);
		return element;
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportMandelbrotFractal(config, createProperty(builder, element, "mandelbrotFractal"), builder);
		exportConstant(config, createProperty(builder, element, "constant"), builder);
		exportView(config, createProperty(builder, element, "view"), builder);
		exportImageMode(config, createProperty(builder, element, "mode"), builder);
		exportPreviewArea(config, createProperty(builder, element, "previewArea"), builder);
		exportShowPreview(config, createProperty(builder, element, "showPreview"), builder);
		exportShowOrbit(config, createProperty(builder, element, "showOrbit"), builder);
		exportShowOrbitTrap(config, createProperty(builder, element, "showOrbitTrap"), builder);
		exportSpeed(config, createProperty(builder, element, "speed"), builder);
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportMandelbrotFractal(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new MandelbrotFractalConfigElementXMLExporter().exportToElement(config.getMandelbrotFractalConfigElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportConstant(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ComplexElementXMLExporter().exportToElement(config.getConstantElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportView(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ViewElementXMLExporter().exportToElement(config.getViewElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportImageMode(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new IntegerElementXMLExporter().exportToElement(config.getImageModeElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportShowPreview(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new BooleanElementXMLExporter().exportToElement(config.getShowPreviewElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportShowOrbit(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new BooleanElementXMLExporter().exportToElement(config.getShowOrbitElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportShowOrbitTrap(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new BooleanElementXMLExporter().exportToElement(config.getShowOrbitTrapElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportPreviewArea(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new RectangleElementXMLExporter().exportToElement(config.getPreviewAreaElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportSpeed(final MandelbrotConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new SpeedElementXMLExporter().exportToElement(config.getSpeedElement(), builder));
	}
}
