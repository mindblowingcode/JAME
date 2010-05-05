package net.sf.jame.contextfree.renderer;

public class ContextFreeLimits {
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;
	private float width;
	private float height;

	public ContextFreeLimits(float width, float height) {
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
	
	public ContextFreeArea getArea() {
		float sizeX = width;
		float sizeY = height;
		float scaleX = sizeX / (maxX - minX);
		float scaleY = sizeY / (maxY - minY);
		float x = ((minX + maxX) * scaleX) / 2f;
		float y = ((minY + maxY) * scaleY) / 2f;
		return new ContextFreeArea(x, y, sizeX, sizeY, scaleX, scaleY);
	}
}
