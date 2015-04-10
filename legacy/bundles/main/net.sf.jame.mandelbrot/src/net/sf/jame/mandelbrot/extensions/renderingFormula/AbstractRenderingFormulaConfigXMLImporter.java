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
package net.sf.jame.mandelbrot.extensions.renderingFormula;

import java.util.List;

import net.sf.jame.core.common.ComplexElement;
import net.sf.jame.core.common.ComplexElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.mandelbrot.common.IterationsElement;
import net.sf.jame.mandelbrot.common.IterationsElementXMLImporter;
import net.sf.jame.mandelbrot.common.ThresholdElement;
import net.sf.jame.mandelbrot.common.ThresholdElementXMLImporter;
import net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractRenderingFormulaConfigXMLImporter<T extends RenderingFormulaExtensionConfig> extends XMLImporter<T> {
	/**
	 * @return
	 */
	protected abstract String getConfigElementClassId();

	/**
	 * @return
	 */
	protected abstract T createExtensionConfig();

	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public T importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, this.getConfigElementClassId());
		final T config = this.createExtensionConfig();
		final List<Element> propertyElements = getProperties(element);
		importProperties(config, propertyElements);
		return config;
	}

	/**
	 * @return
	 */
	protected int getPropertiesSize() {
		return 4;
	}

	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	protected void importProperties(final T config, final List<Element> propertyElements) throws XMLImportException {
		this.importIterations(config, propertyElements.get(0));
		this.importThreshold(config, propertyElements.get(1));
		this.importCenter(config, propertyElements.get(2));
		this.importScale(config, propertyElements.get(3));
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importIterations(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, IterationsElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setIterations(new IterationsElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importThreshold(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ThresholdElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setThreshold(new ThresholdElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importCenter(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ComplexElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setCenter(new ComplexElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importScale(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ComplexElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setScale(new ComplexElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}
}
