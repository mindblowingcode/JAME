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

import net.sf.jame.core.config.ValueConfigElementXMLImporter;
import net.sf.jame.core.util.Rectangle;

/**
 * @author Andrea Medeghini
 */
public class RectangleElementXMLImporter extends ValueConfigElementXMLImporter<Rectangle, RectangleElement> {
	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected RectangleElement createDefaultConfigElement() {
		return new RectangleElement(new Rectangle(0, 0, 0, 0));
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected Rectangle parseValue(final String value) {
		return Rectangle.parseRectangle(value);
	}
}
