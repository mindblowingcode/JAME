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

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.HashMap;
import java.util.Map;

public class PrimShape {
	private GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
	private static final  Map<Integer, PrimShape> shapeMap = new HashMap<Integer, PrimShape>();
	private static final PrimShape CIRCLE = new CircleShape(); 
	private static final PrimShape SQUARE = new SquareShape(); 
	private static final PrimShape TRIANGLE = new TriangleShape(); 

	static {
		shapeMap.put(1, PrimShape.CIRCLE);	
		shapeMap.put(2, PrimShape.SQUARE);	
		shapeMap.put(3, PrimShape.TRIANGLE);	
	}
	
	public static boolean isPrimShape(int shapeType) {
		return shapeType < 4;
	}

	public static Map<Integer, PrimShape> getShapeMap() {
		return shapeMap;
	}

	public PathIterator getPathIterator() {
		return path.getPathIterator(new AffineTransform());
	}
	
	private static class CircleShape extends PrimShape {
		public CircleShape() {
			
		}
	}
	
	private static class SquareShape extends PrimShape {
		public SquareShape() {
			
		}
	}
	
	private static class TriangleShape extends PrimShape {
		public TriangleShape() {
			
		}
	}
}
