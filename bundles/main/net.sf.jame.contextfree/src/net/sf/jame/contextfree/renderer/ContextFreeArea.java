package net.sf.jame.contextfree.renderer;

public class ContextFreeArea {
	private float x;
	private float y;
	private float scaleX;
	private float scaleY;
	
	public ContextFreeArea() {
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

	public void update(float x, float y, float scaleX, float scaleY) {
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
}
