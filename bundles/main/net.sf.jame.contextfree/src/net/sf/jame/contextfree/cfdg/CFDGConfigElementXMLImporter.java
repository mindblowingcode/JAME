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
package net.sf.jame.contextfree.cfdg;

import java.util.List;

import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.contextfree.cfdg.path.PathConfigElementXMLImporter;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElementXMLImporter;
import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLImporter;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.FloatElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class CFDGConfigElementXMLImporter extends XMLImporter<CFDGConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public CFDGConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, CFDGConfigElement.CLASS_ID);
		final CFDGConfigElement configElement = new CFDGConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 9) {
			try {
				importProperties(configElement, propertyElements);
			}
			catch (final ExtensionException e) {
				throw new XMLImportException(e);
			}
		}
		return configElement;
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws ExtensionException
	 * @throws XMLImportException
	 */
	protected void importProperties(final CFDGConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importStartshape(configElement, propertyElements.get(0));
		importX(configElement, propertyElements.get(1));
		importY(configElement, propertyElements.get(2));
		importWidth(configElement, propertyElements.get(3));
		importHeight(configElement, propertyElements.get(4));
		importTileWidth(configElement, propertyElements.get(5));
		importTileHeight(configElement, propertyElements.get(6));
		importBackground(configElement, propertyElements.get(7));
		importFigureListElement(configElement, propertyElements.get(8));
	}

	private void importStartshape(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> startshapeElements = this.getElements(element, StringElement.CLASS_ID);
		if (startshapeElements.size() == 1) {
			configElement.setStartshape(new StringElementXMLImporter().importFromElement(startshapeElements.get(0)).getValue());
		}
	}

	private void importX(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> xElements = this.getElements(element, FloatElement.CLASS_ID);
		if (xElements.size() == 1) {
			configElement.setX(new FloatElementXMLImporter().importFromElement(xElements.get(0)).getValue());
		}
	}

	private void importY(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> yElements = this.getElements(element, FloatElement.CLASS_ID);
		if (yElements.size() == 1) {
			configElement.setY(new FloatElementXMLImporter().importFromElement(yElements.get(0)).getValue());
		}
	}

	private void importWidth(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> widthElements = this.getElements(element, FloatElement.CLASS_ID);
		if (widthElements.size() == 1) {
			configElement.setWidth(new FloatElementXMLImporter().importFromElement(widthElements.get(0)).getValue());
		}
	}

	private void importHeight(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> heightElements = this.getElements(element, FloatElement.CLASS_ID);
		if (heightElements.size() == 1) {
			configElement.setHeight(new FloatElementXMLImporter().importFromElement(heightElements.get(0)).getValue());
		}
	}

	private void importTileWidth(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> tileWidthElements = this.getElements(element, FloatElement.CLASS_ID);
		if (tileWidthElements.size() == 1) {
			configElement.setTileWidth(new FloatElementXMLImporter().importFromElement(tileWidthElements.get(0)).getValue());
		}
	}

	private void importTileHeight(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> tileHeightElements = this.getElements(element, FloatElement.CLASS_ID);
		if (tileHeightElements.size() == 1) {
			configElement.setTileHeight(new FloatElementXMLImporter().importFromElement(tileHeightElements.get(0)).getValue());
		}
	}

	private void importBackground(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> backgroundElements = this.getElements(element, ColorElement.CLASS_ID);
		if (backgroundElements.size() == 1) {
			configElement.setBackground(new ColorElementXMLImporter().importFromElement(backgroundElements.get(0)).getValue());
		}
	}

	private void importFigureListElement(final CFDGConfigElement configElement, final Element element) throws XMLImportException {
		final RuleConfigElementXMLImporter ruleImporter = new RuleConfigElementXMLImporter();
		final PathConfigElementXMLImporter pathImporter = new PathConfigElementXMLImporter();
		final List<Element> figureElements = this.getElements(element, new String[] { RuleConfigElement.CLASS_ID, PathConfigElement.CLASS_ID });
		for (int i = 0; i < figureElements.size(); i++) {
			if (getClassId(figureElements.get(i)).equals(RuleConfigElement.CLASS_ID)) {
				configElement.appendFigureConfigElement(ruleImporter.importFromElement(figureElements.get(i)));
			} else if (getClassId(figureElements.get(i)).equals(PathConfigElement.CLASS_ID)) {
				configElement.appendFigureConfigElement(pathImporter.importFromElement(figureElements.get(i)));
			}
		}
	}
}
