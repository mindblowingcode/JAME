package net.sf.jame.contextfree.renderer;

public interface ContextFreeRule {
	public String getName();
	
	public Float getProbability();
	
	public ContextFreeNode buildNode(ContextFreeContext context, ContextFreeState state, ContextFreeLimits limits);
}
