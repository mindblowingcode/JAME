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

public class HSBColor {
	private double[] values = new double[4];
	
	public HSBColor(double h, double s, double b, double a) {
		this.values[0] = h;
		this.values[1] = s;
		this.values[2] = b;
		this.values[3] = a;
	}
	
	public double hue() {
		return values[0];
	}
	
	public double bright() {
		return values[1];
	}
	
	public double sat() {
		return values[2];
	}
	
	public double alpha() {
		return values[3];
	}
	
	public double[] values() {
		return values;
	}

	public static double adjustHue(double base, double adjustment) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	public static double adjust(double base, double adjustment) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	public static double adjustHue(double base, double adjustment, EAssignmentType useTarget, double target) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	public static double adjust(double base, double adjustment, EAssignmentType useTarget, double target) {
		// TODO Auto-generated method stub
		return 0.0;
	}
}
