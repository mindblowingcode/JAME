package net.sf.jame.contextfree.parser;


class ASTRule extends ASTReplacement {
	private boolean isPath;
	private String name;
	private float weight;
	private int nameIndex;
	private WeightType weightType;
	private ASTRepContainer ruleBody;
	
	public ASTRule(int nameIndex) {
		super(null, "", null);//TODO completare
		this.nameIndex = nameIndex;
		this.name = "";
		this.weight = weight <= 0.0 ? 1.0f : weight;
	}
	
	public ASTRule(String name, float weight, boolean percent) {
		super(null, "", null);//TODO completare
		this.nameIndex = -1;
		this.name = name;
		this.weight = weight <= 0.0 ? 1.0f : weight;
	}
	
	public ASTRule(int nameIndex, float weight, boolean percent) {
		super(null, "", null);//TODO completare
		this.nameIndex = nameIndex;
		this.name = "";
		this.weight = weight <= 0.0 ? 1.0f : weight;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public ASTRepContainer getRuleBody() {
		return ruleBody;
	}
	
	public void setPath(boolean isPath) {
		this.isPath = isPath;
	}

	public boolean isPath() {
		return isPath;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public WeightType getWeightType() {
		return weightType;
	}
}