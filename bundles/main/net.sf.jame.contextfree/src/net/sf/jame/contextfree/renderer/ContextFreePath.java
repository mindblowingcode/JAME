package net.sf.jame.contextfree.renderer;

public interface ContextFreePath {
	public String getName();

	public void createShapes(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds);
}
