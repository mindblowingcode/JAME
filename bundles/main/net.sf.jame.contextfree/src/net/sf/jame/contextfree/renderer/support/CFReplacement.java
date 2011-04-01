package net.sf.jame.contextfree.renderer.support;

public class CFReplacement implements Cloneable {
	private int shapeType;
	private int loopCount;
	private CFModification modification;

	public CFReplacement(int shapeType, int loopCount, CFModification modification) {
		this.shapeType = shapeType;
		this.loopCount = loopCount;
		this.modification = modification;
	}
	
	public final int getShapeType() {
		return shapeType;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public CFModification getModification() {
		return modification;
	}

	@Override
	public CFReplacement clone() {
		return new CFReplacement(shapeType, loopCount, modification);
	}
}
