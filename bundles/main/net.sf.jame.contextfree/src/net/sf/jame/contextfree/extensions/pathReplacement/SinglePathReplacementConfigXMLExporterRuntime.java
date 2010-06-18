/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLExporter;
import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class SinglePathReplacementConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<SinglePathReplacementConfig> createXMLExporter() {
		return new SingleReplacementConfigXMLExporter();
	}

	private class SingleReplacementConfigXMLExporter extends XMLExporter<SinglePathReplacementConfig> {
		protected String getConfigElementClassId() {
			return "SinglePathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final SinglePathReplacementConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
			final Element element = this.createElement(builder, this.getConfigElementClassId());
			try {
				exportProperties(extensionConfig, element, builder);
			}
			catch (final ExtensionException e) {
				throw new XMLExportException(e);
			}
			return element;
		}
	
		/**
		 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter#exportProperties(net.sf.jame.twister.util.ConfigurableExtensionConfigElement, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder, java.lang.String)
		 */
		protected void exportProperties(final SinglePathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportPath(extensionConfig, createProperty(builder, element, "path"), builder);
			exportPathAdjustmentListElement(extensionConfig, createProperty(builder, element, "pathAdjustmentList"), builder);
		}
	
		private void exportPath(final SinglePathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new StringElementXMLExporter().exportToElement(extensionConfig.getPathElement(), builder));
		}
		private void exportPathAdjustmentListElement(final SinglePathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final PathAdjustmentConfigElementXMLExporter pathAdjustmentExporter = new PathAdjustmentConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getPathAdjustmentConfigElementCount(); i++) {
				element.appendChild(pathAdjustmentExporter.exportToElement(extensionConfig.getPathAdjustmentConfigElement(i), builder));
			}
		}
	}
}
