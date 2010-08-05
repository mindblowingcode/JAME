package net.sf.jame.contextfree.renderer;

public class ContextFreeBounds {
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;

	public float getMinX() {
		return minX;
	}

	public float getMinY() {
		return minY;
	}

	public float getMaxX() {
		return maxX;
	}

	public float getMaxY() {
		return maxY;
	}
	
	public void addPoint(float x, float y) {
		minX = minX > x ? x : minX; 
		minY = minY > y ? y : minY; 
		maxX = maxX < x ? x : maxX; 
		maxY = maxY < y ? y : maxY; 
	}
}
