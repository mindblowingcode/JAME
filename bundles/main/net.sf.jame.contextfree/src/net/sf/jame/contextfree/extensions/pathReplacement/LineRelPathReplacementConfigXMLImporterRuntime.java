/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.List;
import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.FloatElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class LineRelPathReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<LineRelPathReplacementConfig> createXMLImporter() {
		return new LineRelPathReplacementConfigXMLImporter();
	}

	private class LineRelPathReplacementConfigXMLImporter extends XMLImporter<LineRelPathReplacementConfig> {
		protected LineRelPathReplacementConfig createExtensionConfig() {
			return new LineRelPathReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "LineRelPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public LineRelPathReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final LineRelPathReplacementConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 2) {
				try {
					importProperties(extensionConfig, propertyElements);
				}
				catch (final ExtensionException e) {
					throw new XMLImportException(e);
				}
			}
			return extensionConfig;
		}
	
		/**
		 * @param extensionConfig
		 * @param propertyElements
		 * @throws ExtensionException
		 * @throws XMLImportException
		 */
		protected void importProperties(final LineRelPathReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importX(extensionConfig, propertyElements.get(0));
			importY(extensionConfig, propertyElements.get(1));
		}
	
		private void importX(final LineRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> xElements = this.getElements(element, FloatElement.CLASS_ID);
			if (xElements.size() == 1) {
				extensionConfig.setX(new FloatElementXMLImporter().importFromElement(xElements.get(0)).getValue());
			}
		}
		private void importY(final LineRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> yElements = this.getElements(element, FloatElement.CLASS_ID);
			if (yElements.size() == 1) {
				extensionConfig.setY(new FloatElementXMLImporter().importFromElement(yElements.get(0)).getValue());
			}
		}
	}
}
