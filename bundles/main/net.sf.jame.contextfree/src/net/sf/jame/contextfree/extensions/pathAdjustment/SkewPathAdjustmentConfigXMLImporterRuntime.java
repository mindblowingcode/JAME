/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.contextfree.extensions.pathAdjustment;

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
public class SkewPathAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<SkewPathAdjustmentConfig> createXMLImporter() {
		return new SkewPathAdjustmentConfigXMLImporter();
	}

	private class SkewPathAdjustmentConfigXMLImporter extends XMLImporter<SkewPathAdjustmentConfig> {
		protected SkewPathAdjustmentConfig createExtensionConfig() {
			return new SkewPathAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "SkewPathAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public SkewPathAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final SkewPathAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final SkewPathAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importShearX(extensionConfig, propertyElements.get(0));
			importShearY(extensionConfig, propertyElements.get(1));
		}
	
		private void importShearX(final SkewPathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> shearXElements = this.getElements(element, FloatElement.CLASS_ID);
			if (shearXElements.size() == 1) {
				extensionConfig.setShearX(new FloatElementXMLImporter().importFromElement(shearXElements.get(0)).getValue());
			}
		}
		private void importShearY(final SkewPathAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> shearYElements = this.getElements(element, FloatElement.CLASS_ID);
			if (shearYElements.size() == 1) {
				extensionConfig.setShearY(new FloatElementXMLImporter().importFromElement(shearYElements.get(0)).getValue());
			}
		}
	}
}
