package net.sf.jame.contextfree.renderer;

public interface ContextFreeRule {
	public String getName();
	
	public Float getProbability();
	
	public void prepare(ContextFreeContext contextFreeContext);
	
	public void draw(ContextFreeContext contextFreeContext);
}
