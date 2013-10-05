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
package net.sf.jame.contextfree.extensions.image;

import java.util.List;

import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.ContextFreeConfigXMLImporter;
import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeImageConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<ContextFreeImageConfig> createXMLImporter() {
		return new ContextFreeImageConfigXMLImporter();
	}

	public class ContextFreeImageConfigXMLImporter extends XMLImporter<ContextFreeImageConfig> {
		/**
		 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public ContextFreeImageConfig importFromElement(final Element element) throws XMLImportException {
			checkClassId(element, ContextFreeImageConfig.CLASS_ID);
			final ContextFreeImageConfig config = new ContextFreeImageConfig();
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 1) {
				importProperties(config, propertyElements);
			}
			return config;
		}

		/**
		 * @param config
		 * @param propertyElements
		 * @throws XMLImportException
		 */
		protected void importProperties(final ContextFreeImageConfig config, final List<Element> propertyElements) throws XMLImportException {
			importContextFreeConfig(config, propertyElements.get(0));
		}

		/**
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importContextFreeConfig(final ContextFreeImageConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ContextFreeConfig.CLASS_ID);
			if (elements.size() == 1) {
				final ContextFreeConfig contextFreeConfig = new ContextFreeConfigXMLImporter().importFromElement(elements.get(0));
				config.setContextFreeConfig(contextFreeConfig);
			}
		}
	}
}
