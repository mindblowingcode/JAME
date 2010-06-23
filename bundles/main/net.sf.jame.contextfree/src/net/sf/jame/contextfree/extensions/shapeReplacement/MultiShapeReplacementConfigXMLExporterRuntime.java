/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLExporter;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLExporter;
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
public class MultiShapeReplacementConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<MultiShapeReplacementConfig> createXMLExporter() {
		return new MultiReplacementConfigXMLExporter();
	}

	private class MultiReplacementConfigXMLExporter extends XMLExporter<MultiShapeReplacementConfig> {
		protected String getConfigElementClassId() {
			return "MultiShapeReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final MultiShapeReplacementConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
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
		protected void exportProperties(final MultiShapeReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportTimes(extensionConfig, createProperty(builder, element, "times"), builder);
			exportShapeReplacementListElement(extensionConfig, createProperty(builder, element, "shapeReplacementList"), builder);
			exportShapeAdjustmentListElement(extensionConfig, createProperty(builder, element, "shapeAdjustmentList"), builder);
		}
	
		private void exportTimes(final MultiShapeReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new IntegerElementXMLExporter().exportToElement(extensionConfig.getTimesElement(), builder));
		}
		private void exportShapeReplacementListElement(final MultiShapeReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final ShapeReplacementConfigElementXMLExporter shapeReplacementExporter = new ShapeReplacementConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getShapeReplacementConfigElementCount(); i++) {
				element.appendChild(shapeReplacementExporter.exportToElement(extensionConfig.getShapeReplacementConfigElement(i), builder));
			}
		}
		private void exportShapeAdjustmentListElement(final MultiShapeReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final ShapeAdjustmentConfigElementXMLExporter shapeAdjustmentExporter = new ShapeAdjustmentConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getShapeAdjustmentConfigElementCount(); i++) {
				element.appendChild(shapeAdjustmentExporter.exportToElement(extensionConfig.getShapeAdjustmentConfigElement(i), builder));
			}
		}
	}
}
