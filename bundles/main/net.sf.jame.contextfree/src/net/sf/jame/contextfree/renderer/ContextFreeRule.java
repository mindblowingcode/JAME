package net.sf.jame.contextfree.renderer;

public interface ContextFreeRule {
	public String getName();
	
	public Float getProbability();
	
	public void createShapes(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds);
}
