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
package net.sf.jame.core.xml;

import net.sf.jame.core.CoreRegistry;
import net.sf.jame.core.actionXMLExporter.extension.ActionXMLExporterExtensionRuntime;
import net.sf.jame.core.extension.Extension;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.tree.NodeActionValue;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ActionXMLExporter extends XMLExporter<NodeActionValue> {
	/**
	 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final NodeActionValue action, final XMLNodeBuilder builder) throws XMLExportException {
		try {
			final Extension<ActionXMLExporterExtensionRuntime> extension = CoreRegistry.getInstance().getXMLActionExporterExtension(action.getActionId());
			final XMLExporter<NodeActionValue> exporter = extension.createExtensionRuntime().createXMLExporter();
			return exporter.exportToElement(action, builder);
		}
		catch (final ExtensionException e) {
			throw new XMLExportException(e);
		}
	}
}
