/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.path;

import java.util.List;

import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElement;
import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElementXMLImporter;
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
