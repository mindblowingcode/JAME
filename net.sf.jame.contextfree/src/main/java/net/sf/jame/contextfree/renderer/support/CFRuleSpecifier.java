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
package net.sf.jame.contextfree.renderer.support;

public class CFRuleSpecifier implements Comparable<CFRuleSpecifier> {
	protected int initialShapeType;
	protected double weight;

	public CFRuleSpecifier(int initialShapeType, double weight) {
		this.initialShapeType = initialShapeType;
		this.weight = weight;
	}

	public int getInitialShapeType() {
		return initialShapeType;
	}

	public double getWeight() {
		return weight;
	}

	public int compareTo(CFRuleSpecifier s) {
		if (initialShapeType == s.initialShapeType) {
			return (weight - s.weight) < 0 ? -1 : +1;
		} else {
			return initialShapeType - s.initialShapeType;
		}
	}

	@Override
	public String toString() {
		return "CFRuleSpecifier [initialShapeType=" + initialShapeType + ", weight=" + weight + "]";
	}
}
