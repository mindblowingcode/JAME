package net.sf.jame.contextfree.renderer.support;

import java.util.ArrayList;
import java.util.List;

public class CFShape implements Cloneable {
	private List<CFReplacement> replacements = new ArrayList<CFReplacement>();
	private CFModification worldState;
	private String name;
	private double area;

	public CFShape(String name, CFModification worldState) {
		this.name = name;
		this.worldState = worldState;
		area = worldState.getTransform().getDeterminant();
	}

	public String getName() {
		return name;
	}

	public void addReplacement(CFReplacement replacement) {
		replacements.add(replacement);
	}
	
	public int getReplacementCount() {
		return replacements.size();
	}
	
	public CFReplacement getReplacement(int index) {
		return replacements.get(index);
	}
	
	public CFModification getWorldState() {
		return worldState;
	}
	
	public double area() {
		return area;
	}

	@Override
	public CFShape clone() {
		CFShape s = new CFShape(name, worldState.clone());
		for (int rep = 0; rep < replacements.size(); rep++) {
			s.addReplacement(replacements.get(rep).clone());
		}
		return s;
	}
}
