/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.shapeReplacement;

import net.sf.jame.contextfree.cfdg.shapeReplacement.extension.ShapeReplacementExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementConfigElementXMLExporter extends XMLExporter<ShapeReplacementConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final ShapeReplacementConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, ShapeReplacementConfigElement.CLASS_ID);
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
	protected void exportProperties(final ShapeReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportExtension(configElement, createProperty(builder, element, "extension"), builder);
	}

	private void exportExtension(final ShapeReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ConfigurableExtensionReferenceElementXMLExporter<ShapeReplacementExtensionConfig>().exportToElement(configElement.getExtensionElement(), builder));
	}
}
