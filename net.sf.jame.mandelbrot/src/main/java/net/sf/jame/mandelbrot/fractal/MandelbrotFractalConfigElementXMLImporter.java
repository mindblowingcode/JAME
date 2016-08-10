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
package net.sf.jame.mandelbrot.fractal;

import java.util.List;

import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.mandelbrot.incolouringFormula.IncolouringFormulaConfigElement;
import net.sf.jame.mandelbrot.incolouringFormula.IncolouringFormulaConfigElementXMLImporter;
import net.sf.jame.mandelbrot.orbitTrap.OrbitTrapConfigElement;
import net.sf.jame.mandelbrot.orbitTrap.OrbitTrapConfigElementXMLImporter;
import net.sf.jame.mandelbrot.outcolouringFormula.OutcolouringFormulaConfigElement;
import net.sf.jame.mandelbrot.outcolouringFormula.OutcolouringFormulaConfigElementXMLImporter;
import net.sf.jame.mandelbrot.processingFormula.ProcessingFormulaConfigElement;
import net.sf.jame.mandelbrot.processingFormula.ProcessingFormulaConfigElementXMLImporter;
import net.sf.jame.mandelbrot.renderingFormula.RenderingFormulaConfigElement;
import net.sf.jame.mandelbrot.renderingFormula.RenderingFormulaConfigElementXMLImporter;
import net.sf.jame.mandelbrot.transformingFormula.TransformingFormulaConfigElement;
import net.sf.jame.mandelbrot.transformingFormula.TransformingFormulaConfigElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotFractalConfigElementXMLImporter extends XMLImporter<MandelbrotFractalConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public MandelbrotFractalConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, MandelbrotFractalConfigElement.CLASS_ID);
		final MandelbrotFractalConfigElement configElement = new MandelbrotFractalConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (isVersion(element, 2) && (propertyElements.size() == 6)) {
			importProperties1(configElement, propertyElements);
		}
		else if (isVersion(element, 1) && (propertyElements.size() == 4)) {
			importProperties0(configElement, propertyElements);
		}
		else if (isVersion(element, 0) && (propertyElements.size() == 4)) {
			importProperties0(configElement, propertyElements);
		}
		return configElement;
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties0(final MandelbrotFractalConfigElement configElement, final List<Element> propertyElements) throws XMLImportException {
		importRenderingFormula(configElement, propertyElements.get(0));
		importTransformingFormula(configElement, propertyElements.get(1));
		importIncolouringFormulas(configElement, propertyElements.get(2));
		importOutcolouringFormulas(configElement, propertyElements.get(3));
		configElement.setProcessingFormulaConfigElement(new ProcessingFormulaConfigElement());
		configElement.setOrbitTrapConfigElement(new OrbitTrapConfigElement());
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties1(final MandelbrotFractalConfigElement configElement, final List<Element> propertyElements) throws XMLImportException {
		importRenderingFormula(configElement, propertyElements.get(0));
		importTransformingFormula(configElement, propertyElements.get(1));
		importIncolouringFormulas(configElement, propertyElements.get(2));
		importOutcolouringFormulas(configElement, propertyElements.get(3));
		importProcessingFormula(configElement, propertyElements.get(4));
		importOrbitTrap(configElement, propertyElements.get(5));
	}

	/**
	 * @param configElement
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importRenderingFormula(final MandelbrotFractalConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, RenderingFormulaConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			configElement.setRenderingFormulaConfigElement(new RenderingFormulaConfigElementXMLImporter().importFromElement(elements.get(0)));
		}
	}

	/**
	 * @param configElement
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importTransformingFormula(final MandelbrotFractalConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, TransformingFormulaConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			configElement.setTransformingFormulaConfigElement(new TransformingFormulaConfigElementXMLImporter().importFromElement(elements.get(0)));
		}
	}

	/**
	 * @param configElement
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importIncolouringFormulas(final MandelbrotFractalConfigElement configElement, final Element element) throws XMLImportException {
		final IncolouringFormulaConfigElementXMLImporter formulaImporter = new IncolouringFormulaConfigElementXMLImporter();
		final List<Element> formulaElements = this.getElements(element, IncolouringFormulaConfigElement.CLASS_ID);
		for (int i = 0; i < formulaElements.size(); i++) {
			configElement.appendIncolouringFormulaConfigElement(formulaImporter.importFromElement(formulaElements.get(i)));
		}
	}

	/**
	 * @param configElement
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importOutcolouringFormulas(final MandelbrotFractalConfigElement configElement, final Element element) throws XMLImportException {
		final OutcolouringFormulaConfigElementXMLImporter formulaImporter = new OutcolouringFormulaConfigElementXMLImporter();
		final List<Element> formulaElements = this.getElements(element, OutcolouringFormulaConfigElement.CLASS_ID);
		for (int i = 0; i < formulaElements.size(); i++) {
			configElement.appendOutcolouringFormulaConfigElement(formulaImporter.importFromElement(formulaElements.get(i)));
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importProcessingFormula(final MandelbrotFractalConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ProcessingFormulaConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			configElement.setProcessingFormulaConfigElement(new ProcessingFormulaConfigElementXMLImporter().importFromElement(elements.get(0)));
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importOrbitTrap(final MandelbrotFractalConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, OrbitTrapConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			configElement.setOrbitTrapConfigElement(new OrbitTrapConfigElementXMLImporter().importFromElement(elements.get(0)));
		}
	}
}
