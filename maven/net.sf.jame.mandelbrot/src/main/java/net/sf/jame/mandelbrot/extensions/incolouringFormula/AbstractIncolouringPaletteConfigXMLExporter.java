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
package net.sf.jame.mandelbrot.extensions.incolouringFormula;

import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.paletteRenderer.PaletteRendererConfigElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractIncolouringPaletteConfigXMLExporter<T extends AbstractIncolouringPaletteConfig> extends AbstractIncolouringFormulaConfigXMLExporter<T> {
	/**
	 * @see net.sf.jame.mandelbrot.extensions.incolouringFormula.AbstractIncolouringFormulaConfigXMLExporter#exportProperties(net.sf.jame.mandelbrot.incolouringFormula.extension.IncolouringFormulaExtensionConfig, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportProperties(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		this.exportPalette(config, createProperty(builder, element, "paletteRenderer"), builder);
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportPalette(final T config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new PaletteRendererConfigElementXMLExporter().exportToElement(config.getPaletteRendererElement(), builder));
	}
}
