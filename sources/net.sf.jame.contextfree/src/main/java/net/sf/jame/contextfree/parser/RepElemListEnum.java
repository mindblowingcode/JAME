package net.sf.jame.contextfree.parser;

enum RepElemListEnum { 
	rule(8), replacement(4), mixed(3), command(2), op(1), empty(0); 
	
	private int type;
	
	private RepElemListEnum(int type) { 
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public static RepElemListEnum typeByOrdinal(int ordinal) {
		for (RepElemListEnum type : RepElemListEnum.values()) {
			if (type.ordinal() == ordinal) {
				return type;
			}
		}
		return empty;
	}
}