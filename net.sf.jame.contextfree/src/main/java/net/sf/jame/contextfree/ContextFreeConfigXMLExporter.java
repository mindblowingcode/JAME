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
package net.sf.jame.contextfree;

import net.sf.jame.contextfree.cfdg.CFDGConfigElementXMLExporter;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.twister.common.SpeedElementXMLExporter;
import net.sf.jame.twister.common.ViewElementXMLExporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigXMLExporter extends XMLExporter<ContextFreeConfig> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final ContextFreeConfig config, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, ContextFreeConfig.CLASS_ID, 0, 0);
		exportProperties(config, element, builder);
		return element;
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportCFDG(config, createProperty(builder, element, "cdfg"), builder);
		exportView(config, createProperty(builder, element, "view"), builder);
		exportSpeed(config, createProperty(builder, element, "speed"), builder);
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportCFDG(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new CFDGConfigElementXMLExporter().exportToElement(config.getCFDG(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportView(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ViewElementXMLExporter().exportToElement(config.getViewElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportSpeed(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new SpeedElementXMLExporter().exportToElement(config.getSpeedElement(), builder));
	}
}
