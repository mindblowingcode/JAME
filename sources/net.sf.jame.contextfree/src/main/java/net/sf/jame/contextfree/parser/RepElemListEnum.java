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
		switch (ordinal) {
			case 8:
				return rule; 
			case 4:
				return replacement; 
			case 3:
				return mixed; 
			case 2:
				return command; 
			case 1:
				return op; 
			case 0:
				return empty; 
		}
		return empty;
	}
}