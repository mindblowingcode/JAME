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
package net.sf.jame.contextfree.renderer.support;

public class CFReplacement implements Cloneable {
	private int shapeType = -1;
	private int loopCount = 0;
	private CFModification modification;

	public CFReplacement(int shapeType, int loopCount, CFModification modification) {
		this.shapeType = shapeType;
		this.loopCount = loopCount;
		this.modification = modification;
	}

	public CFReplacement(int shapeType) {
		this.shapeType = shapeType;
		this.loopCount = 0;
		this.modification = new CFModification();
	}
	
	public final int getShapeType() {
		return shapeType;
	}

	public CFModification getModification() {
		return modification;
	}
	
	public int getLoopCount() {
		return loopCount;
	}

	@Override
	public CFReplacement clone() {
		return new CFReplacement(shapeType, loopCount, modification);
	}

	@Override
	public String toString() {
		return "CFReplacement [shapeType=" + shapeType + ", modification=" + modification + ", loopCount=" + loopCount + "]";
	}
}
