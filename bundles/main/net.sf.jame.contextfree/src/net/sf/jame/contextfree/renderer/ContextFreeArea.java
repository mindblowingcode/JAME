package net.sf.jame.contextfree.renderer;

public class ContextFreeArea {
	private float x;
	private float y;
	private float scaleX;
	private float scaleY;
	private float sizeX;
	private float sizeY;
	
	public ContextFreeArea(float x, float y, float sizeX, float sizeY, float scaleX, float scaleY) {
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
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
