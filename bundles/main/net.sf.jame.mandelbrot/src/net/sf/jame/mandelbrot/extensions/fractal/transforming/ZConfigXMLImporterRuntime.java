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
package net.sf.jame.mandelbrot.extensions.fractal.transforming;

import java.util.List;

import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ZConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<ZConfig> createXMLImporter() {
		return new ZConfigXMLImporter();
	}

	private class ZConfigXMLImporter extends AbstractTransformingFormulaConfigXMLImporter<ZConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.transforming.AbstractTransformingFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected ZConfig createExtensionConfig() {
			return new ZConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.transforming.AbstractTransformingFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "ZConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.transforming.AbstractTransformingFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 0;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.transforming.AbstractTransformingFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.fractal.transforming.extension.TransformingFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final ZConfig config, final List<Element> propertyElements) throws XMLImportException {
		}
	}
}
