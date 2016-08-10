/*
 * JAME 6.2.1
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2016 Andrea Medeghini
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.contextfree.extensions.pathReplacement;

import java.util.List;

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.BooleanElementXMLImporter;
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
public class ArcRelPathReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<ArcRelPathReplacementConfig> createXMLImporter() {
		return new ArcRelPathReplacementConfigXMLImporter();
	}

	private class ArcRelPathReplacementConfigXMLImporter extends XMLImporter<ArcRelPathReplacementConfig> {
		protected ArcRelPathReplacementConfig createExtensionConfig() {
			return new ArcRelPathReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "ArcRelPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public ArcRelPathReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final ArcRelPathReplacementConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 7) {
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
		protected void importProperties(final ArcRelPathReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importX(extensionConfig, propertyElements.get(0));
			importY(extensionConfig, propertyElements.get(1));
			importRx(extensionConfig, propertyElements.get(2));
			importRy(extensionConfig, propertyElements.get(3));
			importR(extensionConfig, propertyElements.get(4));
			importSweep(extensionConfig, propertyElements.get(5));
			importLarge(extensionConfig, propertyElements.get(6));
		}
	
		private void importX(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> xElements = this.getElements(element, FloatElement.CLASS_ID);
			if (xElements.size() == 1) {
				extensionConfig.setX(new FloatElementXMLImporter().importFromElement(xElements.get(0)).getValue());
			}
		}
		private void importY(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> yElements = this.getElements(element, FloatElement.CLASS_ID);
			if (yElements.size() == 1) {
				extensionConfig.setY(new FloatElementXMLImporter().importFromElement(yElements.get(0)).getValue());
			}
		}
		private void importRx(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> rxElements = this.getElements(element, FloatElement.CLASS_ID);
			if (rxElements.size() == 1) {
				extensionConfig.setRx(new FloatElementXMLImporter().importFromElement(rxElements.get(0)).getValue());
			}
		}
		private void importRy(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> ryElements = this.getElements(element, FloatElement.CLASS_ID);
			if (ryElements.size() == 1) {
				extensionConfig.setRy(new FloatElementXMLImporter().importFromElement(ryElements.get(0)).getValue());
			}
		}
		private void importR(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> rElements = this.getElements(element, FloatElement.CLASS_ID);
			if (rElements.size() == 1) {
				extensionConfig.setR(new FloatElementXMLImporter().importFromElement(rElements.get(0)).getValue());
			}
		}
		private void importSweep(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> sweepElements = this.getElements(element, BooleanElement.CLASS_ID);
			if (sweepElements.size() == 1) {
				extensionConfig.setSweep(new BooleanElementXMLImporter().importFromElement(sweepElements.get(0)).getValue());
			}
		}
		private void importLarge(final ArcRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> largeElements = this.getElements(element, BooleanElement.CLASS_ID);
			if (largeElements.size() == 1) {
				extensionConfig.setLarge(new BooleanElementXMLImporter().importFromElement(largeElements.get(0)).getValue());
			}
		}
	}
}
