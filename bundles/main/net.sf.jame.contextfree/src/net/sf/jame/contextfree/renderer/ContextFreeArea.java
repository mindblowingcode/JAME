package net.sf.jame.contextfree.renderer;

public class ContextFreeArea {
	private float x;
	private float y;
	private float scaleX;
	private float scaleY;
	private float sizeX;
	private float sizeY;
	private float minX;
	private float minY;
	private float maxX;
	private float maxY;
	
	public ContextFreeArea(float x, float y, float sizeX, float sizeY, float scaleX, float scaleY) {
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		minX = x - sizeX / 2;
		minY = y - sizeY / 2;
		maxX = x + sizeX / 2;
		maxY = y + sizeY / 2;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
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

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getSizeX() {
		return sizeX;
	}

	public float getSizeY() {
		return sizeY;
	}
}
