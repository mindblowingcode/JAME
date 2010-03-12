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
package net.sf.jame.contextfree.cfdg.path;

import java.util.List;

import net.sf.jame.contextfree.cfdg.path.operation.PathOperationConfigElement;
import net.sf.jame.contextfree.cfdg.path.operation.PathOperationConfigElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class PathConfigElementXMLImporter extends XMLImporter<PathConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public PathConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, PathConfigElement.CLASS_ID);
		final PathConfigElement configElement = new PathConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 2) {
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
	protected void importProperties(final PathConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importName(configElement, propertyElements.get(0));
		importPathOperationListElement(configElement, propertyElements.get(1));
	}

	private void importName(final PathConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> nameElements = this.getElements(element, StringElement.CLASS_ID);
		if (nameElements.size() == 1) {
			configElement.setName(new StringElementXMLImporter().importFromElement(nameElements.get(0)).getValue());
		}
	}

	private void importPathOperationListElement(final PathConfigElement configElement, final Element element) throws XMLImportException {
		final PathOperationConfigElementXMLImporter pathOperationImporter = new PathOperationConfigElementXMLImporter();
		final List<Element> pathOperationElements = this.getElements(element, PathOperationConfigElement.CLASS_ID);
		for (int i = 0; i < pathOperationElements.size(); i++) {
			configElement.appendPathOperationConfigElement(pathOperationImporter.importFromElement(pathOperationElements.get(i)));
		}
	}
}
