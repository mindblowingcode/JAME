/*
 * $Id:$
 *
 */
package net.sf.jame.contextfree.extensions.figure;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

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
public class SquareFigureRuntime<T extends SquareFigureConfig> extends FigureExtensionRuntime<T> implements ContextFreePath {
	@Override
	public void register(ContextFreeContext contextFreeContext) {
		contextFreeContext.registerPath(this);
	}

	public String getName() {
		return "square";
	}

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
		return new FigureContextFreeNode(context, state, limits);
	}
	
	private class FigureContextFreeNode extends ContextFreeNode {
		private ContextFreeState state;
		
		public FigureContextFreeNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits) {
			this.state = state;
		}

		@Override
		public void drawNode(Graphics2D g2d, ContextFreeArea area) {
			Color c = new Color((((int) Math.rint((255 * state.getCurrentAlpha()))) << 24) |  Color.HSBtoRGB(state.getCurrentHue(), state.getCurrentSaturation(), state.getCurrentBrightness()));
			g2d.setColor(c);
			float sx = area.getScaleX();
			float sy = area.getScaleY();
			float x = area.getX() + state.getX() * sx;
			float y = area.getY() + state.getY() * sy;
			g2d.draw(new Rectangle2D.Float(x - 0.5f * sx, y - 0.5f * sy, 1 * sx, 1 * sy));	
		}
	}
}
