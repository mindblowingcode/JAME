package net.sf.jame.contextfree.parser;

enum ELocalityType {
	UnknownLocal(0), ImpureNonlocal(1), PureNonlocal(3), PureLocal(7);
	
	private int type;

	private ELocalityType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}