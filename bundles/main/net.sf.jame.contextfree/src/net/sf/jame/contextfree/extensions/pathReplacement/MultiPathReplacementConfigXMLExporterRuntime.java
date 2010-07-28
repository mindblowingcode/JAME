/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLExporter;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementXMLExporter;
import net.sf.jame.core.common.IntegerElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MultiPathReplacementConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<MultiPathReplacementConfig> createXMLExporter() {
		return new MultiPathReplacementConfigXMLExporter();
	}

	private class MultiPathReplacementConfigXMLExporter extends XMLExporter<MultiPathReplacementConfig> {
		protected String getConfigElementClassId() {
			return "MultiPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final MultiPathReplacementConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
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
		protected void exportProperties(final MultiPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportTimes(extensionConfig, createProperty(builder, element, "times"), builder);
			exportPathReplacementListElement(extensionConfig, createProperty(builder, element, "pathReplacementList"), builder);
			exportPathAdjustmentListElement(extensionConfig, createProperty(builder, element, "pathAdjustmentList"), builder);
		}
	
		private void exportTimes(final MultiPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new IntegerElementXMLExporter().exportToElement(extensionConfig.getTimesElement(), builder));
		}
		private void exportPathReplacementListElement(final MultiPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final PathReplacementConfigElementXMLExporter pathReplacementExporter = new PathReplacementConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getPathReplacementConfigElementCount(); i++) {
				element.appendChild(pathReplacementExporter.exportToElement(extensionConfig.getPathReplacementConfigElement(i), builder));
			}
		}
		private void exportPathAdjustmentListElement(final MultiPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final PathAdjustmentConfigElementXMLExporter pathAdjustmentExporter = new PathAdjustmentConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getPathAdjustmentConfigElementCount(); i++) {
				element.appendChild(pathAdjustmentExporter.exportToElement(extensionConfig.getPathAdjustmentConfigElement(i), builder));
			}
		}
	}
}
