/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
public class TriangleFigureRuntime<T extends TriangleFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	@Override
	public void register(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}

	public String getName() {
		return "triangle";
	}

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
		return new FigureContextFreeNode(context, state, limits);
	}
	
	private class FigureContextFreeNode extends ContextFreeNode {
		private ContextFreeState state;
		private float[] p = { -0.5f, (float) (-0.5 * Math.tan(Math.PI / 6.0)), +0.5f, (float) (-0.5 * Math.tan(Math.PI / 6.0)), 0, (float) (0.5 * Math.tan(Math.PI / 3.0) - 0.5 * Math.tan(Math.PI / 6.0)) }; 
		private float[] q = new float[6]; 
		
		public FigureContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			this.state = state;
			AffineTransform t = new AffineTransform();
			t.translate(state.getX(), state.getY());
			t.rotate(state.getRotation());
			t.shear(state.getSkewX(), state.getSkewY());
			t.scale((state.getFlipX() < 0 ? -1 : +1) * state.getSizeX(), (state.getFlipY() < 0 ? -1 : +1) * state.getSizeY());
			t.transform(p, 0, q, 0, 3);
			for (int i = 0; i < 6; i += 2) {
				limits.addPoint(q[i + 0], q[i + 1]);
			}
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
//			Color c = new Color((((int) Math.rint((255 * state.getCurrentAlpha()))) << 24) |  Color.HSBtoRGB(state.getCurrentHue(), state.getCurrentSaturation(), state.getCurrentBrightness()), true);
			Color c = Color.getHSBColor(state.getCurrentHue(), state.getCurrentSaturation(), state.getCurrentBrightness());
			g2d.setComposite(AlphaComposite.Src.derive(state.getCurrentAlpha()));
			g2d.setColor(c);
			float sx = area.getScaleX();
			float sy = area.getScaleY();
			float tx = area.getX();
			float ty = area.getY();
			GeneralPath path = new GeneralPath();
			path.append(new Line2D.Float(tx + q[0] * sx, ty + q[1] * sy, tx + q[2] * sx, ty + q[3] * sy), false);
			path.append(new Line2D.Float(tx + q[2] * sx, ty + q[3] * sy, tx + q[4] * sx, ty + q[5] * sy), false);
			path.append(new Line2D.Float(tx + q[4] * sx, ty + q[5] * sy, tx + q[0] * sx, ty + q[1] * sy), true);
			g2d.fill(path);	
			g2d.draw(path);	
		}
	}
}
