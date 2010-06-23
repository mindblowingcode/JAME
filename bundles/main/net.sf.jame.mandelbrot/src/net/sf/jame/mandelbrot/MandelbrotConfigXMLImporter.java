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

import java.util.List;

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.BooleanElementXMLImporter;
import net.sf.jame.core.common.ComplexElement;
import net.sf.jame.core.common.ComplexElementXMLImporter;
import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.common.IntegerElementXMLImporter;
import net.sf.jame.core.common.RectangleElement;
import net.sf.jame.core.common.RectangleElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.mandelbrot.fractal.MandelbrotFractalConfigElement;
import net.sf.jame.mandelbrot.fractal.MandelbrotFractalConfigElementXMLImporter;
import net.sf.jame.twister.common.SpeedElement;
import net.sf.jame.twister.common.SpeedElementXMLImporter;
import net.sf.jame.twister.common.ViewElement;
import net.sf.jame.twister.common.ViewElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MandelbrotConfigXMLImporter extends XMLImporter<MandelbrotConfig> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public MandelbrotConfig importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, MandelbrotConfig.CLASS_ID);
		final MandelbrotConfig config = new MandelbrotConfig();
		final List<Element> propertyElements = getProperties(element);
		if (isVersion(element, 3) && (propertyElements.size() == 9)) {
			importProperties3(config, propertyElements);
		}
		else if (isVersion(element, 2) && (propertyElements.size() == 8)) {
			importProperties2(config, propertyElements);
		}
		else if (isVersion(element, 1) && (propertyElements.size() == 7)) {
			importProperties1(config, propertyElements);
		}
		else if (isVersion(element, 0) && (propertyElements.size() == 7)) {
			importProperties0(config, propertyElements);
		}
		return config;
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties0(final MandelbrotConfig config, final List<Element> propertyElements) throws XMLImportException {
		importMandelbrotFractal(config, propertyElements.get(0));
		importConstant(config, propertyElements.get(1));
		importView(config, propertyElements.get(2));
		importImageMode(config, propertyElements.get(3));
		importPreviewArea(config, propertyElements.get(4));
		importShowPreview(config, propertyElements.get(5));
		importShowOrbit(config, propertyElements.get(6));
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties1(final MandelbrotConfig config, final List<Element> propertyElements) throws XMLImportException {
		importMandelbrotFractal(config, propertyElements.get(0));
		importConstant(config, propertyElements.get(1));
		importView(config, propertyElements.get(2));
		importImageMode(config, propertyElements.get(3));
		importPreviewArea(config, propertyElements.get(4));
		importShowPreview(config, propertyElements.get(5));
		importShowOrbit(config, propertyElements.get(6));
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties2(final MandelbrotConfig config, final List<Element> propertyElements) throws XMLImportException {
		importMandelbrotFractal(config, propertyElements.get(0));
		importConstant(config, propertyElements.get(1));
		importView(config, propertyElements.get(2));
		importImageMode(config, propertyElements.get(3));
		importPreviewArea(config, propertyElements.get(4));
		importShowPreview(config, propertyElements.get(5));
		importShowOrbit(config, propertyElements.get(6));
		importSpeed(config, propertyElements.get(7));
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties3(final MandelbrotConfig config, final List<Element> propertyElements) throws XMLImportException {
		importMandelbrotFractal(config, propertyElements.get(0));
		importConstant(config, propertyElements.get(1));
		importView(config, propertyElements.get(2));
		importImageMode(config, propertyElements.get(3));
		importPreviewArea(config, propertyElements.get(4));
		importShowPreview(config, propertyElements.get(5));
		importShowOrbit(config, propertyElements.get(6));
		importShowOrbitTrap(config, propertyElements.get(7));
		importSpeed(config, propertyElements.get(8));
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importMandelbrotFractal(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, MandelbrotFractalConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			final MandelbrotFractalConfigElement mandelbrotFractal = new MandelbrotFractalConfigElementXMLImporter().importFromElement(elements.get(0));
			config.setMandelbrotFractalConfigElement(mandelbrotFractal);
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importConstant(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ComplexElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setConstant(new ComplexElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importView(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ViewElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setView(new ViewElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importImageMode(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, IntegerElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setImageMode(new IntegerElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importPreviewArea(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, RectangleElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setPreviewArea(new RectangleElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importShowPreview(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, BooleanElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setShowPreview(new BooleanElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importShowOrbit(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, BooleanElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setShowOrbit(new BooleanElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importShowOrbitTrap(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, BooleanElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setShowOrbitTrap(new BooleanElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importSpeed(final MandelbrotConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, SpeedElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setSpeed(new SpeedElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}
}
