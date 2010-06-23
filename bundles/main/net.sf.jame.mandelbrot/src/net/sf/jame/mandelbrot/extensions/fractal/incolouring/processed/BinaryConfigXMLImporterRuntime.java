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
package net.sf.jame.mandelbrot.extensions.fractal.incolouring.processed;

import java.util.List;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class BinaryConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<BinaryConfig> createXMLImporter() {
		return new BinaryConfigXMLImporter();
	}

	private class BinaryConfigXMLImporter extends AbstractIncolouringFormulaConfigXMLImporter<BinaryConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected BinaryConfig createExtensionConfig() {
			return new BinaryConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "BinaryConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.fractal.incolouring.AbstractIncolouringFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.fractal.incolouring.extension.IncolouringFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final BinaryConfig config, final List<Element> propertyElements) throws XMLImportException {
			importColors(config, propertyElements.get(0));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importColors(final BinaryConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ColorElement.CLASS_ID);
			if (elements.size() == 2) {
				config.getColorElement(0).setValue(new ColorElementXMLImporter().importFromElement(elements.get(0)).getValue());
				config.getColorElement(1).setValue(new ColorElementXMLImporter().importFromElement(elements.get(1)).getValue());
			}
		}
	}
}
