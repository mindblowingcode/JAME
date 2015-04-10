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
package net.sf.jame.mandelbrot.extensions.outcolouringFormula;

import java.util.List;

import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.mandelbrot.colorRenderer.ColorRendererConfigElement;
import net.sf.jame.mandelbrot.colorRenderer.ColorRendererConfigElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class UniversalTrueColorConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<UniversalTrueColorConfig> createXMLImporter() {
		return new UniversalTrueColorConfigXMLImporter();
	}

	private class UniversalTrueColorConfigXMLImporter extends AbstractOutcolouringFormulaConfigXMLImporter<UniversalTrueColorConfig> {
		/**
		 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringFormulaConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected UniversalTrueColorConfig createExtensionConfig() {
			return new UniversalTrueColorConfig();
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringFormulaConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "UniversalTrueColorConfig";
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringFormulaConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 1;
		}

		/**
		 * @see net.sf.jame.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringFormulaConfigXMLImporter#importProperties(net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final UniversalTrueColorConfig config, final List<Element> propertyElements) throws XMLImportException {
			importColorRenderers(config, propertyElements.get(0));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importColorRenderers(final UniversalTrueColorConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ColorRendererConfigElement.CLASS_ID);
			if (elements.size() == 4) {
				config.getColorRendererElement(0).setReference(new ColorRendererConfigElementXMLImporter().importFromElement(elements.get(0)).getReference());
				config.getColorRendererElement(1).setReference(new ColorRendererConfigElementXMLImporter().importFromElement(elements.get(1)).getReference());
				config.getColorRendererElement(2).setReference(new ColorRendererConfigElementXMLImporter().importFromElement(elements.get(2)).getReference());
				config.getColorRendererElement(3).setReference(new ColorRendererConfigElementXMLImporter().importFromElement(elements.get(3)).getReference());
			}
		}
	}
}
