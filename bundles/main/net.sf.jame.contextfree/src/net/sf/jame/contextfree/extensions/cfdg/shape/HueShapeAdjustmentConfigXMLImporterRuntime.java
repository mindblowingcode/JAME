/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
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
package net.sf.jame.contextfree.extensions.cfdg.shape;

import java.util.List;

import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.common.DoubleElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class HueShapeAdjustmentConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<HueShapeAdjustmentConfig> createXMLImporter() {
		return new HueShapeAdjustmentConfigXMLImporter();
	}

	private class HueShapeAdjustmentConfigXMLImporter extends AbstractShapeAdjustmentConfigXMLImporter<HueShapeAdjustmentConfig> {
		/**
		 * @see net.sf.jame.twister.extensions.frame.layer.filter.AbstractLayerFilterConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected HueShapeAdjustmentConfig createExtensionConfig() {
			return new HueShapeAdjustmentConfig();
		}

		/**
		 * @see net.sf.jame.twister.extensions.frame.layer.filter.AbstractLayerFilterConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "HueShapeAdjustmentConfig";
		}
		
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public HueShapeAdjustmentConfig importFromElement(final Element element) throws XMLImportException {
			final HueShapeAdjustmentConfig config = super.importFromElement(element);
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 1) {
				importProperties(config, propertyElements);
			}
			return config;
		}

		/**
		 * @param config
		 * @param propertyElements
		 * @throws XMLImportException
		 */
		protected void importProperties(final HueShapeAdjustmentConfig config, final List<Element> propertyElements) throws XMLImportException {
			importValue(config, propertyElements.get(0));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importValue(final HueShapeAdjustmentConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}
	}
}
