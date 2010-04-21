/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import java.util.List;

import net.sf.jame.contextfree.ContextFreeRegistry;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLImporter;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class PathOperationConfigElementXMLImporter extends XMLImporter<PathOperationConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public PathOperationConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, PathOperationConfigElement.CLASS_ID);
		final PathOperationConfigElement configElement = new PathOperationConfigElement();
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
	protected void importProperties(final PathOperationConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importExtension(configElement, propertyElements.get(0));
		importPathAdjustmentListElement(configElement, propertyElements.get(1));
	}

	private void importExtension(final PathOperationConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> extensionElements = this.getElements(element, ConfigurableExtensionReferenceElement.CLASS_ID);
		if (extensionElements.size() == 1) {
			configElement.setExtensionReference(new ConfigurableExtensionReferenceElementXMLImporter<PathOperationExtensionConfig>(ContextFreeRegistry.getInstance().getPathOperationRegistry()).importFromElement(extensionElements.get(0)).getReference());
		}
	}
	private void importPathAdjustmentListElement(final PathOperationConfigElement configElement, final Element element) throws XMLImportException {
		final PathAdjustmentConfigElementXMLImporter pathAdjustmentImporter = new PathAdjustmentConfigElementXMLImporter();
		final List<Element> pathAdjustmentElements = this.getElements(element, PathAdjustmentConfigElement.CLASS_ID);
		for (int i = 0; i < pathAdjustmentElements.size(); i++) {
			configElement.appendPathAdjustmentConfigElement(pathAdjustmentImporter.importFromElement(pathAdjustmentElements.get(i)));
		}
	}
}
