/**
 * 
 */
package net.sf.jame.contextfree.renderer.support;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeShape;
import net.sf.jame.contextfree.renderer.ContextFreeState;

public class SolidPathShape extends ContextFreeShape {
	private ExtendedGeneralPath path;
	private ContextFreeState state;
	private int r;

	public SolidPathShape(ContextFreeState state, String rule) {
		super(state.getZ());
		this.state = state;
		path = state.getPath();
		r = getRule(rule);
	}

	private int getRule(String rule) {
		if ("even-odd".equals(rule)) { 
			return Path2D.WIND_EVEN_ODD;
		} else if ("non-zero".equals(rule)) { 
			return Path2D.WIND_NON_ZERO;
		}
		throw new IllegalArgumentException("Rule not supported " + rule);
	}

	@Override
	public void render(Graphics2D g2d, ContextFreeArea area) {
		if (path != null) {
			state.fill(g2d, area, path, r);
		}
	}
}