package net.sf.jame.contextfree.renderer;

public class ContextFreeBounds {
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;
	private int width;
	private int height;

	public ContextFreeBounds(int width, int height) {
		this.width = width;
		this.height = height;
	}

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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isValid() {
		return (maxX - minX) > 0 && (maxY - minY) > 0;
	}
}
