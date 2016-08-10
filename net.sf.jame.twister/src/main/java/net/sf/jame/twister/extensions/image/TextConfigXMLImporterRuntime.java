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
package net.sf.jame.twister.extensions.image;

import java.util.List;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLImporter;
import net.sf.jame.core.common.DoubleElement;
import net.sf.jame.core.common.DoubleElementXMLImporter;
import net.sf.jame.core.common.FontElement;
import net.sf.jame.core.common.FontElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class TextConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see net.sf.jame.core.extensionConfigXMLImporter.extension.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<TextConfig> createXMLImporter() {
		return new TextConfigXMLImporter();
	}

	private class TextConfigXMLImporter extends AbstractImageConfigXMLImporter<TextConfig> {
		/**
		 * @see net.sf.jame.twister.extensions.image.AbstractImageConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected TextConfig createExtensionConfig() {
			return new TextConfig();
		}

		/**
		 * @see net.sf.jame.twister.extensions.image.AbstractImageConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "TextConfig";
		}

		/**
		 * @see net.sf.jame.twister.extensions.image.AbstractImageConfigXMLImporter#importFromElement(org.w3c.dom.Element)
		 */
		@Override
		public TextConfig importFromElement(final Element element) throws XMLImportException {
			final TextConfig config = super.importFromElement(element);
			final List<Element> propertyElements = getProperties(element);
			if (propertyElements.size() == 7) {
				importProperties(config, propertyElements);
			}
			return config;
		}

		/**
		 * @param config
		 * @param propertyElements
		 * @throws XMLImportException
		 */
		protected void importProperties(final TextConfig config, final List<Element> propertyElements) throws XMLImportException {
			importColor(config, propertyElements.get(0));
			importFont(config, propertyElements.get(1));
			importSize(config, propertyElements.get(2));
			importText(config, propertyElements.get(3));
			importLeft(config, propertyElements.get(4));
			importTop(config, propertyElements.get(5));
			importRotation(config, propertyElements.get(6));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importColor(final TextConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, ColorElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getColorElement().setValue(new ColorElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importFont(final TextConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, FontElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getFontElement().setValue(new FontElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importSize(final TextConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getSizeElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importText(final TextConfig config, final Element element) throws XMLImportException {
			List<Element> elements = this.getElements(element, StringElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getTextElement().setValue(new StringElementXMLImporter().importFromElement(elements.get(0)).getValue());
			} else {
				//TODO orrore: per retrocompatibilit� devo gestire la classe label
				elements = this.getElements(element, "Label");
				if (elements.size() == 1) {
					config.getTextElement().setValue(new StringElementXMLImporter().importFromElement(elements.get(0)).getValue());
				}
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importLeft(final TextConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getLeftElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importTop(final TextConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getTopElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importRotation(final TextConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, DoubleElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getRotationElement().setValue(new DoubleElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}
	}
}
