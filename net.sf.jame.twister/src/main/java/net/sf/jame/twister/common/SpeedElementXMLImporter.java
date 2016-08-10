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

import java.util.StringTokenizer;

import net.sf.jame.core.config.ValueConfigElementXMLImporter;
import net.sf.jame.core.util.DoubleVector4D;
import net.sf.jame.twister.util.Speed;

/**
 * @author Andrea Medeghini
 */
public class SpeedElementXMLImporter extends ValueConfigElementXMLImporter<Speed, SpeedElement> {
	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected Speed parseValue(final String value) {
		final StringTokenizer tknz = new StringTokenizer(value, ";");
		if (tknz.hasMoreTokens()) {
			final DoubleVector4D position = DoubleVector4D.valueOf(tknz.nextToken());
			if (tknz.hasMoreTokens()) {
				final DoubleVector4D rotation = DoubleVector4D.valueOf(tknz.nextToken());
				return new Speed(position, rotation);
			}
		}
		throw new RuntimeException("Invalid format: " + value);
	}

	/**
	 * @see net.sf.jame.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected SpeedElement createDefaultConfigElement() {
		return new SpeedElement(new Speed(new DoubleVector4D(0, 0, 100, 1), new DoubleVector4D(0, 0, 100, 0)));
	}
}
