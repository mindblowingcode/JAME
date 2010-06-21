/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

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
public class FlipShapeAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<FlipShapeAdjustmentConfig> createXMLImporter() {
		return new FlipShapeAdjustmentConfigXMLImporter();
	}

	private class FlipShapeAdjustmentConfigXMLImporter extends XMLImporter<FlipShapeAdjustmentConfig> {
		protected FlipShapeAdjustmentConfig createExtensionConfig() {
			return new FlipShapeAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "FlipShapeAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public FlipShapeAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final FlipShapeAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final FlipShapeAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importFlipX(extensionConfig, propertyElements.get(0));
			importFlipY(extensionConfig, propertyElements.get(1));
		}
	
		private void importFlipX(final FlipShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> flipXElements = this.getElements(element, FloatElement.CLASS_ID);
			if (flipXElements.size() == 1) {
				extensionConfig.setFlipX(new FloatElementXMLImporter().importFromElement(flipXElements.get(0)).getValue());
			}
		}
		private void importFlipY(final FlipShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> flipYElements = this.getElements(element, FloatElement.CLASS_ID);
			if (flipYElements.size() == 1) {
				extensionConfig.setFlipY(new FloatElementXMLImporter().importFromElement(flipYElements.get(0)).getValue());
			}
		}
	}
}
