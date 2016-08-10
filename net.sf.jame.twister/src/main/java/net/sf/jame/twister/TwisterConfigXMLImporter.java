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
package net.sf.jame.twister;

import java.util.List;

import net.sf.jame.core.common.ColorElement;
import net.sf.jame.core.common.ColorElementXMLImporter;
import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;
import net.sf.jame.twister.effect.EffectConfigElement;
import net.sf.jame.twister.effect.EffectConfigElementXMLImporter;
import net.sf.jame.twister.frame.FrameConfigElement;
import net.sf.jame.twister.frame.FrameConfigElementXMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class TwisterConfigXMLImporter extends XMLImporter<TwisterConfig> {
	/**
	 * @param element
	 * @return
	 * @throws XMLImportException
	 */
	@Override
	public TwisterConfig importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, TwisterConfig.CLASS_ID);
		final TwisterConfig config = new TwisterConfig();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 3) {
			importProperties(config, propertyElements);
		}
		return config;
	}

	/**
	 * @param config
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties(final TwisterConfig config, final List<Element> propertyElements) throws XMLImportException {
		importBackground(config, propertyElements.get(0));
		importFrame(config, propertyElements.get(1));
		importEffect(config, propertyElements.get(2));
	}

	/**
	 * @param configElement
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importBackground(final TwisterConfig config, final Element element) throws XMLImportException {
		final List<Element> colorsElements = this.getElements(element, ColorElement.CLASS_ID);
		if (colorsElements.size() == 1) {
			config.setBackground(new ColorElementXMLImporter().importFromElement(colorsElements.get(0)).getValue());
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importFrame(final TwisterConfig config, final Element element) throws XMLImportException {
		final FrameConfigElementXMLImporter frameImporter = new FrameConfigElementXMLImporter();
		final List<Element> frameElements = this.getElements(element, FrameConfigElement.CLASS_ID);
		if (frameElements.size() == 1) {
			config.setFrameConfigElement(frameImporter.importFromElement(frameElements.get(0)));
		}
	}

	/**
	 * @param config
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importEffect(final TwisterConfig config, final Element element) throws XMLImportException {
		final EffectConfigElementXMLImporter effectImporter = new EffectConfigElementXMLImporter();
		final List<Element> effectElements = this.getElements(element, EffectConfigElement.CLASS_ID);
		if (effectElements.size() == 1) {
			config.setEffectConfigElement(effectImporter.importFromElement(effectElements.get(0)));
		}
	}
}
