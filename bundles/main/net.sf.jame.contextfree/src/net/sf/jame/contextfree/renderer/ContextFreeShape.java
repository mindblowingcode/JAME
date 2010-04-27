package net.sf.jame.contextfree.renderer;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface ContextFreeShape {
	/**
	 * @param size 
	 * @param limits
	 */
	public void prepare(Dimension size, ContextFreeLimits limits);

	/**
	 * @param g2d 
	 * @param area
	 * @param state
	 */
	public void draw(Graphics2D g2d, ContextFreeArea area, ContextFreeState state);
}
