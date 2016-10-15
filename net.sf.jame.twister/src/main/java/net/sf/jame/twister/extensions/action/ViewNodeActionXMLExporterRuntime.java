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
package net.sf.jame.twister.extensions.action;

import net.sf.jame.core.tree.NodeActionValue;
import net.sf.jame.core.util.AbstractActionXMLExporterRuntime;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.twister.common.ViewElement;
import net.sf.jame.twister.common.ViewElementXMLExporter;
import net.sf.jame.twister.util.View;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ViewNodeActionXMLExporterRuntime extends AbstractActionXMLExporterRuntime {
	/**
	 * @see net.sf.jame.core.util.AbstractActionXMLExporterRuntime#exportParams(net.sf.jame.core.tree.NodeActionValue, org.w3c.dom.Element, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportParams(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final ViewElementXMLExporter exporter = new ViewElementXMLExporter();
		final ViewElement configElement0 = new ViewElement((View) action.getActionParams()[0]);
		final ViewElement configElement1 = new ViewElement((View) action.getActionParams()[1]);
		element.appendChild(exporter.exportToElement(configElement0, builder));
		element.appendChild(exporter.exportToElement(configElement1, builder));
	}
}
