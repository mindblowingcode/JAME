package net.sf.jame.contextfree.renderer.support;

public class CFFinishedShape implements Cloneable {
	private CFModification worldState;
	private CFPathAttributes attributes;
	private CFPath path;

	public CFFinishedShape(CFPath path, CFPathAttributes attributes, CFModification worldState) {
		this.path = path;
		this.attributes = attributes;
		this.worldState = worldState;
	}

	public CFPathAttributes getAttributes() {
		return attributes;
	}

	public CFPath getPath() {
		return path;
	}

	public CFModification getWorldState() {
		return worldState;
	}
	
	@Override
	public CFFinishedShape clone() {
		return new CFFinishedShape(path.clone(), attributes.clone(), worldState.clone());
	}
}
