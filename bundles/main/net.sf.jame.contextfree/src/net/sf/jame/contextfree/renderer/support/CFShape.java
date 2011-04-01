package net.sf.jame.contextfree.renderer.support;


public class CFShape implements Cloneable {
	private CFModification modification;
	private int initialShapeType;
	private double area;

	public CFShape(int initialShapeType, CFModification modification) {
		this.modification = modification;
		this.initialShapeType = initialShapeType;
		area = modification.getTransform().getDeterminant();
	}

	public final int getInitialShapeType() {
		return initialShapeType;
	}

	public CFModification getModification() {
		return modification;
	}
	
	public double area() {
		return area;
	}

	@Override
	public CFShape clone() {
		CFShape s = new CFShape(initialShapeType, modification.clone());
		return s;
	}

	public void replace(CFReplacement replacement) {
		initialShapeType = replacement.getShapeType();
		modification.add(replacement.getModification());
	}
}
