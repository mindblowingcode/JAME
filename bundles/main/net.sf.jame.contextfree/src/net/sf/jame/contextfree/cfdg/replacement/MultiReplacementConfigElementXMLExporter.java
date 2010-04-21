/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLExporter;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLExporter;
import net.sf.jame.core.common.IntegerElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class MultiReplacementConfigElementXMLExporter extends XMLExporter<MultiReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final MultiReplacementConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, MultiReplacementConfigElement.CLASS_ID);
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
	protected void exportProperties(final MultiReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportTimes(configElement, createProperty(builder, element, "times"), builder);
		exportShapeReplacementListElement(configElement, createProperty(builder, element, "shapeReplacementList"), builder);
		exportShapeAdjustmentListElement(configElement, createProperty(builder, element, "shapeAdjustmentList"), builder);
	}

	private void exportTimes(final MultiReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new IntegerElementXMLExporter().exportToElement(configElement.getTimesElement(), builder));
	}
	private void exportShapeReplacementListElement(final MultiReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ShapeReplacementConfigElementXMLExporter shapeReplacementExporter = new ShapeReplacementConfigElementXMLExporter();
		for (int i = 0; i < configElement.getShapeReplacementConfigElementCount(); i++) {
			element.appendChild(shapeReplacementExporter.exportToElement(configElement.getShapeReplacementConfigElement(i), builder));
		}
	}
	private void exportShapeAdjustmentListElement(final MultiReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ShapeAdjustmentConfigElementXMLExporter shapeAdjustmentExporter = new ShapeAdjustmentConfigElementXMLExporter();
		for (int i = 0; i < configElement.getShapeAdjustmentConfigElementCount(); i++) {
			element.appendChild(shapeAdjustmentExporter.exportToElement(configElement.getShapeAdjustmentConfigElement(i), builder));
		}
	}
}
