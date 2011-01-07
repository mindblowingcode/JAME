package net.sf.jame.contextfree.renderer;

public class RuleNode extends ReplacementNode {
	public RuleNode(ReplacementNode parent, String name, float size) {
		super(parent, name, size);
	}

	@Override
	public boolean isValid() {
		for (int i = 0; i < getChildCount(); i++) {
			if (getChild(i).getProbability() / getProbability() > 0.001 && getChild(i).isValid()) {
				return true;
			}
		}
		return false;
	}
}
