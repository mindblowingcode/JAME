/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementXMLExporter;
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
public class PathFigureConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<PathFigureConfig> createXMLExporter() {
		return new PathFigureConfigXMLExporter();
	}

	private class PathFigureConfigXMLExporter extends XMLExporter<PathFigureConfig> {
		protected String getConfigElementClassId() {
			return "PathFigureConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final PathFigureConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
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
		protected void exportProperties(final PathFigureConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportName(extensionConfig, createProperty(builder, element, "name"), builder);
			exportPathReplacementListElement(extensionConfig, createProperty(builder, element, "pathReplacementList"), builder);
		}
	
		private void exportName(final PathFigureConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new StringElementXMLExporter().exportToElement(extensionConfig.getNameElement(), builder));
		}
		private void exportPathReplacementListElement(final PathFigureConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			final PathReplacementConfigElementXMLExporter pathReplacementExporter = new PathReplacementConfigElementXMLExporter();
			for (int i = 0; i < extensionConfig.getPathReplacementConfigElementCount(); i++) {
				element.appendChild(pathReplacementExporter.exportToElement(extensionConfig.getPathReplacementConfigElement(i), builder));
			}
		}
	}
}
