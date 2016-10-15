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
public class QuadRelPathReplacementConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<QuadRelPathReplacementConfig> createXMLImporter() {
		return new QuadRelPathReplacementConfigXMLImporter();
	}

	private class QuadRelPathReplacementConfigXMLImporter extends XMLImporter<QuadRelPathReplacementConfig> {
		protected QuadRelPathReplacementConfig createExtensionConfig() {
			return new QuadRelPathReplacementConfig();
		}

		protected String getConfigElementClassId() {
			return "QuadRelPathReplacementConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public QuadRelPathReplacementConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final QuadRelPathReplacementConfig extensionConfig = this.createExtensionConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 4) {
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
		protected void importProperties(final QuadRelPathReplacementConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importX(extensionConfig, propertyElements.get(0));
			importY(extensionConfig, propertyElements.get(1));
			importX1(extensionConfig, propertyElements.get(2));
			importY1(extensionConfig, propertyElements.get(3));
		}
	
		private void importX(final QuadRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> xElements = this.getElements(element, FloatElement.CLASS_ID);
			if (xElements.size() == 1) {
				extensionConfig.setX(new FloatElementXMLImporter().importFromElement(xElements.get(0)).getValue());
			}
		}
		private void importY(final QuadRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> yElements = this.getElements(element, FloatElement.CLASS_ID);
			if (yElements.size() == 1) {
				extensionConfig.setY(new FloatElementXMLImporter().importFromElement(yElements.get(0)).getValue());
			}
		}
		private void importX1(final QuadRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> x1Elements = this.getElements(element, FloatElement.CLASS_ID);
			if (x1Elements.size() == 1) {
				extensionConfig.setX1(new FloatElementXMLImporter().importFromElement(x1Elements.get(0)).getValue());
			}
		}
		private void importY1(final QuadRelPathReplacementConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> y1Elements = this.getElements(element, FloatElement.CLASS_ID);
			if (y1Elements.size() == 1) {
				extensionConfig.setY1(new FloatElementXMLImporter().importFromElement(y1Elements.get(0)).getValue());
			}
		}
	}
}
