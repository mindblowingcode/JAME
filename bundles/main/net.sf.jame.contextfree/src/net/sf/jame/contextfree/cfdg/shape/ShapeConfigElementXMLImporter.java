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
package net.sf.jame.contextfree.cfdg.shape;

import java.util.List;

import net.sf.jame.contextfree.cfdg.shape.replacement.LoopShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.LoopShapeReplacementConfigElementXMLImporter;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class ShapeConfigElementXMLImporter extends XMLImporter<ShapeConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public ShapeConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, ShapeConfigElement.CLASS_ID);
		final ShapeConfigElement configElement = new ShapeConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 1) {
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
	protected void importProperties(final ShapeConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importReplacementListElement(configElement, propertyElements.get(0));
	}

	private void importReplacementListElement(final ShapeConfigElement configElement, final Element element) throws XMLImportException {
		final ShapeReplacementConfigElementXMLImporter shapeReplacementImporter = new ShapeReplacementConfigElementXMLImporter();
		final LoopShapeReplacementConfigElementXMLImporter loopShapeReplacementImporter = new LoopShapeReplacementConfigElementXMLImporter();
		final List<Element> replacementElements = this.getElements(element, new String[] { ShapeReplacementConfigElement.CLASS_ID, LoopShapeReplacementConfigElement.CLASS_ID });
		for (int i = 0; i < replacementElements.size(); i++) {
			if (getClassId(replacementElements.get(i)).equals(ShapeReplacementConfigElement.CLASS_ID)) {
				configElement.appendReplacementConfigElement(shapeReplacementImporter.importFromElement(replacementElements.get(i)));
			} else if (getClassId(replacementElements.get(i)).equals(LoopShapeReplacementConfigElement.CLASS_ID)) {
				configElement.appendReplacementConfigElement(loopShapeReplacementImporter.importFromElement(replacementElements.get(i)));
			}
		}
	}
}
