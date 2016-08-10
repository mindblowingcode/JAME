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
package net.sf.jame.mandelbrot.extensions.incolouringFormula;

import java.util.List;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLImporter;
import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ColorConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<ColorConfig> createXMLImporter() {
		return new ColorConfigXMLImporter();
	}

	private class ColorConfigXMLImporter extends AbstractIncolouringFormulaConfigXMLImporter<ColorConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected ColorConfig createExtensionConfig() {
			return new ColorConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "ColorConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.incolouringFormula.extension.IncolouringFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final ColorConfig config, final List<Element> propertyElements) throws XMLImportException {
			importColor(config, propertyElements.get(0));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importColor(final ColorConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ColorElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getColorElement().setValue(new ColorElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}
	}
}
