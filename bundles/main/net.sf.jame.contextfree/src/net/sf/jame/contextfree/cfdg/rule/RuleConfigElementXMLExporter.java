/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.rule;

import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLExporter;
import net.sf.jame.core.common.FloatElementXMLExporter;
import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class RuleConfigElementXMLExporter extends XMLExporter<RuleConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final RuleConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, RuleConfigElement.CLASS_ID);
		try {
			exportProperties(configElement, element, builder);
		}
		catch (final ExtensionException e) {
			throw new XMLExportException(e);
		}
		return element;
	}

	/**
	 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter#exportProperties(net.sf.jame.twister.util.ConfigurableExtensionConfigElement, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder, java.lang.String)
	 */
	protected void exportProperties(final RuleConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportName(configElement, createProperty(builder, element, "name"), builder);
		exportPropability(configElement, createProperty(builder, element, "propability"), builder);
		exportShapeReplacementListElement(configElement, createProperty(builder, element, "shapeReplacementList"), builder);
	}

	private void exportName(final RuleConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getNameElement(), builder));
	}
	private void exportPropability(final RuleConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getPropabilityElement(), builder));
	}
	private void exportShapeReplacementListElement(final RuleConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ShapeReplacementConfigElementXMLExporter shapeReplacementExporter = new ShapeReplacementConfigElementXMLExporter();
		for (int i = 0; i < configElement.getShapeReplacementConfigElementCount(); i++) {
			element.appendChild(shapeReplacementExporter.exportToElement(configElement.getShapeReplacementConfigElement(i), builder));
		}
	}
}
