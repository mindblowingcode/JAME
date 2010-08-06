/**
 * 
 */
package net.sf.jame.contextfree.renderer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class FlushContextFreeNode extends ContextFreeNode {
	private ContextFreeState state;
	private AlphaComposite a; 
	private Color c;
	
	public FlushContextFreeNode(ContextFreeState state) {
		float[] hsba = state.getHSBA();
		a = AlphaComposite.Src.derive(hsba[3]);
		c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
	}

	@Override
	public void drawNode(Graphics2D g2d, ContextFreeArea area) {
		AffineTransform t = new AffineTransform();
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		t.translate(tx, ty);
		t.scale(sx, sy);
		state.flush(g2d, t, a, c);
	}
}