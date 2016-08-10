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
package net.sf.jame.mandelbrot.renderer;

/**
 * @author Andrea Medeghini
 */
public class RenderedPoint {
	/**
	 * 
	 */
	public double xr;
	/**
	 * 
	 */
	public double xi;
	/**
	 * 
	 */
	public double zr;
	/**
	 * 
	 */
	public double zi;
	/**
	 * 
	 */
	public double wr;
	/**
	 * 
	 */
	public double wi;
	/**
	 * 
	 */
	public double pr;
	/**
	 * 
	 */
	public double pi;
	/**
	 * 
	 */
	public double tr;
	/**
	 * 
	 */
	public double ti;
	/**
	 * 
	 */
	public double dr;
	/**
	 * 
	 */
	public double di;
	/**
	 * 
	 */
	public int time;

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder point = new StringBuilder();
		point.append("<xr = ");
		point.append(xr);
		point.append(", xi = ");
		point.append(xi);
		point.append(", zr = ");
		point.append(zr);
		point.append(", zi = ");
		point.append(zi);
		point.append(", wr = ");
		point.append(wr);
		point.append(", wi = ");
		point.append(wi);
		point.append(", tr = ");
		point.append(pr);
		point.append(", ti = ");
		point.append(pi);
		point.append(", time = ");
		point.append(time);
		point.append(">");
		return point.toString();
	}
}
