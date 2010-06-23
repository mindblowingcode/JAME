package net.sf.jame.contextfree.renderer;

public interface ContextFreePath {
	public String getName();

	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits);
}
