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
package net.sf.jame.twister.common;

import net.sf.jame.core.config.ValueConfigElementXMLImporter;
import net.sf.jame.core.xml.XMLExporter;
import net.sf.jame.core.xml.XMLImportException;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class PercentageElementXMLImporter extends ValueConfigElementXMLImporter<Integer, PercentageElement> {
	/**
	 * @param element
	 * @param expectedClassId
	 * @throws XMLImportException
	 */
	@Override
	protected void checkClassId(final Element element, final String expectedClassId) throws XMLImportException {
		final String classId = getClassId(element);
		if (!"Percent".equals(classId) && !classId.equals(expectedClassId)) {
			throw new XMLImportException("Invalid value: " + XMLExporter.ATTRIBUTE_CLASS_ID + " = " + expectedClassId);
		}
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected Integer parseValue(final String value) {
		return Integer.valueOf(value);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected PercentageElement createDefaultConfigElement() {
		return new PercentageElement(100);
	}
}
