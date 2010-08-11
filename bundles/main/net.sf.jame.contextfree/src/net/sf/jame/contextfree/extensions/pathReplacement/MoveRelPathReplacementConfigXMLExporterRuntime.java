/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import net.sf.jame.core.common.FloatElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MoveRelPathReplacementConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<MoveRelPathReplacementConfig> createXMLExporter() {
		return new MoveRelPathReplacementConfigXMLExporter();
	}

	private class MoveRelPathReplacementConfigXMLExporter extends XMLExporter<MoveRelPathReplacementConfig> {
		protected String getConfigElementClassId() {
			return "MoveRelPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final MoveRelPathReplacementConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
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
		protected void exportProperties(final MoveRelPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportX(extensionConfig, createProperty(builder, element, "x"), builder);
			exportY(extensionConfig, createProperty(builder, element, "y"), builder);
		}
	
		private void exportX(final MoveRelPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new FloatElementXMLExporter().exportToElement(extensionConfig.getXElement(), builder));
		}
		private void exportY(final MoveRelPathReplacementConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new FloatElementXMLExporter().exportToElement(extensionConfig.getYElement(), builder));
		}
	}
}
