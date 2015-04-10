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
package net.sf.jame.core.extensions.action;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLExporter;
import net.sf.jame.core.tree.NodeActionValue;
import net.sf.jame.core.util.AbstractActionXMLExporterRuntime;
import net.sf.jame.core.util.Color32bit;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLNodeBuilder;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ColorNodeActionXMLExporterRuntime extends AbstractActionXMLExporterRuntime {
	/**
	 * @see net.sf.jame.twister.util.AbstractActionXMLExporterRuntime#exportParams(net.sf.jame.core.tree.NodeActionValue, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportParams(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ColorElementXMLExporter exporter = new ColorElementXMLExporter();
		final ColorElement configElement0 = new ColorElement((Color32bit) action.getActionParams()[0]);
		final ColorElement configElement1 = new ColorElement((Color32bit) action.getActionParams()[1]);
		element.appendChild(exporter.exportToElement(configElement0, builder));
		element.appendChild(exporter.exportToElement(configElement1, builder));
	}
}
