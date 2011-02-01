package net.sf.jame.contextfree.renderer.support;

import java.awt.Graphics2D;

import net.sf.jame.contextfree.renderer.ContextFreeArea;
import net.sf.jame.contextfree.renderer.ContextFreeState;

public final class FlushPathShape extends FinishedShape {
	private ContextFreeState state;

	public FlushPathShape(ContextFreeState state) {
		super(state.getZ());
		this.state = state;
	}
	
	public void render(Graphics2D g2d, ContextFreeArea area) {
		state.flush(g2d, area);
	}
}
