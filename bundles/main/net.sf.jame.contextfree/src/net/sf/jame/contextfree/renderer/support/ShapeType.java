package net.sf.jame.contextfree.renderer.support;

public class ShapeType {
	private String name;
	private int shapeType;
	private boolean hasRules;

	public ShapeType(String name, int shapeType, boolean hasRules) {
		this.name = name;
		this.shapeType = shapeType;
		this.hasRules = hasRules;
	}

	public final String getName() {
		return name;
	}

	public final int getShapeType() {
		return shapeType;
	}

	public final boolean hasRules() {
		return hasRules;
	}
}
