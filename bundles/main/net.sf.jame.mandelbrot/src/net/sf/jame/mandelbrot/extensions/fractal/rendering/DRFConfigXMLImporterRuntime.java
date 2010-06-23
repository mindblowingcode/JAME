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
package net.sf.jame.mandelbrot.extensions.fractal.rendering;

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
public class DRFConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<DRFConfig> createXMLImporter() {
		return new DRMConfigXMLImporter();
	}

	private class DRMConfigXMLImporter extends AbstractRenderingFormulaConfigXMLImporter<DRFConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.rendering.AbstractRenderingFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return super.getPropertiesSize() + 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.rendering.AbstractRenderingFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.fractal.rendering.extension.RenderingFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final DRFConfig config, final List<Element> propertyElements) throws XMLImportException {
			super.importProperties(config, propertyElements);
			importExponent(config, propertyElements.get(super.getPropertiesSize()));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importExponent(final DRFConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.setExponent(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.rendering.AbstractRenderingFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected DRFConfig createExtensionConfig() {
			return new DRFConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.rendering.AbstractRenderingFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "DRMConfig";
		}
	}
}
