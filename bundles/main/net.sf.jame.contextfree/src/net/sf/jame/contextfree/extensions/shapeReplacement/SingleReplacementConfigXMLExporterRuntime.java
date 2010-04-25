/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLExporter;
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
public class SingleReplacementConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<SingleReplacementConfig> createXMLExporter() {
		return new SingleReplacementConfigXMLExporter();
	}

	private class SingleReplacementConfigXMLExporter extends XMLExporter<SingleReplacementConfig> {
		protected String getConfigElementClassId() {
			return "SingleReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final SingleReplacementConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
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
		protected void exportProperties(final SingleReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportShape(extensionConfig, createProperty(builder, element, "shape"), builder);
			exportShapeAdjustmentListElement(extensionConfig, createProperty(builder, element, "shapeAdjustmentList"), builder);
		}
	
		private void exportShape(final SingleReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new StringElementXMLExporter().exportToElement(extensionConfig.getShapeElement(), builder));
		}
		private void exportShapeAdjustmentListElement(final SingleReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final ShapeAdjustmentConfigElementXMLExporter shapeAdjustmentExporter = new ShapeAdjustmentConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getShapeAdjustmentConfigElementCount(); i++) {
				element.appendChild(shapeAdjustmentExporter.exportToElement(extensionConfig.getShapeAdjustmentConfigElement(i), builder));
			}
		}
	}
}
