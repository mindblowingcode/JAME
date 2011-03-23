package net.sf.jame.contextfree.renderer.support;

public class CFReplacement implements Cloneable {
	private String name;
	private int loopCount;
	private CFModification stateChange;

	public CFReplacement(String name, int loopCount, CFModification stateChange) {
		this.name = name;
		this.loopCount = loopCount;
		this.stateChange = stateChange;
	}
	
	public String getName() {
		return name;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public CFModification getStateChange() {
		return stateChange;
	}

	@Override
	public CFReplacement clone() {
		return new CFReplacement(name, loopCount, stateChange);
	}
}
