package net.sf.jame.contextfree.renderer;

import java.awt.Graphics2D;

public class DefaultContextFreeShape implements ContextFreeShape {
	private float z;
	
	public DefaultContextFreeShape(float z) {
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
