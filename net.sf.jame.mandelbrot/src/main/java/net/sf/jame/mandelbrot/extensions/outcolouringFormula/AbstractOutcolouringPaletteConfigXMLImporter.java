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
package net.sf.jame.mandelbrot.extensions.outcolouringFormula;

import java.util.List;

import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.mandelbrot.paletteRenderer.PaletteRendererConfigElement;
import net.sf.jame.mandelbrot.paletteRenderer.PaletteRendererConfigElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractOutcolouringPaletteConfigXMLImporter<T extends AbstractOutcolouringPaletteConfig> extends AbstractOutcolouringFormulaConfigXMLImporter<T> {
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
	protected void importProperties(final T config, final List<Element> propertyElements) throws XMLImportException {
		this.importPalette(config, propertyElements.get(0));
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importPalette(final T config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, PaletteRendererConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			config.getPaletteRendererElement().setReference(new PaletteRendererConfigElementXMLImporter().importFromElement(elements.get(0)).getReference());
		}
	}
}
