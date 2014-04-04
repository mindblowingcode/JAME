package net.sf.jame.contextfree.parser;

enum ExpType {
	NoType(0), NumericType(1), ModificationType(2), RuleType(4), FlagType(8), StringType(16), ReuseType(32);
	
	private int type;

	private ExpType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}