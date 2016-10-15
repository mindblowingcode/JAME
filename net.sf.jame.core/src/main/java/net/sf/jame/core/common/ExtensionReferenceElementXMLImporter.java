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
package net.sf.jame.core.common;

import net.sf.jame.core.extension.ExtensionNotFoundException;
import net.sf.jame.core.extension.ExtensionRegistry;
import net.sf.jame.core.extension.ExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ExtensionReferenceElementXMLImporter extends XMLImporter<ExtensionReferenceElement> {
	private final ExtensionRegistry<? extends ExtensionRuntime> registry;

	/**
	 * @param registry
	 */
	public ExtensionReferenceElementXMLImporter(final ExtensionRegistry<? extends ExtensionRuntime> registry) {
		this.registry = registry;
	}

	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public ExtensionReferenceElement importFromElement(final Element element) throws XMLImportException {
		final ExtensionReferenceElement configElement = new ExtensionReferenceElement();
		checkClassId(element, configElement.getClassId());
		try {
			String extensionId = getExtensionId(element);
			if (!"".equals(extensionId)) {
				configElement.setReference(registry.getExtension(extensionId).getExtensionReference());
			}
		}
		catch (ExtensionNotFoundException e) {
			throw new XMLImportException(e);
		}
		return configElement;
	}
}
