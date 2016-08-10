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
package net.sf.jame.mandelbrot.extensions.colorRenderer;

import java.util.List;

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.BooleanElementXMLImporter;
import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.common.DoubleElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.mandelbrot.colorRendererFormula.ColorRendererFormulaConfigElement;
import net.sf.jame.mandelbrot.colorRendererFormula.ColorRendererFormulaConfigElementXMLImporter;
import net.sf.jame.twister.common.PercentageElement;
import net.sf.jame.twister.common.PercentageElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractPeriodicConfigXMLImporter<T extends AbstractPeriodicConfig> extends AbstractColorRendererConfigXMLImporter<T> {
	/**
	 * @see net.sf.jame.mandelbrot.extensions.colorRenderer.AbstractColorRendererConfigXMLImporter#getPropertiesSize()
	 */
	@Override
	protected int getPropertiesSize() {
		return 6;
	}

	/**
	 * @see net.sf.jame.mandelbrot.extensions.colorRenderer.AbstractColorRendererConfigXMLImporter#importProperties(org.w3c.dom.Element, net.sf.jame.mandelbrot.colorRenderer.extension.ColorRendererExtensionConfig, java.util.List)
	 */
	@Override
	protected void importProperties(final Element element, final T config, final List<Element> propertyElements) throws XMLImportException {
		if (isVersion(element, 1)) {
			importProperties1(config, propertyElements);
		}
		else if (isVersion(element, 0)) {
			importProperties0(config, propertyElements);
		}
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties1(final T config, final List<Element> propertyElements) throws XMLImportException {
		this.importFormula(config, propertyElements.get(0));
		this.importAmplitude(config, propertyElements.get(1));
		this.importFrequency1(config, propertyElements.get(2));
		this.importScale1(config, propertyElements.get(3));
		this.importTimeEnabled(config, propertyElements.get(4));
		this.importAbsoluteEnabled(config, propertyElements.get(5));
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties0(final T config, final List<Element> propertyElements) throws XMLImportException {
		this.importFormula(config, propertyElements.get(0));
		this.importAmplitude(config, propertyElements.get(1));
		this.importFrequency0(config, propertyElements.get(2));
		this.importScale0(config, propertyElements.get(3));
		this.importTimeEnabled(config, propertyElements.get(4));
		this.importAbsoluteEnabled(config, propertyElements.get(5));
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importFormula(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ColorRendererFormulaConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getColorRendererFormulaElement().setReference(new ColorRendererFormulaConfigElementXMLImporter().importFromElement(elements.get(0)).getReference());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importAmplitude(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, PercentageElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getAmplitudeElement().setValue(new PercentageElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importFrequency0(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, PercentageElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getFrequencyElement().setValue(new Double(new PercentageElementXMLImporter().importFromElement(elements.get(0)).getValue()));
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importScale0(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, PercentageElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getScaleElement().setValue(new Double(new PercentageElementXMLImporter().importFromElement(elements.get(0)).getValue()));
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importFrequency1(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getFrequencyElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importScale1(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getScaleElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importTimeEnabled(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, BooleanElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getTimeEnabledElement().setValue(new BooleanElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importAbsoluteEnabled(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, BooleanElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getAbsoluteEnabledElement().setValue(new BooleanElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}
}
