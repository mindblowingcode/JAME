/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.util.List;

import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.pathReplacement.PathReplacementConfigElementXMLImporter;
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
public class PathFigureConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<PathFigureConfig> createXMLImporter() {
		return new PathFigureConfigXMLImporter();
	}

	private class PathFigureConfigXMLImporter extends XMLImporter<PathFigureConfig> {
		protected PathFigureConfig createExtensionConfig() {
			return new PathFigureConfig();
		}

		protected String getConfigElementClassId() {
			return "PathFigureConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public PathFigureConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final PathFigureConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final PathFigureConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importName(extensionConfig, propertyElements.get(0));
			importPathReplacementListElement(extensionConfig, propertyElements.get(1));
		}
	
		private void importName(final PathFigureConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> nameElements = this.getElements(element, StringElement.CLASS_ID);
			if (nameElements.size() == 1) {
				extensionConfig.setName(new StringElementXMLImporter().importFromElement(nameElements.get(0)).getValue());
			}
		}
		private void importPathReplacementListElement(final PathFigureConfig extensionConfig, final Element element) throws XMLImportException {
			final PathReplacementConfigElementXMLImporter pathReplacementImporter = new PathReplacementConfigElementXMLImporter();
			final List<Element> pathReplacementElements = this.getElements(element, PathReplacementConfigElement.CLASS_ID);
			for (int i = 0; i < pathReplacementElements.size(); i++) {
				extensionConfig.appendPathReplacementConfigElement(pathReplacementImporter.importFromElement(pathReplacementElements.get(i)));
			}
		}
	}
}
