/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

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
public class FlipShapeAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
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
		protected void importProperties(final FlipShapeAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importAngle(extensionConfig, propertyElements.get(0));
		}
	
		private void importAngle(final FlipShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> angleElements = this.getElements(element, FloatElement.CLASS_ID);
			if (angleElements.size() == 1) {
				extensionConfig.setAngle(new FloatElementXMLImporter().importFromElement(angleElements.get(0)).getValue());
			}
		}
	}
}