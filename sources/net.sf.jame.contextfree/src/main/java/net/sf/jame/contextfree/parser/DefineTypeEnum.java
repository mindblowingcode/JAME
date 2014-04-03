package net.sf.jame.contextfree.parser;

enum DefineTypeEnum {
	StackDefine(0), ConstDefine(1), ConfigDefine(2), FunctionDefine(4), LetDefine(8);
	
	private int ordinal;

	private DefineTypeEnum(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}
}