package net.sf.jame.contextfree.renderer;

public interface ContextFreePath {
	public String getName();

	public void prepare(ContextFreeContext contextFreeContext);

	public void draw(ContextFreeContext contextFreeContext);
}
