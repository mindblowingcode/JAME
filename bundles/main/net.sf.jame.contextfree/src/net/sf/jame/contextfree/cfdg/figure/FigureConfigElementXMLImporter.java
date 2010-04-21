/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.figure;

import java.util.List;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class FigureConfigElementXMLImporter extends XMLImporter<FigureConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public FigureConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, FigureConfigElement.CLASS_ID);
		final FigureConfigElement configElement = new FigureConfigElement();
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
	protected void importProperties(final FigureConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importName(configElement, propertyElements.get(0));
		importExtension(configElement, propertyElements.get(1));
	}

	private void importName(final FigureConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> nameElements = this.getElements(element, StringElement.CLASS_ID);
		if (nameElements.size() == 1) {
			configElement.setName(new StringElementXMLImporter().importFromElement(nameElements.get(0)).getValue());
		}
	}
	private void importExtension(final FigureConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> extensionElements = this.getElements(element, ConfigurableExtensionReferenceElement.CLASS_ID);
		if (extensionElements.size() == 1) {
			configElement.setExtensionReference(new ConfigurableExtensionReferenceElementXMLImporter<FigureExtensionConfig>(ContextFreeRegistry.getInstance().getFigureRegistry()).importFromElement(extensionElements.get(0)).getReference());
		}
	}
}
