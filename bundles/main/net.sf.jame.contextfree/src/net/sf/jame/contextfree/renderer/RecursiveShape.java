/**
 * 
 */
package net.sf.jame.contextfree.renderer;

public class RecursiveShape extends ContextFreeShape {
	private final ContextFreeContext context;
	private final ContextFreeState state;
	private final ContextFreeBounds globalBounds;
	private final ContextFreeBounds shapeBounds;
	private final String shape;
	
	public RecursiveShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds globalBounds, ContextFreeBounds shapeBounds, String shape) {
		super(state.getZ());
		this.context = context;
		this.state = state;
		this.globalBounds = globalBounds;
		this.shapeBounds = shapeBounds;
		this.shape = shape;
	}

	public boolean expand() {
		context.expandRule(this, state, globalBounds, shapeBounds, shape);
		return true;
	}
}