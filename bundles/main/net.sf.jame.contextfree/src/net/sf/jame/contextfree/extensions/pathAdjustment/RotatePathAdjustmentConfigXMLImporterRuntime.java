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
public class RotatePathAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<RotatePathAdjustmentConfig> createXMLImporter() {
		return new RotatePathAdjustmentConfigXMLImporter();
	}

	private class RotatePathAdjustmentConfigXMLImporter extends XMLImporter<RotatePathAdjustmentConfig> {
		protected RotatePathAdjustmentConfig createExtensionConfig() {
			return new RotatePathAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "RotatePathAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public RotatePathAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final RotatePathAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final RotatePathAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importAngle(extensionConfig, propertyElements.get(0));
		}
	
		private void importAngle(final RotatePathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> angleElements = this.getElements(element, FloatElement.CLASS_ID);
			if (angleElements.size() == 1) {
				extensionConfig.setAngle(new FloatElementXMLImporter().importFromElement(angleElements.get(0)).getValue());
			}
		}
	}
}
