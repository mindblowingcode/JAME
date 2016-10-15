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
package net.sf.jame.mandelbrot.extensions.paletteRenderer;

import java.util.List;

import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.mandelbrot.common.RenderedPaletteElement;
import net.sf.jame.mandelbrot.common.RenderedPaletteElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class DefaultRendererConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<DefaultRendererConfig> createXMLImporter() {
		return new DefaultRendererConfigXMLImporter();
	}

	private class DefaultRendererConfigXMLImporter extends AbstractPaletteRendererConfigXMLImporter<DefaultRendererConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected DefaultRendererConfig createExtensionConfig() {
			return new DefaultRendererConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "DefaultRendererConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.paletteRenderer.extension.PaletteRendererExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final DefaultRendererConfig config, final List<Element> propertyElements) throws XMLImportException {
			importRenderedPalette(config, propertyElements.get(0));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importRenderedPalette(final DefaultRendererConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, RenderedPaletteElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getRenderedPaletteElement().setValue(new RenderedPaletteElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}
	}
}
