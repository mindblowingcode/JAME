package net.sf.jame.contextfree.renderer;

public class ContextFreeLimits {
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;
	private float x;
	private float y;
	private float width;
	private float height;
	private float border;

	public ContextFreeLimits(int x, int y, float width, float height, int border) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.border = border;
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
		float scale = Math.min((width - 2 * border) / Math.abs(maxX - minX), (height - 2 * border) / Math.abs(maxY - minY));
		return new ContextFreeArea(x + (width + (minX + maxX) * scale) / 2, y + (height + (minY + maxY) * scale) / 2, width, height, +scale, -scale);
	}
}
