package net.sf.jame.contextfree.renderer;

public class ContextFreeLimits {
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;

	public void setMinX(float minX) {
		this.minX = minX;
	}

	public void setMinY(float minY) {
		this.minY = minY;
	}

	public void setMaxX(float maxX) {
		this.maxX = maxX;
	}

	public void setMaxY(float maxY) {
		this.maxY = maxY;
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
}
