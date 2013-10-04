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
package net.sf.jame.mandelbrot.common;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLExporter;
import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.common.DoubleElementXMLExporter;
import net.sf.jame.core.config.ValueConfigElementXMLExporter;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.paletteRendererFormula.PaletteRendererFormulaConfigElement;
import net.sf.jame.mandelbrot.paletteRendererFormula.PaletteRendererFormulaConfigElementXMLExporter;
import net.sf.jame.mandelbrot.util.RenderedPalette;
import net.sf.jame.mandelbrot.util.RenderedPaletteParam;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class RenderedPaletteElementXMLExporter extends ValueConfigElementXMLExporter<RenderedPalette, RenderedPaletteElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final RenderedPaletteElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, configElement.getClassId());
		exportProperties(configElement, element, builder);
		return element;
	}

	/**
	 * @param configElement
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	@Override
	protected void exportProperties(final RenderedPaletteElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportPalette(configElement, createProperty(builder, element, "palette"), builder);
	}

	/**
	 * @param palette
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	private void exportPalette(final RenderedPaletteElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final RenderedPalette palette = configElement.getValue();
		for (int i = 0; i < palette.getParamCount(); i++) {
			exportParam(palette.getParam(i), createProperty(builder, element, "paletteParam"), builder);
		}
	}

	/**
	 * @param param
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportParam(final RenderedPaletteParam param, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportFormulas(param, createProperty(builder, element, "formulas"), builder);
		exportColors(param, createProperty(builder, element, "colors"), builder);
		exportSize(param, createProperty(builder, element, "size"), builder);
	}

	/**
	 * @param param
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	private void exportSize(final RenderedPaletteParam param, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final DoubleElementXMLExporter doubleExporter = new DoubleElementXMLExporter();
		final DoubleElement sizeElement = new DoubleElement(0d);
		sizeElement.setValue(new Double(param.getSize()));
		element.appendChild(doubleExporter.exportToElement(sizeElement, builder));
	}

	/**
	 * @param param
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	private void exportColors(final RenderedPaletteParam param, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ColorElementXMLExporter colorExporter = new ColorElementXMLExporter();
		final ColorElement colorElement = new ColorElement(Color32bit.BLACK);
		colorElement.setValue(new Color32bit(param.getColor(0)));
		element.appendChild(colorExporter.exportToElement(colorElement, builder));
		colorElement.setValue(new Color32bit(param.getColor(1)));
		element.appendChild(colorExporter.exportToElement(colorElement, builder));
	}

	/**
	 * @param param
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	private void exportFormulas(final RenderedPaletteParam param, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final PaletteRendererFormulaConfigElementXMLExporter formulaExporter = new PaletteRendererFormulaConfigElementXMLExporter();
		final PaletteRendererFormulaConfigElement formulaElement = new PaletteRendererFormulaConfigElement();
		formulaElement.setReference(param.getFormula(0));
		element.appendChild(formulaExporter.exportToElement(formulaElement, builder));
		formulaElement.setReference(param.getFormula(1));
		element.appendChild(formulaExporter.exportToElement(formulaElement, builder));
		formulaElement.setReference(param.getFormula(2));
		element.appendChild(formulaExporter.exportToElement(formulaElement, builder));
		formulaElement.setReference(param.getFormula(3));
		element.appendChild(formulaExporter.exportToElement(formulaElement, builder));
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLExporter#formatValue(java.io.Serializable)
	 */
	@Override
	protected String formatValue(final RenderedPalette value) {
		return null;
	}
}
