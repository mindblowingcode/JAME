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
