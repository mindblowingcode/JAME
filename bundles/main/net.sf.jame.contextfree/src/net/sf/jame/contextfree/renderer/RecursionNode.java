package net.sf.jame.contextfree.renderer;

public class RecursionNode extends ExpansionNode {
	public RecursionNode(ReplacementNode parent, String name, float size) {
		super(parent, name, size);
	}

	@Override
	public boolean isValid() {
		return getSize() < 1f;
//		ExpansionNode node = getParent();
//		while (node != null) {
//			if (getName().equals(node.getName())) {
//				if (getSize() < node.getSize()) {
//					return true;
//				}
//			}
//			node = node.getParent();
//		}
//		return false;
	}
}
