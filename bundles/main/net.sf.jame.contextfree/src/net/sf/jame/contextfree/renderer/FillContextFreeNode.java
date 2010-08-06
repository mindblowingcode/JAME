/**
 * 
 */
package net.sf.jame.contextfree.renderer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class FillContextFreeNode extends ContextFreeNode {
	private ContextFreeState state;
	private AlphaComposite a; 
	private Color c;
	private int r;

	public FillContextFreeNode(ContextFreeState state, String rule) {
		float[] hsba = state.getHSBA();
		a = AlphaComposite.Src.derive(hsba[3]);
		c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
		r = getRule(rule);
	}

	private int getRule(String rule) {
		if ("evenodd".equals(rule)) { 
			return Path2D.WIND_EVEN_ODD;
		} else if ("nonzero".equals(rule)) { 
			return Path2D.WIND_NON_ZERO;
		}
		throw new IllegalArgumentException("Rule not supported");
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
		state.fill(g2d, t, a, c, r);
	}
}