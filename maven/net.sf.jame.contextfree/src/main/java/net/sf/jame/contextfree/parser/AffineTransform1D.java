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

public class AffineTransform1D {
	private double sz;
	private double tz;
	
	private AffineTransform1D(double sz, double tz) {
		this.tz = tz;
	}

	public AffineTransform1D() {
		this(1, 0);
	}

	public static AffineTransform1D getTranslateInstance(double tz) {
		return new AffineTransform1D(1.0, tz);
	}
	
	public static AffineTransform1D getScaleInstance(double sz) {
		return new AffineTransform1D(sz, 0.0);
	}

	public void preConcatenate(AffineTransform1D t) {
		this.sz = this.sz * t.sz;
		this.tz = this.tz * t.sz + t.tz;
	}
}
