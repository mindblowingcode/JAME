/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import java.util.List;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class SingleReplacementConfigElementXMLImporter extends XMLImporter<SingleReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public SingleReplacementConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, SingleReplacementConfigElement.CLASS_ID);
		final SingleReplacementConfigElement configElement = new SingleReplacementConfigElement();
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
	protected void importProperties(final SingleReplacementConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importShape(configElement, propertyElements.get(0));
		importShapeAdjustmentListElement(configElement, propertyElements.get(1));
	}

	private void importShape(final SingleReplacementConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> shapeElements = this.getElements(element, StringElement.CLASS_ID);
		if (shapeElements.size() == 1) {
			configElement.setShape(new StringElementXMLImporter().importFromElement(shapeElements.get(0)).getValue());
		}
	}
	private void importShapeAdjustmentListElement(final SingleReplacementConfigElement configElement, final Element element) throws XMLImportException {
		final ShapeAdjustmentConfigElementXMLImporter shapeAdjustmentImporter = new ShapeAdjustmentConfigElementXMLImporter();
		final List<Element> shapeAdjustmentElements = this.getElements(element, ShapeAdjustmentConfigElement.CLASS_ID);
		for (int i = 0; i < shapeAdjustmentElements.size(); i++) {
			configElement.appendShapeAdjustmentConfigElement(shapeAdjustmentImporter.importFromElement(shapeAdjustmentElements.get(i)));
		}
	}
}
