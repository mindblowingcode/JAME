package net.sf.jame.contextfree.renderer.support;

import java.util.ArrayList;
import java.util.List;

public class CFRule {
	private List<CFReplacement> replacements = new ArrayList<CFReplacement>();
	private CFPath path;
	private int ruleType;
	
	public CFRule(int ruleType) {
		this.ruleType = ruleType;
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

	public final CFPath getPath() {
		return path;
	}

	public final int getRuleType() {
		return ruleType;
	}

	public final void setPath(CFPath path) {
		this.path = path;
	}
}
