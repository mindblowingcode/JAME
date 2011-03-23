/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.List;

import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElement;
import net.sf.jame.contextfree.cfdg.pathAdjustment.PathAdjustmentConfigElementXMLImporter;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementXMLImporter;
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
public class MultiPathReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<MultiPathReplacementConfig> createXMLImporter() {
		return new MultiPathReplacementConfigXMLImporter();
	}

	private class MultiPathReplacementConfigXMLImporter extends XMLImporter<MultiPathReplacementConfig> {
		protected MultiPathReplacementConfig createExtensionConfig() {
			return new MultiPathReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "MultiPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public MultiPathReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final MultiPathReplacementConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final MultiPathReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importTimes(extensionConfig, propertyElements.get(0));
			importPathReplacementListElement(extensionConfig, propertyElements.get(1));
			importPathAdjustmentListElement(extensionConfig, propertyElements.get(2));
		}
	
		private void importTimes(final MultiPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> timesElements = this.getElements(element, IntegerElement.CLASS_ID);
			if (timesElements.size() == 1) {
				extensionConfig.setTimes(new IntegerElementXMLImporter().importFromElement(timesElements.get(0)).getValue());
			}
		}
		private void importPathReplacementListElement(final MultiPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final PathReplacementConfigElementXMLImporter pathReplacementImporter = new PathReplacementConfigElementXMLImporter();
			final List<Element> pathReplacementElements = this.getElements(element, PathReplacementConfigElement.CLASS_ID);
			for (int i = 0; i < pathReplacementElements.size(); i++) {
				extensionConfig.appendPathReplacementConfigElement(pathReplacementImporter.importFromElement(pathReplacementElements.get(i)));
			}
		}
		private void importPathAdjustmentListElement(final MultiPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final PathAdjustmentConfigElementXMLImporter pathAdjustmentImporter = new PathAdjustmentConfigElementXMLImporter();
			final List<Element> pathAdjustmentElements = this.getElements(element, PathAdjustmentConfigElement.CLASS_ID);
			for (int i = 0; i < pathAdjustmentElements.size(); i++) {
				extensionConfig.appendPathAdjustmentConfigElement(pathAdjustmentImporter.importFromElement(pathAdjustmentElements.get(i)));
			}
		}
	}
}
