package net.sf.jame.contextfree.renderer.support;

import java.util.ArrayList;
import java.util.List;

public class CFRule extends CFRuleSpecifier {
	private CFPath path;
	private CFPathAttributes attributes;
	private List<CFReplacement> replacements = new ArrayList<CFReplacement>();
	
	public CFRule(int initialShapeType, double weight) {
		super(initialShapeType, weight);
	}

	public boolean hasPath() {
		return path != null;
	}
	
	public final CFPath getPath() {
		return path;
	}

	public final void setPath(CFPath path) {
		this.path = path;
	}

	public CFPathAttributes getPathAttributes() {
		return attributes;
	}

	public void setPathAttributes(CFPathAttributes attributes) {
		this.attributes = attributes;
	}

	public void addReplacement(CFReplacement replacement) {
		replacements.add(replacement);
	}
	
	public void removeReplacement(CFReplacement replacement) {
		replacements.remove(replacement);
	}
	
	public CFReplacement getReplacement(int index) {
		return replacements.get(index);
	}
	
	public int getReplacementCount() {
		return replacements.size();
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "CFRule [initialShapeType=" + initialShapeType + ", weight=" + weight + ", path=" + path + ", attributes=" + attributes + ", replacements=" + replacements + "]";
	}
}
