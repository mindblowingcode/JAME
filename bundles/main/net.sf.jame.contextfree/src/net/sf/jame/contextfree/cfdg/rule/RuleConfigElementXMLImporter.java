/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.rule;

import java.util.List;

import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLImporter;
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
		importShapeReplacementListElement(configElement, propertyElements.get(2));
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
	private void importShapeReplacementListElement(final RuleConfigElement configElement, final Element element) throws XMLImportException {
		final ShapeReplacementConfigElementXMLImporter shapeReplacementImporter = new ShapeReplacementConfigElementXMLImporter();
		final List<Element> shapeReplacementElements = this.getElements(element, ShapeReplacementConfigElement.CLASS_ID);
		for (int i = 0; i < shapeReplacementElements.size(); i++) {
			configElement.appendShapeReplacementConfigElement(shapeReplacementImporter.importFromElement(shapeReplacementElements.get(i)));
		}
	}
}
