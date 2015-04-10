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

import net.sf.jame.core.xml.XMLImportException;
import net.sf.jame.core.xml.XMLImporter;

import org.w3c.dom.Element;

/**
 * @author Andrea Medeghini
 */
public class TwisterBookmarkXMLImporter extends XMLImporter<TwisterBookmark> {
	/**
	 * @param element
	 * @return
	 * @throws XMLImportException
	 */
	@Override
	public TwisterBookmark importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, "bookmark");
		final TwisterBookmark bookmark = new TwisterBookmark();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 1) {
			importProperties(bookmark, propertyElements);
		}
		return bookmark;
	}

	/**
	 * @param bookmark
	 * @param propertyElements
	 * @throws XMLImportException
	 */
	protected void importProperties(final TwisterBookmark bookmark, final List<Element> propertyElements) throws XMLImportException {
		importConfig(bookmark, propertyElements.get(0));
	}

	/**
	 * @param bookmark
	 * @param element
	 * @throws XMLImportException
	 */
	protected void importConfig(final TwisterBookmark bookmark, final Element element) throws XMLImportException {
		final TwisterConfigXMLImporter configImporter = new TwisterConfigXMLImporter();
		bookmark.setConfig(configImporter.importFromElement(element));
	}
}
