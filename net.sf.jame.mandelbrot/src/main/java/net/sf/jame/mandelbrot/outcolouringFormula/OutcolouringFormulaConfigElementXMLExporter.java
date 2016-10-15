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
package net.sf.jame.mandelbrot.outcolouringFormula;

import net.sf.jame.core.common.BooleanElementXMLExporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter;
import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.common.IterationsElementXMLExporter;
import net.sf.jame.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionConfig;
import net.sf.jame.twister.common.PercentageElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class OutcolouringFormulaConfigElementXMLExporter extends XMLExporter<OutcolouringFormulaConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final OutcolouringFormulaConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, OutcolouringFormulaConfigElement.CLASS_ID);
		try {
			exportProperties(configElement, element, builder);
		}
		catch (final ExtensionException e) {
			throw new XMLExportException(e);
		}
		return element;
	}

	/**
	 * @see net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLExporter#exportProperties(net.sf.jame.twister.util.ConfigurableExtensionConfigElement, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder, java.lang.String)
	 */
	protected void exportProperties(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportExtension(configElement, createProperty(builder, element, "extension"), builder);
		exportLocked(configElement, createProperty(builder, element, "locked"), builder);
		exportEnabled(configElement, createProperty(builder, element, "enabled"), builder);
		exportOpacity(configElement, createProperty(builder, element, "opacity"), builder);
		exportIterations(configElement, createProperty(builder, element, "iterations"), builder);
		exportAutoIterations(configElement, createProperty(builder, element, "autoIterations"), builder);
		exportLabel(configElement, createProperty(builder, element, "label"), builder);
	}

	private void exportExtension(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ConfigurableExtensionReferenceElementXMLExporter<OutcolouringFormulaExtensionConfig>().exportToElement(configElement.getExtensionElement(), builder));
	}

	private void exportOpacity(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new PercentageElementXMLExporter().exportToElement(configElement.getOpacityElement(), builder));
	}

	private void exportIterations(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new IterationsElementXMLExporter().exportToElement(configElement.getIterationsElement(), builder));
	}

	private void exportEnabled(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new BooleanElementXMLExporter().exportToElement(configElement.getEnabledElement(), builder));
	}

	private void exportLocked(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new BooleanElementXMLExporter().exportToElement(configElement.getLockedElement(), builder));
	}

	private void exportAutoIterations(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new BooleanElementXMLExporter().exportToElement(configElement.getAutoIterationsElement(), builder));
	}

	private void exportLabel(final OutcolouringFormulaConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getLabelElement(), builder));
	}
}
