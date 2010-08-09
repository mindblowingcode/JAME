/**
 * 
 */
package net.sf.jame.contextfree.renderer;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class PathShape extends ContextFreeShape {
	private ContextFreeState state;
	private AlphaComposite a; 
	private BasicStroke s;
	private Color c;

	public PathShape(ContextFreeState state, String cap, String join, Float width) {
		super(state.getZ());
		float[] hsba = state.getHSBA();
		a = AlphaComposite.Src.derive(hsba[3]);
		c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
		s = new BasicStroke(width, getCap(cap), getJoin(join));
	}

	private int getCap(String cap) {
		if ("butt".equals(cap)) {
			return BasicStroke.CAP_BUTT;
		} else if ("round".equals(cap)) {
			return BasicStroke.CAP_ROUND;
		} else if ("square".equals(cap)) {
			return BasicStroke.CAP_SQUARE;
		}
		throw new IllegalArgumentException("Cap not supported");
	}

	private int getJoin(String join) {
		if ("miter".equals(join)) {
			return BasicStroke.JOIN_MITER;
		} else if ("round".equals(join)) {
			return BasicStroke.JOIN_ROUND;
		} else if ("bevel".equals(join)) {
			return BasicStroke.JOIN_BEVEL;
		}
		throw new IllegalArgumentException("Join not supported");
	}

	@Override
	public void render(Graphics2D g2d, ContextFreeArea area) {
		AffineTransform t = new AffineTransform();
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		t.translate(tx, ty);
		t.scale(sx, sy);
		state.draw(g2d, t, a, c, s);
	}
}