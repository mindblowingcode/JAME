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
package net.sf.jame.core.util;

import net.sf.jame.core.common.IntegerElement;
import net.sf.jame.core.common.IntegerElementXMLImporter;
import net.sf.jame.core.config.ConfigElement;
import net.sf.jame.core.tree.NodeActionValue;
import net.sf.jame.core.xml.XML;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractConfigElementListNodeActionXMLImporterRuntime<T extends ConfigElement> extends AbstractActionXMLImporterRuntime {
	/**
	 * @see net.sf.jame.core.util.AbstractActionXMLImporterRuntime#importParams(net.sf.jame.core.tree.NodeActionValue, org.w3c.dom.Element)
	 */
	@Override
	protected void importParams(final NodeActionValue action, final Element element) throws XMLImportException {
		final XMLImporter<T> importer = this.createImporter();
		final IntegerElementXMLImporter indexImporter = new IntegerElementXMLImporter();
		final List<Element> elements = XML.getElementsByName(element, "element");
		if (elements.size() == 2) {
			final IntegerElement configElement0 = indexImporter.importFromElement(elements.get(0));
			final ConfigElement configElement1 = importer.importFromElement(elements.get(1));
			action.setActionParams(new Serializable[] { configElement0.getValue(), configElement1 });
		}
	}

	/**
	 * @return
	 */
	protected abstract XMLImporter<T> createImporter();
}
