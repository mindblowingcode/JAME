/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.path;

import net.sf.jame.contextfree.cfdg.pathOperation.PathOperationConfigElementXMLExporter;
import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class PathConfigElementXMLExporter extends XMLExporter<PathConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final PathConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, PathConfigElement.CLASS_ID);
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
	protected void exportProperties(final PathConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportName(configElement, createProperty(builder, element, "name"), builder);
		exportPathOperationListElement(configElement, createProperty(builder, element, "pathOperationList"), builder);
	}

	private void exportName(final PathConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getNameElement(), builder));
	}
	private void exportPathOperationListElement(final PathConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final PathOperationConfigElementXMLExporter pathOperationExporter = new PathOperationConfigElementXMLExporter();
		for (int i = 0; i < configElement.getPathOperationConfigElementCount(); i++) {
			element.appendChild(pathOperationExporter.exportToElement(configElement.getPathOperationConfigElement(i), builder));
		}
	}
}
