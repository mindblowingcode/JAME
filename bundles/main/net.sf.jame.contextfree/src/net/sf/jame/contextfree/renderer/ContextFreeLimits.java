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
		float scale = Math.min(width / (maxX - minX), height / (maxY - minY));
		float x = (width + ((minX + maxX) * scale)) / 2f;
		float y = (height - ((minY + maxY) * scale)) / 2f;
		return new ContextFreeArea(x, y, width, height, +scale, -scale);
	}
}
