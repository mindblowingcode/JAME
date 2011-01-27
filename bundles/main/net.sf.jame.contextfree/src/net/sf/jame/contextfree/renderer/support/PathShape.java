/**
 * 
 */
package net.sf.jame.contextfree.renderer.support;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeShape;
import net.sf.jame.contextfree.renderer.ContextFreeState;

public class PathShape extends ContextFreeShape {
	private ExtendedGeneralPath path;
	private ContextFreeState state;
	private BasicStroke s;

	public PathShape(ContextFreeState state, String cap, String join, Float width) {
		super(state.getZ());
		this.state = state;
		path = state.getPath();
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
		throw new IllegalArgumentException("Cap not supported " + cap);
	}

	private int getJoin(String join) {
		if ("miter".equals(join)) {
			return BasicStroke.JOIN_MITER;
		} else if ("round".equals(join)) {
			return BasicStroke.JOIN_ROUND;
		} else if ("bevel".equals(join)) {
			return BasicStroke.JOIN_BEVEL;
		}
		throw new IllegalArgumentException("Join not supported " + join);
	}

	@Override
	public void render(Graphics2D g2d, ContextFreeArea area) {
		if (path != null) {
			state.draw(g2d, area, path, s);
		}
	}
}