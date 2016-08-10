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
package net.sf.jame.mandelbrot.extensions.renderingFormula;

import java.util.List;

import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.mandelbrot.common.ExponentElement;
import net.sf.jame.mandelbrot.common.ExponentElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ZNConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<ZNConfig> createXMLImporter() {
		return new ZNConfigXMLImporter();
	}

	private class ZNConfigXMLImporter extends AbstractRenderingFormulaConfigXMLImporter<ZNConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.renderingFormula.AbstractRenderingFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return super.getPropertiesSize() + 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.renderingFormula.AbstractRenderingFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final ZNConfig config, final List<Element> propertyElements) throws XMLImportException {
			super.importProperties(config, propertyElements);
			importExponent(config, propertyElements.get(super.getPropertiesSize()));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importExponent(final ZNConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ExponentElement.CLASS_ID);
			if (elements.size() == 1) {
				config.setExponent(new ExponentElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.renderingFormula.AbstractRenderingFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected ZNConfig createExtensionConfig() {
			return new ZNConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.renderingFormula.AbstractRenderingFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "ZNConfig";
		}
	}
}
