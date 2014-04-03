package net.sf.jame.contextfree.parser;

enum LocalityType {
	UnknownLocal(0), ImpureNonlocal(1), PureNonlocal(3), PureLocal(7);
	
	private int ordinal;

	private LocalityType(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}
}