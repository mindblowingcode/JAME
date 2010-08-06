package net.sf.jame.contextfree.renderer;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public abstract class ContextFreeNode {
	private List<ContextFreeNode> childList = new LinkedList<ContextFreeNode>();
	
	public ContextFreeNode() {
	}

	protected final void addChild(ContextFreeNode child) {
		childList.add(child);
	}
	
	protected final void removeChild(ContextFreeNode child) {
		childList.remove(child);
	}

	protected void drawNode(Graphics2D g2d, ContextFreeArea area) {
	}

	public final void draw(Graphics2D g2d, ContextFreeArea area) {
		drawNode(g2d, area);
		for (ContextFreeNode child : childList) {
			child.draw(g2d, area);
		}
	}

	protected boolean expandNode() {
		return false;
	}

	public final boolean expand() {
		boolean expanded = expandNode();
		for (ContextFreeNode child : childList) {
			expanded |= child.expand();
		}
		return expanded;
	}
}
