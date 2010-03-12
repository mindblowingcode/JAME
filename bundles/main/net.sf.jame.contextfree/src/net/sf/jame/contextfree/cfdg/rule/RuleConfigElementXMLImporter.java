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
package net.sf.jame.contextfree.cfdg.rule;

import java.util.List;

import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElement;
import net.sf.jame.contextfree.cfdg.shape.ShapeConfigElementXMLImporter;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.FloatElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class RuleConfigElementXMLImporter extends XMLImporter<RuleConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public RuleConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, RuleConfigElement.CLASS_ID);
		final RuleConfigElement configElement = new RuleConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 3) {
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
	protected void importProperties(final RuleConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importName(configElement, propertyElements.get(0));
		importPropability(configElement, propertyElements.get(1));
		importShapeSingleElement(configElement, propertyElements.get(2));
	}

	private void importName(final RuleConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> nameElements = this.getElements(element, StringElement.CLASS_ID);
		if (nameElements.size() == 1) {
			configElement.setName(new StringElementXMLImporter().importFromElement(nameElements.get(0)).getValue());
		}
	}

	private void importPropability(final RuleConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> propabilityElements = this.getElements(element, FloatElement.CLASS_ID);
		if (propabilityElements.size() == 1) {
			configElement.setPropability(new FloatElementXMLImporter().importFromElement(propabilityElements.get(0)).getValue());
		}
	}

	private void importShapeSingleElement(final RuleConfigElement configElement, final Element element) throws XMLImportException {
		final ShapeConfigElementXMLImporter shapeImporter = new ShapeConfigElementXMLImporter();
		final List<Element> shapeElements = this.getElements(element, ShapeConfigElement.CLASS_ID);
		if (shapeElements.size() == 1) {
			configElement.setShapeConfigElement(shapeImporter.importFromElement(shapeElements.get(0)));
		}
	}
}
