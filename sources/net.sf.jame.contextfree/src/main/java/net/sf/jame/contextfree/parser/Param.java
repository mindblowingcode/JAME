package net.sf.jame.contextfree.parser;

enum Param {
	Color(1), Alpha(2), Time(4), FrameTime(8);
	
	private int ordinal;

	private Param(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}
}