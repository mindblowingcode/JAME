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
package net.sf.jame.mandelbrot.extensions.transformingFormula;

import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.mandelbrot.transformingFormula.extension.TransformingFormulaExtensionConfig;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractTransformingFormulaConfigXMLExporter<T extends TransformingFormulaExtensionConfig> extends XMLExporter<T> {
	/**
	 * @return
	 */
	protected abstract String getConfigElementClassId();

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected abstract void exportProperties(T config, Element element, XMLNodeBuilder builder) throws XMLExportException;

	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final T config, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, this.getConfigElementClassId());
		this.exportProperties(config, element, builder);
		return element;
	}
}
