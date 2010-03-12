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
package net.sf.jame.contextfree.cfdg;

import net.sf.jame.contextfree.cfdg.path.PathConfigElement;
import net.sf.jame.contextfree.cfdg.path.PathConfigElementNode;
import net.sf.jame.contextfree.cfdg.path.PathConfigElementXMLExporter;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElement;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElementNode;
import net.sf.jame.contextfree.cfdg.rule.RuleConfigElementXMLExporter;
import net.sf.jame.core.common.ColorElementXMLExporter;
import net.sf.jame.core.common.FloatElementXMLExporter;
import net.sf.jame.core.common.StringElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class CFDGConfigElementXMLExporter extends XMLExporter<CFDGConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final CFDGConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, CFDGConfigElement.CLASS_ID);
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
	protected void exportProperties(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportStartshape(configElement, createProperty(builder, element, "startshape"), builder);
		exportX(configElement, createProperty(builder, element, "x"), builder);
		exportY(configElement, createProperty(builder, element, "y"), builder);
		exportWidth(configElement, createProperty(builder, element, "width"), builder);
		exportHeight(configElement, createProperty(builder, element, "height"), builder);
		exportTileWidth(configElement, createProperty(builder, element, "tileWidth"), builder);
		exportTileHeight(configElement, createProperty(builder, element, "tileHeight"), builder);
		exportBackground(configElement, createProperty(builder, element, "background"), builder);
		exportFigureListElement(configElement, createProperty(builder, element, "figureList"), builder);
	}

	private void exportStartshape(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getStartshapeElement(), builder));
	}

	private void exportX(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getXElement(), builder));
	}

	private void exportY(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getYElement(), builder));
	}

	private void exportWidth(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getWidthElement(), builder));
	}

	private void exportHeight(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getHeightElement(), builder));
	}

	private void exportTileWidth(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getTileWidthElement(), builder));
	}

	private void exportTileHeight(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new FloatElementXMLExporter().exportToElement(configElement.getTileHeightElement(), builder));
	}

	private void exportBackground(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ColorElementXMLExporter().exportToElement(configElement.getBackgroundElement(), builder));
	}

	private void exportFigureListElement(final CFDGConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final RuleConfigElementXMLExporter ruleExporter = new RuleConfigElementXMLExporter();
		final PathConfigElementXMLExporter pathExporter = new PathConfigElementXMLExporter();
		for (int i = 0; i < configElement.getFigureConfigElementCount(); i++) {
			if (configElement.getFigureConfigElement(i).getClassId().equals(RuleConfigElement.CLASS_ID)) {
				element.appendChild(ruleExporter.exportToElement((RuleConfigElement) configElement.getFigureConfigElement(i), builder));
			} else if (configElement.getFigureConfigElement(i).getClassId().equals(PathConfigElement.CLASS_ID)) {
				element.appendChild(pathExporter.exportToElement((PathConfigElement) configElement.getFigureConfigElement(i), builder));
			}
		}
	}
}
