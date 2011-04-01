package net.sf.jame.contextfree.renderer.support;

public class CFFinishedShape implements Cloneable {
	private CFModification modification;
	private CFPathAttributes attributes;
	private CFPath path;

	public CFFinishedShape(CFPath path, CFPathAttributes attributes, CFModification modification) {
		this.path = path;
		this.attributes = attributes;
		this.modification = modification;
	}

	public CFPathAttributes getAttributes() {
		return attributes;
	}

	public CFPath getPath() {
		return path;
	}

	public CFModification getModification() {
		return modification;
	}
	
	@Override
	public CFFinishedShape clone() {
		return new CFFinishedShape(path.clone(), attributes.clone(), modification.clone());
	}
}
