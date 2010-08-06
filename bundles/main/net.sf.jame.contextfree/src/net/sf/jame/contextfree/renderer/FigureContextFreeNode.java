package net.sf.jame.contextfree.renderer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class FigureContextFreeNode extends ContextFreeNode {
	private AlphaComposite a; 
	private Color c;
	private float[] q;

	public FigureContextFreeNode(ContextFreeState state, float[] q) {
		this.q = q;
		float[] hsba = state.getHSBA();
		a = AlphaComposite.Src.derive(hsba[3]);
		c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
	}

	@Override
	public void drawNode(Graphics2D g2d, ContextFreeArea area) {
		g2d.setComposite(a);
		g2d.setColor(c);
		float sx = area.getScaleX();
		float sy = area.getScaleY();
		float tx = area.getX();
		float ty = area.getY();
		float px;
		float py;
		float qx;
		float qy;
		float gx;
		float gy;
		GeneralPath path = new GeneralPath();
		gx = px = tx + q[0] * sx;
		gy = py = ty + q[1] * sy;
		for (int i = 2; i < q.length; i += 2) {
			qx = tx + q[i + 0] * sx;
			qy = ty + q[i + 1] * sy;
			path.append(new Line2D.Float(px, py, qx, qy), true);
			px = qx;
			py = qy;
		}
		path.append(new Line2D.Float(px, py, gx, gy), true);
		g2d.fill(path);	
		g2d.draw(path);	
	}
}
