/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.List;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class CircleFigureConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<CircleFigureConfig> createXMLImporter() {
		return new CircleFigureConfigXMLImporter();
	}

	private class CircleFigureConfigXMLImporter extends XMLImporter<CircleFigureConfig> {
		protected CircleFigureConfig createExtensionConfig() {
			return new CircleFigureConfig();
		}

		protected String getConfigElementClassId() {
			return "CircleFigureConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public CircleFigureConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final CircleFigureConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 0) {
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
		protected void importProperties(final CircleFigureConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		}
	
	}
}
