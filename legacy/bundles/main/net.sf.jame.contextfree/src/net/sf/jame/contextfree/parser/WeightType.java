package net.sf.jame.contextfree.parser;

enum WeightType { 
	NoWeight(1), PercentWeight(2), ExplicitWeight(4);
	
	private int type;

	private WeightType(int type) { 
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
