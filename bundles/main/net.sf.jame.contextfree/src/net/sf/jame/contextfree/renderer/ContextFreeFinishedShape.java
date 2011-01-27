package net.sf.jame.contextfree.renderer;

import java.awt.Graphics2D;

public abstract class ContextFreeFinishedShape {
	private float z;
	
	public ContextFreeFinishedShape(float z) {
		this.z = z;
	}
	
	public float getZ() {
		return z;
	}

	public void render(Graphics2D g2d, ContextFreeArea area) {
	}
}
