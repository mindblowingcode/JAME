package net.sf.jame.contextfree.parser;

enum Param {
	Color(1), Alpha(2), Time(4), FrameTime(8);
	
	private int type;

	private Param(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}