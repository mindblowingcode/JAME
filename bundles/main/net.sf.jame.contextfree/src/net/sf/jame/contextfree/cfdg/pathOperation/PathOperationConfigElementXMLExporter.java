/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.cfdg.pathOperation;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLExporter;
import net.sf.jame.contextfree.cfdg.pathOperation.extension.PathOperationExtensionConfig;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class PathOperationConfigElementXMLExporter extends XMLExporter<PathOperationConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final PathOperationConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, PathOperationConfigElement.CLASS_ID);
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
	protected void exportProperties(final PathOperationConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportExtension(configElement, createProperty(builder, element, "extension"), builder);
		exportPathAdjustmentListElement(configElement, createProperty(builder, element, "pathAdjustmentList"), builder);
	}

	private void exportExtension(final PathOperationConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ConfigurableExtensionReferenceElementXMLExporter<PathOperationExtensionConfig>().exportToElement(configElement.getExtensionElement(), builder));
	}
	private void exportPathAdjustmentListElement(final PathOperationConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final PathAdjustmentConfigElementXMLExporter pathAdjustmentExporter = new PathAdjustmentConfigElementXMLExporter();
		for (int i = 0; i < configElement.getPathAdjustmentConfigElementCount(); i++) {
			element.appendChild(pathAdjustmentExporter.exportToElement(configElement.getPathAdjustmentConfigElement(i), builder));
		}
	}
}
