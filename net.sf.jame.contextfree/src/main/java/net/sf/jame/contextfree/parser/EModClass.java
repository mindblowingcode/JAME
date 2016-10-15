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
package net.sf.jame.contextfree.parser;

enum EModClass {
    InvalidClass(-1), 
    NotAClass(0), 
    GeomClass(1), 
    ZClass(2), 
    TimeClass(4),
    HueClass(8), 
    SatClass(16), 
    BrightClass(32), 
    AlphaClass(64),
    HueTargetClass(128), 
    SatTargetClass(256), 
    BrightTargetClass(512), 
    AlphaTargetClass(1024),
    StrokeClass(2048), 
    ParamClass(4096), 
    PathOpClass(8192);
    
	private int type;
	
	private EModClass(int type) { 
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public static EModClass classByOrdinal(int ordinal) {
		for (EModClass type : EModClass.values()) {
			if (type.ordinal() == ordinal) {
				return type;
			}
		}
		return NotAClass;
	}
}
