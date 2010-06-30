/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import net.sf.jame.contextfree.cfdg.figure.extension.FigureExtensionRuntime;
import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeLimits;
import net.sf.jame.contextfree.renderer.ContextFreeNode;
import net.sf.jame.contextfree.renderer.ContextFreePath;
import net.sf.jame.contextfree.renderer.ContextFreeState;

/**
 * @author Andrea Medeghini
 */
public class CircleFigureRuntime<T extends CircleFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	@Override
	public void register(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}

	public String getName() {
		return "CIRCLE";
	}

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
		return new FigureContextFreeNode(context, state, limits);
	}
	
	private class FigureContextFreeNode extends ContextFreeNode {
		private AlphaComposite a; 
		private Color c;
		private float[] p; 
		private float[] q;

		public FigureContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			int n = 50;
			p = new float[n * 2];
			q = new float[p.length];
			double e = 0;
			double d = 2 * Math.PI / n;
			for (int t = 0; t < n * 2; t += 2) {
				p[t + 0] = (float) (0.5 * Math.cos(e));
				p[t + 1] = (float) (0.5 * Math.sin(e));
				e += d;
			}
			float[] hsba = state.getHSBA();
			a = AlphaComposite.Src.derive(hsba[3]);
			c = Color.getHSBColor(hsba[0], hsba[1], hsba[2]);
			state.transform(p, q);
			for (int i = 0; i < q.length; i += 2) {
				limits.addPoint(q[i + 0], q[i + 1]);
			}
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
}
