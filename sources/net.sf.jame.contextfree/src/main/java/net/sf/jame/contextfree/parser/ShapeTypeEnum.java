package net.sf.jame.contextfree.parser;

enum ShapeTypeEnum {
	NewShape(0), RuleType(1), PathType(2);
	
	private int ordinal;

	private ShapeTypeEnum(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}
}