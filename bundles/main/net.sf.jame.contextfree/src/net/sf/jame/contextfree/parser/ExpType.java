package net.sf.jame.contextfree.parser;

enum ExpType {
	NoType(0), NumericType(1), ModificationType(2), RuleType(4), FlagType(8);
	
	private int ordinal;

	private ExpType(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}
}