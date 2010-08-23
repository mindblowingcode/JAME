package net.sf.jame.contextfree.renderer;

public interface ContextFreePath {
	public String getName();

	public ContextFreeShape createShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds);
}
