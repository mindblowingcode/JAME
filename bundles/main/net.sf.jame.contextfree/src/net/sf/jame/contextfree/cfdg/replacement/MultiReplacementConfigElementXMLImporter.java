/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import java.util.List;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLImporter;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLImporter;
import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.common.IntegerElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

public class MultiReplacementConfigElementXMLImporter extends XMLImporter<MultiReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public MultiReplacementConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, MultiReplacementConfigElement.CLASS_ID);
		final MultiReplacementConfigElement configElement = new MultiReplacementConfigElement();
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
	protected void importProperties(final MultiReplacementConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importTimes(configElement, propertyElements.get(0));
		importShapeReplacementListElement(configElement, propertyElements.get(1));
		importShapeAdjustmentListElement(configElement, propertyElements.get(2));
	}

	private void importTimes(final MultiReplacementConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> timesElements = this.getElements(element, IntegerElement.CLASS_ID);
		if (timesElements.size() == 1) {
			configElement.setTimes(new IntegerElementXMLImporter().importFromElement(timesElements.get(0)).getValue());
		}
	}
	private void importShapeReplacementListElement(final MultiReplacementConfigElement configElement, final Element element) throws XMLImportException {
		final ShapeReplacementConfigElementXMLImporter shapeReplacementImporter = new ShapeReplacementConfigElementXMLImporter();
		final List<Element> shapeReplacementElements = this.getElements(element, ShapeReplacementConfigElement.CLASS_ID);
		for (int i = 0; i < shapeReplacementElements.size(); i++) {
			configElement.appendShapeReplacementConfigElement(shapeReplacementImporter.importFromElement(shapeReplacementElements.get(i)));
		}
	}
	private void importShapeAdjustmentListElement(final MultiReplacementConfigElement configElement, final Element element) throws XMLImportException {
		final ShapeAdjustmentConfigElementXMLImporter shapeAdjustmentImporter = new ShapeAdjustmentConfigElementXMLImporter();
		final List<Element> shapeAdjustmentElements = this.getElements(element, ShapeAdjustmentConfigElement.CLASS_ID);
		for (int i = 0; i < shapeAdjustmentElements.size(); i++) {
			configElement.appendShapeAdjustmentConfigElement(shapeAdjustmentImporter.importFromElement(shapeAdjustmentElements.get(i)));
		}
	}
}
