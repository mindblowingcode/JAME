package net.sf.jame.contextfree.renderer;

public class ReplacementNode extends ExpansionNode {
	public ReplacementNode(ReplacementNode parent, String name, float size) {
		super(parent, name, size);
	}

	@Override
	public boolean isValid() {
		if (getChildCount() == 0) {
			return true;
		}
		for (int i = 0; i < getChildCount(); i++) {
			if (!getChild(i).isValid()) {
				return false;
			}
		}
		return true;
	}
}
