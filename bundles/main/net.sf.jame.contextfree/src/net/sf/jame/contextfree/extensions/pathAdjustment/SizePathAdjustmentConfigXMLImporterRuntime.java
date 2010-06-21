/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathAdjustment;

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
public class SizePathAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<SizePathAdjustmentConfig> createXMLImporter() {
		return new SizePathAdjustmentConfigXMLImporter();
	}

	private class SizePathAdjustmentConfigXMLImporter extends XMLImporter<SizePathAdjustmentConfig> {
		protected SizePathAdjustmentConfig createExtensionConfig() {
			return new SizePathAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "SizePathAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public SizePathAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final SizePathAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final SizePathAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importScale(extensionConfig, propertyElements.get(0));
		}
	
		private void importScale(final SizePathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> scaleElements = this.getElements(element, FloatElement.CLASS_ID);
			if (scaleElements.size() == 1) {
				extensionConfig.setScale(new FloatElementXMLImporter().importFromElement(scaleElements.get(0)).getValue());
			}
		}
	}
}
