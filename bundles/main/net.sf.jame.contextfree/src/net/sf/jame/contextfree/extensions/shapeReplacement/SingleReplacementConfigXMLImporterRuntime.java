/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.shapeReplacement;

import java.util.List;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.shapeAdjustment.ShapeAdjustmentConfigElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class SingleReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<SingleReplacementConfig> createXMLImporter() {
		return new SingleReplacementConfigXMLImporter();
	}

	private class SingleReplacementConfigXMLImporter extends XMLImporter<SingleReplacementConfig> {
		protected SingleReplacementConfig createExtensionConfig() {
			return new SingleReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "SingleReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public SingleReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final SingleReplacementConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final SingleReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importShape(extensionConfig, propertyElements.get(0));
			importShapeAdjustmentListElement(extensionConfig, propertyElements.get(1));
		}
	
		private void importShape(final SingleReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> shapeElements = this.getElements(element, StringElement.CLASS_ID);
			if (shapeElements.size() == 1) {
				extensionConfig.setShape(new StringElementXMLImporter().importFromElement(shapeElements.get(0)).getValue());
			}
		}
		private void importShapeAdjustmentListElement(final SingleReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final ShapeAdjustmentConfigElementXMLImporter shapeAdjustmentImporter = new ShapeAdjustmentConfigElementXMLImporter();
			final List<Element> shapeAdjustmentElements = this.getElements(element, ShapeAdjustmentConfigElement.CLASS_ID);
			for (int i = 0; i < shapeAdjustmentElements.size(); i++) {
				extensionConfig.appendShapeAdjustmentConfigElement(shapeAdjustmentImporter.importFromElement(shapeAdjustmentElements.get(i)));
			}
		}
	}
}
