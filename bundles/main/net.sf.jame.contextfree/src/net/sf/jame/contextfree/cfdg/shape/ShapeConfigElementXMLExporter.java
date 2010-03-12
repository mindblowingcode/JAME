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
package net.sf.jame.contextfree.cfdg.shape;

import net.sf.jame.contextfree.cfdg.shape.replacement.LoopShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.LoopShapeReplacementConfigElementXMLExporter;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElement;
import net.sf.jame.contextfree.cfdg.shape.replacement.ShapeReplacementConfigElementXMLExporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

public class ShapeConfigElementXMLExporter extends XMLExporter<ShapeConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final ShapeConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, ShapeConfigElement.CLASS_ID);
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
	protected void exportProperties(final ShapeConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportReplacementListElement(configElement, createProperty(builder, element, "shapeReplacementList"), builder);
	}

	private void exportReplacementListElement(final ShapeConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ShapeReplacementConfigElementXMLExporter shapeReplacementExporter = new ShapeReplacementConfigElementXMLExporter();
		final LoopShapeReplacementConfigElementXMLExporter loopShapeReplacementExporter = new LoopShapeReplacementConfigElementXMLExporter();
		for (int i = 0; i < configElement.getReplacementConfigElementCount(); i++) {
			if (configElement.getReplacementConfigElement(i).getClassId().equals(ShapeReplacementConfigElement.CLASS_ID)) {
				element.appendChild(shapeReplacementExporter.exportToElement((ShapeReplacementConfigElement) configElement.getReplacementConfigElement(i), builder));
			} else if (configElement.getReplacementConfigElement(i).equals(LoopShapeReplacementConfigElement.CLASS_ID)) {
				element.appendChild(loopShapeReplacementExporter.exportToElement((LoopShapeReplacementConfigElement) configElement.getReplacementConfigElement(i), builder));
			}
		}
	}
}
