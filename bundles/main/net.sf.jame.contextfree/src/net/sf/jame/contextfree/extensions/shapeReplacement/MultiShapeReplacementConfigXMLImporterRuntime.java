/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import java.util.List;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLImporter;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shapeReplacement.ShapeReplacementConfigElementXMLImporter;
import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.common.IntegerElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class MultiShapeReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<MultiShapeReplacementConfig> createXMLImporter() {
		return new MultiShapeReplacementConfigXMLImporter();
	}

	private class MultiShapeReplacementConfigXMLImporter extends XMLImporter<MultiShapeReplacementConfig> {
		protected MultiShapeReplacementConfig createExtensionConfig() {
			return new MultiShapeReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "MultiShapeReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public MultiShapeReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final MultiShapeReplacementConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 3) {
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
		protected void importProperties(final MultiShapeReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importTimes(extensionConfig, propertyElements.get(0));
			importShapeReplacementListElement(extensionConfig, propertyElements.get(1));
			importShapeAdjustmentListElement(extensionConfig, propertyElements.get(2));
		}
	
		private void importTimes(final MultiShapeReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> timesElements = this.getElements(element, IntegerElement.CLASS_ID);
			if (timesElements.size() == 1) {
				extensionConfig.setTimes(new IntegerElementXMLImporter().importFromElement(timesElements.get(0)).getValue());
			}
		}
		private void importShapeReplacementListElement(final MultiShapeReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final ShapeReplacementConfigElementXMLImporter shapeReplacementImporter = new ShapeReplacementConfigElementXMLImporter();
			final List<Element> shapeReplacementElements = this.getElements(element, ShapeReplacementConfigElement.CLASS_ID);
			for (int i = 0; i < shapeReplacementElements.size(); i++) {
				extensionConfig.appendShapeReplacementConfigElement(shapeReplacementImporter.importFromElement(shapeReplacementElements.get(i)));
			}
		}
		private void importShapeAdjustmentListElement(final MultiShapeReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final ShapeAdjustmentConfigElementXMLImporter shapeAdjustmentImporter = new ShapeAdjustmentConfigElementXMLImporter();
			final List<Element> shapeAdjustmentElements = this.getElements(element, ShapeAdjustmentConfigElement.CLASS_ID);
			for (int i = 0; i < shapeAdjustmentElements.size(); i++) {
				extensionConfig.appendShapeAdjustmentConfigElement(shapeAdjustmentImporter.importFromElement(shapeAdjustmentElements.get(i)));
			}
		}
	}
}
