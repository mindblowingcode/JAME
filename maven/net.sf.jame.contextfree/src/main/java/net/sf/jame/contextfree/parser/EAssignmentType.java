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
package net.sf.jame.contextfree.parser;

enum EAssignmentType { 
	ColorTarget(1), Color2Value(3), ColorMask(3), HueTarget(1), Hue2Value(3), HueMask(3), SaturationTarget(1 << 2), Saturation2Value(3 << 2), SaturationMask(3 << 2),
	BrightnessTarget(1 << 4), Brightness2Value(3 << 4), BrightnessMask(3 << 4), AlphaTarget(1 << 6), Alpha2Value(3 << 6), AlphaMask(3 << 6), 
	HSBA2Value(3 << 0 | 3 << 2 | 3 << 4 | 3 << 6), HSBATarget(1 << 0 | 1 << 2 | 1 << 4 | 1 << 6); 
	
	private int type;

	private EAssignmentType(int type) { 
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
