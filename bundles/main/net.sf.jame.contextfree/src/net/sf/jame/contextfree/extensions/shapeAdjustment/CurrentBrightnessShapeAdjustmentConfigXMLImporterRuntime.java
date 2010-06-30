/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeAdjustment;

import java.util.List;
import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.BooleanElementXMLImporter;
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
public class CurrentBrightnessShapeAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<CurrentBrightnessShapeAdjustmentConfig> createXMLImporter() {
		return new CurrentBrightnessShapeAdjustmentConfigXMLImporter();
	}

	private class CurrentBrightnessShapeAdjustmentConfigXMLImporter extends XMLImporter<CurrentBrightnessShapeAdjustmentConfig> {
		protected CurrentBrightnessShapeAdjustmentConfig createExtensionConfig() {
			return new CurrentBrightnessShapeAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "CurrentBrightnessShapeAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public CurrentBrightnessShapeAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final CurrentBrightnessShapeAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final CurrentBrightnessShapeAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importValue(extensionConfig, propertyElements.get(0));
			importTarget(extensionConfig, propertyElements.get(1));
		}
	
		private void importValue(final CurrentBrightnessShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> valueElements = this.getElements(element, FloatElement.CLASS_ID);
			if (valueElements.size() == 1) {
				extensionConfig.setValue(new FloatElementXMLImporter().importFromElement(valueElements.get(0)).getValue());
			}
		}
		private void importTarget(final CurrentBrightnessShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> targetElements = this.getElements(element, BooleanElement.CLASS_ID);
			if (targetElements.size() == 1) {
				extensionConfig.setTarget(new BooleanElementXMLImporter().importFromElement(targetElements.get(0)).getValue());
			}
		}
	}
}
