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
public class Size3ShapeAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<Size3ShapeAdjustmentConfig> createXMLImporter() {
		return new Size3ShapeAdjustmentConfigXMLImporter();
	}

	private class Size3ShapeAdjustmentConfigXMLImporter extends XMLImporter<Size3ShapeAdjustmentConfig> {
		protected Size3ShapeAdjustmentConfig createExtensionConfig() {
			return new Size3ShapeAdjustmentConfig();
		}

		protected String getConfigElementClassId() {
			return "Size3ShapeAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public Size3ShapeAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, this.getConfigElementClassId());
			final Size3ShapeAdjustmentConfig extensionConfig = this.createExtensionConfig();
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
		protected void importProperties(final Size3ShapeAdjustmentConfig extensionConfig, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
			importScaleX(extensionConfig, propertyElements.get(0));
			importScaleY(extensionConfig, propertyElements.get(1));
			importScaleZ(extensionConfig, propertyElements.get(2));
		}
	
		private void importScaleX(final Size3ShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> scaleXElements = this.getElements(element, FloatElement.CLASS_ID);
			if (scaleXElements.size() == 1) {
				extensionConfig.setScaleX(new FloatElementXMLImporter().importFromElement(scaleXElements.get(0)).getValue());
			}
		}
		private void importScaleY(final Size3ShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> scaleYElements = this.getElements(element, FloatElement.CLASS_ID);
			if (scaleYElements.size() == 1) {
				extensionConfig.setScaleY(new FloatElementXMLImporter().importFromElement(scaleYElements.get(0)).getValue());
			}
		}
		private void importScaleZ(final Size3ShapeAdjustmentConfig extensionConfig, final Element element) throws XMLImportException {
			final List<Element> scaleZElements = this.getElements(element, FloatElement.CLASS_ID);
			if (scaleZElements.size() == 1) {
				extensionConfig.setScaleZ(new FloatElementXMLImporter().importFromElement(scaleZElements.get(0)).getValue());
			}
		}
	}
}
