/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

import java.util.List;

import net.sf.jame.core.common.FloatElement;
import net.sf.jame.core.common.FloatElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class YPathAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<YPathAdjustmentConfig> createXMLImporter() {
		return new YPathAdjustmentConfigXMLImporter();
	}

	private class YPathAdjustmentConfigXMLImporter extends XMLImporter<YPathAdjustmentConfig> {
		protected YPathAdjustmentConfig createExtensionConfig() {
			return new YPathAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "YPathAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public YPathAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final YPathAdjustmentConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 1) {
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
		protected void importProperties(final YPathAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importValue(extensionConfig, propertyElements.get(0));
		}
	
		private void importValue(final YPathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> valueElements = this.getElements(element, FloatElement.CLASS_ID);
			if (valueElements.size() == 1) {
				extensionConfig.setValue(new FloatElementXMLImporter().importFromElement(valueElements.get(0)).getValue());
			}
		}
	}
}
