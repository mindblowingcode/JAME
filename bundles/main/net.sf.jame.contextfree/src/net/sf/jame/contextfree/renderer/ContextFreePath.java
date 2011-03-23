package net.sf.jame.contextfree.renderer;

import net.sf.jame.contextfree.renderer.support.CFShape;

public interface ContextFreePath {
	public String getName();

	public void process(ContextFreeContext context, CFShape shape);
}
