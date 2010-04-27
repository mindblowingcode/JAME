package net.sf.jame.contextfree.renderer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class ContextFreeNode {
	private List<ContextFreeNode> childList = new LinkedList<ContextFreeNode>();
	private ContextFreeShape shape;
	private ContextFreeState state;
	
	public ContextFreeNode(ContextFreeShape shape, ContextFreeState state) {
		this.shape = shape;
		this.state = state;
	}
	
	public void addChild(ContextFreeNode child) {
		childList.add(child);
	}
	
	public void removeChild(ContextFreeNode child) {
		childList.remove(child);
	}

	public void prepare(Dimension size, ContextFreeLimits limits) {
		if (shape != null) {
			shape.prepare(size, limits);
		}
		for (ContextFreeNode child : childList) {
			child.prepare(size, limits);
		}
	}
	
	public void draw(Graphics2D g2d, ContextFreeArea area) {
		if (shape != null) {
			shape.draw(g2d, area, state);
		}
		for (ContextFreeNode child : childList) {
			child.draw(g2d, area);
		}
	}
}
