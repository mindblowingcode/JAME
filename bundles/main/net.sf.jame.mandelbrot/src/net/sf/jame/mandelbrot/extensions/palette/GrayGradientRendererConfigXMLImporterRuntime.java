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
package net.sf.jame.mandelbrot.extensions.palette;

import java.util.List;

import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class GrayGradientRendererConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<GrayGradientRendererConfig> createXMLImporter() {
		return new GrayGradientRendererConfigXMLImporter();
	}

	private class GrayGradientRendererConfigXMLImporter extends AbstractPaletteRendererConfigXMLImporter<GrayGradientRendererConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.palette.AbstractPaletteRendererConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected GrayGradientRendererConfig createExtensionConfig() {
			return new GrayGradientRendererConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.palette.AbstractPaletteRendererConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "GrayGradientRendererConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.palette.AbstractPaletteRendererConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 0;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.palette.AbstractPaletteRendererConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.palette.extension.PaletteRendererExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final GrayGradientRendererConfig config, final List<Element> propertyElements) throws XMLImportException {
		}
	}
}
