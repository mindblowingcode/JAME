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
public class Size2PathAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<Size2PathAdjustmentConfig> createXMLImporter() {
		return new Size2PathAdjustmentConfigXMLImporter();
	}

	private class Size2PathAdjustmentConfigXMLImporter extends XMLImporter<Size2PathAdjustmentConfig> {
		protected Size2PathAdjustmentConfig createExtensionConfig() {
			return new Size2PathAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "Size2PathAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public Size2PathAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final Size2PathAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final Size2PathAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importScaleX(extensionConfig, propertyElements.get(0));
			importScaleY(extensionConfig, propertyElements.get(1));
		}
	
		private void importScaleX(final Size2PathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> scaleXElements = this.getElements(element, FloatElement.CLASS_ID);
			if (scaleXElements.size() == 1) {
				extensionConfig.setScaleX(new FloatElementXMLImporter().importFromElement(scaleXElements.get(0)).getValue());
			}
		}
		private void importScaleY(final Size2PathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> scaleYElements = this.getElements(element, FloatElement.CLASS_ID);
			if (scaleYElements.size() == 1) {
				extensionConfig.setScaleY(new FloatElementXMLImporter().importFromElement(scaleYElements.get(0)).getValue());
			}
		}
	}
}
