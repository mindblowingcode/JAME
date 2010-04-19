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
package net.sf.jame.contextfree;

import java.util.List;

import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.CFDGConfigElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.twister.common.SpeedElement;
import net.sf.jame.twister.common.SpeedElementXMLImporter;
import net.sf.jame.twister.common.ViewElement;
import net.sf.jame.twister.common.ViewElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigXMLImporter extends XMLImporter<ContextFreeConfig> {
	/**
	 * @see net.sf.jame.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public ContextFreeConfig importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, ContextFreeConfig.CLASS_ID);
		final ContextFreeConfig config = new ContextFreeConfig();
		final List<Element> propertyElements = getProperties(element);
		if (isVersion(element, 0) && (propertyElements.size() == 3)) {
			importProperties(config, propertyElements);
		}
		return config;
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties(final ContextFreeConfig config, final List<Element> propertyElements) throws XMLImportException {
		importCFDG(config, propertyElements.get(0));
		importView(config, propertyElements.get(1));
		importSpeed(config, propertyElements.get(2));
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importCFDG(final ContextFreeConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, CFDGConfigElement.CLASS_ID);
		if (elements.size() == 1) {
			final CFDGConfigElement contextFreeFractal = new CFDGConfigElementXMLImporter().importFromElement(elements.get(0));
			config.setCFDG(contextFreeFractal);
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importView(final ContextFreeConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, ViewElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setView(new ViewElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importSpeed(final ContextFreeConfig config, final Element element) throws XMLImportException {
		final List<Element> elements = this.getElements(element, SpeedElement.CLASS_ID);
		if (elements.size() == 1) {
			config.setSpeed(new SpeedElementXMLImporter().importFromElement(elements.get(0)).getValue());
		}
	}
}
