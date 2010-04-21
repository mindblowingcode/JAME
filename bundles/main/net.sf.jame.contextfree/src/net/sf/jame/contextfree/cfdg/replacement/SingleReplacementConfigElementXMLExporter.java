/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.replacement;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLExporter;
import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class SingleReplacementConfigElementXMLExporter extends XMLExporter<SingleReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final SingleReplacementConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, SingleReplacementConfigElement.CLASS_ID);
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
	protected void exportProperties(final SingleReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportShape(configElement, createProperty(builder, element, "shape"), builder);
		exportShapeAdjustmentListElement(configElement, createProperty(builder, element, "shapeAdjustmentList"), builder);
	}

	private void exportShape(final SingleReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getShapeElement(), builder));
	}
	private void exportShapeAdjustmentListElement(final SingleReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ShapeAdjustmentConfigElementXMLExporter shapeAdjustmentExporter = new ShapeAdjustmentConfigElementXMLExporter();
		for (int i = 0; i < configElement.getShapeAdjustmentConfigElementCount(); i++) {
			element.appendChild(shapeAdjustmentExporter.exportToElement(configElement.getShapeAdjustmentConfigElement(i), builder));
		}
	}
}
