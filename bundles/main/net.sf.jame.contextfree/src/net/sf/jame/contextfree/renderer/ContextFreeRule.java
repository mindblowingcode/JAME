package net.sf.jame.contextfree.renderer;

import net.sf.jame.contextfree.renderer.support.CFShape;

public interface ContextFreeRule {
	public String getName();
	
	public Float getProbability();
	
	public void process(ContextFreeContext context, CFShape shape);
}
