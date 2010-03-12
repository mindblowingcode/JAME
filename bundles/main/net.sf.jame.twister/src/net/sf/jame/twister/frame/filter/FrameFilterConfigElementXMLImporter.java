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
package net.sf.jame.twister.frame.filter;

import java.util.List;

import net.sf.jame.core.common.BooleanElement;
import net.sf.jame.core.common.BooleanElementXMLImporter;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElement;
import net.sf.jame.core.common.ConfigurableExtensionReferenceElementXMLImporter;
import net.sf.jame.core.common.StringElement;
import net.sf.jame.core.common.StringElementXMLImporter;
import net.sf.jame.core.extension.ExtensionException;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.twister.TwisterRegistry;
import net.sf.jame.twister.frame.filter.extension.FrameFilterExtensionConfig;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class FrameFilterConfigElementXMLImporter extends XMLImporter<FrameFilterConfigElement> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public FrameFilterConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, FrameFilterConfigElement.CLASS_ID);
		final FrameFilterConfigElement configElement = new FrameFilterConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (isVersion(element, 1) && (propertyElements.size() == 4)) {
			try {
				importProperties1(configElement, propertyElements);
			}
			catch (final ExtensionException e) {
				throw new XMLImportException(e);
			}
		}
		else if (isVersion(element, 0) && (propertyElements.size() == 3)) {
			try {
				importProperties0(configElement, propertyElements);
			}
			catch (final ExtensionException e) {
				throw new XMLImportException(e);
			}
		}
		return configElement;
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws ExtensionException
	 * @throws XMLImportException
	 */
	protected void importProperties0(final FrameFilterConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importExtension(configElement, propertyElements.get(0));
		importEnabled(configElement, propertyElements.get(1));
		importLocked(configElement, propertyElements.get(2));
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws ExtensionException
	 * @throws XMLImportException
	 */
	protected void importProperties1(final FrameFilterConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importExtension(configElement, propertyElements.get(0));
		importEnabled(configElement, propertyElements.get(1));
		importLocked(configElement, propertyElements.get(2));
		importLabel(configElement, propertyElements.get(3));
	}

	private void importExtension(final FrameFilterConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> extensionElements = this.getElements(element, ConfigurableExtensionReferenceElement.CLASS_ID);
		if (extensionElements.size() == 1) {
			configElement.setReference(new ConfigurableExtensionReferenceElementXMLImporter<FrameFilterExtensionConfig>(TwisterRegistry.getInstance().getFrameFilterRegistry()).importFromElement(extensionElements.get(0)).getReference());
		}
	}

	private void importEnabled(final FrameFilterConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> booleanElements = this.getElements(element, BooleanElement.CLASS_ID);
		if (booleanElements.size() == 1) {
			configElement.setEnabled(new BooleanElementXMLImporter().importFromElement(booleanElements.get(0)).getValue());
		}
	}

	private void importLocked(final FrameFilterConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> booleanElements = this.getElements(element, BooleanElement.CLASS_ID);
		if (booleanElements.size() == 1) {
			configElement.setLocked(new BooleanElementXMLImporter().importFromElement(booleanElements.get(0)).getValue());
		}
	}

	private void importLabel(final FrameFilterConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> stringElements = this.getElements(element, StringElement.CLASS_ID);
		if (stringElements.size() == 1) {
			configElement.setLabel(new StringElementXMLImporter().importFromElement(stringElements.get(0)).getValue());
		}
	}
}
