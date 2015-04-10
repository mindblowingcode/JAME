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
package net.sf.jame.mandelbrot.fractal;

import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.incolouringFormula.IncolouringFormulaConfigElementXMLExporter;
import net.sf.jame.mandelbrot.orbitTrap.OrbitTrapConfigElementXMLExporter;
import net.sf.jame.mandelbrot.outcolouringFormula.OutcolouringFormulaConfigElementXMLExporter;
import net.sf.jame.mandelbrot.processingFormula.ProcessingFormulaConfigElementXMLExporter;
import net.sf.jame.mandelbrot.renderingFormula.RenderingFormulaConfigElementXMLExporter;
import net.sf.jame.mandelbrot.transformingFormula.TransformingFormulaConfigElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotFractalConfigElementXMLExporter extends XMLExporter<MandelbrotFractalConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final MandelbrotFractalConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, MandelbrotFractalConfigElement.CLASS_ID, 2, 0);
		exportProperties(configElement, element, builder);
		return element;
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportRenderingFormula(configElement, createProperty(builder, element, "renderingFormula"), builder);
		exportTransformingFormula(configElement, createProperty(builder, element, "transformingFormula"), builder);
		exportIncolouringFormulas(configElement, createProperty(builder, element, "incolouringFormulaList"), builder);
		exportOutcolouringFormulas(configElement, createProperty(builder, element, "outcolouringFormulaList"), builder);
		exportProcessingFormula(configElement, createProperty(builder, element, "processingFormula"), builder);
		exportOrbitTrap(configElement, createProperty(builder, element, "orbitTrap"), builder);
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportRenderingFormula(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new RenderingFormulaConfigElementXMLExporter().exportToElement(configElement.getRenderingFormulaConfigElement(), builder));
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportTransformingFormula(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new TransformingFormulaConfigElementXMLExporter().exportToElement(configElement.getTransformingFormulaConfigElement(), builder));
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportIncolouringFormulas(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final IncolouringFormulaConfigElementXMLExporter filterExporter = new IncolouringFormulaConfigElementXMLExporter();
		for (int i = 0; i < configElement.getIncolouringFormulaConfigElementCount(); i++) {
			element.appendChild(filterExporter.exportToElement(configElement.getIncolouringFormulaConfigElement(i), builder));
		}
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportOutcolouringFormulas(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final OutcolouringFormulaConfigElementXMLExporter filterExporter = new OutcolouringFormulaConfigElementXMLExporter();
		for (int i = 0; i < configElement.getOutcolouringFormulaConfigElementCount(); i++) {
			element.appendChild(filterExporter.exportToElement(configElement.getOutcolouringFormulaConfigElement(i), builder));
		}
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProcessingFormula(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ProcessingFormulaConfigElementXMLExporter().exportToElement(configElement.getProcessingFormulaConfigElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportOrbitTrap(final MandelbrotFractalConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new OrbitTrapConfigElementXMLExporter().exportToElement(configElement.getOrbitTrapConfigElement(), builder));
	}
}
