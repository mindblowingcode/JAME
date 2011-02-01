package net.sf.jame.contextfree.renderer.support;

import java.awt.Graphics2D;

import net.sf.jame.contextfree.renderer.ContextFreeArea;

public abstract class FinishedShape {
	private float z;
	
	public FinishedShape(float z) {
		this.z = z;
	}
	
	public boolean expand() {
		return false;
	}

	public float getZ() {
		return z;
	}

	public void render(Graphics2D g2d, ContextFreeArea area) {
	}
}
