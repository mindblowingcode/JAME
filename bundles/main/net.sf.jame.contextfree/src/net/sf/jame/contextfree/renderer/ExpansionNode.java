package net.sf.jame.contextfree.renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpansionNode {
	private List<ExpansionNode> children = new ArrayList<ExpansionNode>();
	private ExpansionNode parent;
	private String name;
	private float size;
	private float probability;

	protected ExpansionNode(ExpansionNode parent, String name, float size) {
		this.parent = parent;
		this.name = name;
		this.size = size;
	}
	
	public ExpansionNode getParent() {
		return parent;
	}
	
	public String getName() {
		return name;
	}
	
	public float getSize() {
		return size;
	}
	
	public void addChild(ExpansionNode node) {
		if (node == null) {
			return;
		}
		children.add(node);
	}
	
	public void removeChild(ExpansionNode node) {
		if (node == null) {
			return;
		}
		children.remove(node);
	}
	
	public int getChildCount() {
		return children.size();
	}
	
	public ExpansionNode getChild(int index) {
		return children.get(index);
	}
	
	public abstract boolean isValid();
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		dump(builder, "");
		return builder.toString();
	}
	
	private void dump(StringBuilder builder, String prefix) {
		builder.append(prefix);
		builder.append("Type = ");
		builder.append(getClass().getSimpleName());
		builder.append("\n");
		builder.append(prefix);
		builder.append("Name = ");
		builder.append(name);
		builder.append("\n");
		builder.append(prefix);
		builder.append("Size = ");
		builder.append(size);
		builder.append("\n");
		builder.append(prefix);
		builder.append("{\n");
		for (ExpansionNode child : children) {
			child.dump(builder, prefix + "\t");
		}
		builder.append(prefix);
		builder.append("}\n");
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}
}
