/**
 * 
 */
package net.sf.jame.contextfree.renderer.support;

import net.sf.jame.contextfree.renderer.ContextFreeBounds;
import net.sf.jame.contextfree.renderer.ContextFreeContext;
import net.sf.jame.contextfree.renderer.ContextFreeState;

public class UnfinishedShape {
	private final ContextFreeContext context;
	private final ContextFreeState state;
	private final ContextFreeBounds globalBounds;
	private final ContextFreeBounds shapeBounds;
	private final String shapeName;
	
	public UnfinishedShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds, String shapeName) {
		this.context = context;
		this.state = state;
		this.globalBounds = globalBounds;
		this.shapeBounds = shapeBounds;
		this.shapeName = shapeName;
	}

	public boolean expand() {
		context.expandRule(state, globalBounds, shapeBounds, shapeName);
		return true;
	}
}