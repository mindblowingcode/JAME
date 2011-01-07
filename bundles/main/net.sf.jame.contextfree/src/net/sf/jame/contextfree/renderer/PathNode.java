package net.sf.jame.contextfree.renderer;

public class PathNode extends ExpansionNode {
	public PathNode(ReplacementNode parent, String name, float size) {
		super(parent, name, size);
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
