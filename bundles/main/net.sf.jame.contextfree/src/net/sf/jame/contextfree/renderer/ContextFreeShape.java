package net.sf.jame.contextfree.renderer;

import java.awt.Graphics2D;

public interface ContextFreeShape {
	public boolean expand();

	public float getZ();

	public void render(Graphics2D g2d, ContextFreeArea area);
}
