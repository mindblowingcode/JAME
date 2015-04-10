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
package net.sf.jame.mandelbrot.extensions.transformingFormula;

import java.util.List;

import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class HConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<HConfig> createXMLImporter() {
		return new HConfigXMLImporter();
	}

	private class HConfigXMLImporter extends AbstractTransformingFormulaConfigXMLImporter<HConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.transformingFormula.AbstractTransformingFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected HConfig createExtensionConfig() {
			return new HConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.transformingFormula.AbstractTransformingFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "HConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.transformingFormula.AbstractTransformingFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 0;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.transformingFormula.AbstractTransformingFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.transformingFormula.extension.TransformingFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final HConfig config, final List<Element> propertyElements) throws XMLImportException {
		}
	}
}
