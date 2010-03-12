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
package net.sf.jame.core.util;

import net.sf.jame.core.tree.NodeActionValue;
import net.sf.jame.core.xml.XML;
import net.sf.jame.core.xml.XMLExportException;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLNodeBuilder;
import net.sf.jame.core.xml.extension.ActionXMLExporterExtensionRuntime;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractActionXMLExporterRuntime extends ActionXMLExporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.xml.extension.ActionXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<NodeActionValue> createXMLExporter() {
		return new ActionExporter();
	}

	private class ActionExporter extends XMLExporter<NodeActionValue> {
		/**
		 * @see net.sf.jame.core.xml.XMLExporter#exportToElement(java.lang.Object, net.sf.jame.core.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final NodeActionValue action, final XMLNodeBuilder builder) throws XMLExportException {
			final Element element = this.createElement(builder, "action", action.getActionId(), 1, 1);
			exportProperties(action, element, builder);
			return element;
		}

		/**
		 * @param action
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportProperties(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportActionType(action, createProperty(builder, element, "actionType"), builder);
			exportTimestamp(action, createProperty(builder, element, "timestamp"), builder);
			exportRefreshRequired(action, createProperty(builder, element, "refreshRequired"), builder);
			exportTarget(action, createProperty(builder, element, "actionTarget"), builder);
			exportParams(action, createProperty(builder, element, "actionParams"), builder);
		}

		private void exportActionType(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) {
			element.appendChild(XML.createIntegerElement(builder, "value", action.getActionType()));
		}

		private void exportTimestamp(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) {
			element.appendChild(XML.createLongElement(builder, "value", action.getTimestamp()));
		}

		private void exportRefreshRequired(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) {
			element.appendChild(XML.createBooleanElement(builder, "value", action.isRefreshRequired()));
		}

		private void exportTarget(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) {
			element.appendChild(XML.createStringElement(builder, "value", action.getActionTarget().toString()));
		}
	}

	/**
	 * @param action
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected abstract void exportParams(NodeActionValue action, Element element, XMLNodeBuilder builder) throws XMLExportException;

	/**
	 * @see net.sf.jame.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
	}
}
