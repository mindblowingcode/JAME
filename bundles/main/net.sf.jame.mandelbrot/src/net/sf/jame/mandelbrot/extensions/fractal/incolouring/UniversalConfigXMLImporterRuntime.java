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
package net.sf.jame.mandelbrot.extensions.fractal.incolouring;

import java.util.List;

import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.mandelbrot.color.ColorRendererConfigElement;
import net.sf.jame.mandelbrot.color.ColorRendererConfigElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class UniversalConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<UniversalConfig> createXMLImporter() {
		return new UniversalConfigXMLImporter();
	}

	private class UniversalConfigXMLImporter extends AbstractIncolouringPaletteConfigXMLImporter<UniversalConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected UniversalConfig createExtensionConfig() {
			return new UniversalConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "UniversalConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringPaletteConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return super.getPropertiesSize() + 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringPaletteConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractPaletteExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final UniversalConfig config, final List<Element> propertyElements) throws XMLImportException {
			super.importProperties(config, propertyElements);
			importColorRenderer(config, propertyElements.get(1));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importColorRenderer(final UniversalConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ColorRendererConfigElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getColorRendererElement().setReference(new ColorRendererConfigElementXMLImporter().importFromElement(elements.get(0)).getReference());
			}
		}
	}
}
