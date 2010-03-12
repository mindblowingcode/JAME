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
package net.sf.jame.mandelbrot.extensions.fractal.rendering;

import net.sf.jame.core.common.ComplexElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.common.IterationsElementXMLExporter;
import net.sf.jame.mandelbrot.common.ThresholdElementXMLExporter;
import net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractRenderingFormulaConfigXMLExporter<T extends RenderingFormulaExtensionConfig> extends XMLExporter<T> {
	/**
	 * @return
	 */
	protected abstract String getConfigElementClassId();

	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final T config, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, this.getConfigElementClassId(), 1, 0);
		this.exportProperties(config, element, builder);
		return element;
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		this.exportIterations(config, createProperty(builder, element, "iterations"), builder);
		this.exportThreshold(config, createProperty(builder, element, "threshold"), builder);
		this.exportCenter(config, createProperty(builder, element, "center"), builder);
		this.exportScale(config, createProperty(builder, element, "scale"), builder);
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportIterations(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new IterationsElementXMLExporter().exportToElement(config.getIterationsElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportThreshold(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ThresholdElementXMLExporter().exportToElement(config.getThresholdElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportCenter(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ComplexElementXMLExporter().exportToElement(config.getCenterElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportScale(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ComplexElementXMLExporter().exportToElement(config.getScaleElement(), builder));
	}
}
