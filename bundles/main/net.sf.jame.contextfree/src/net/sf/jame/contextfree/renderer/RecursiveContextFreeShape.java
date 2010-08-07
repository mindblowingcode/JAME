/**
 * 
 */
package net.sf.jame.contextfree.renderer;

public class RecursiveContextFreeShape extends DefaultContextFreeShape {
	private final ContextFreeContext context;
	private final ContextFreeState state;
	private final ContextFreeBounds bounds;
	private final String shape;
	
	public RecursiveContextFreeShape(ContextFreeContext context, ContextFreeState state, ContextFreeBounds bounds, String shape) {
		super(state.getZ());
		this.context = context;
		this.state = state;
		this.bounds = bounds;
		this.shape = shape;
	}

	public boolean expand() {
		context.expandRule(this, state, bounds, shape);
		return true;
	}
}