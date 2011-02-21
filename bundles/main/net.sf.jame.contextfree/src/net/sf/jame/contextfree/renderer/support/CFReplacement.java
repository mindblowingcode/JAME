package net.sf.jame.contextfree.renderer.support;

public class CFReplacement implements Cloneable {
	private CFModification stateChange;

	public CFReplacement(CFModification stateChange) {
		this.stateChange = stateChange;
	}

	public CFModification getStateChange() {
		return stateChange;
	}

	@Override
	public CFReplacement clone() {
		CFReplacement rep = new CFReplacement(stateChange.clone());
		return rep;
	}
}
