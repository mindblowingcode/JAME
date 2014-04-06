package net.sf.jame.contextfree.parser;


class ASTRule extends ASTReplacement {
	private ASTRepContainer ruleBody = new ASTRepContainer();
	private ASTCompiledPath cachedPath;
	private float weight;
	private boolean isPath;
	private int nameIndex;
	private EWeightType weightType;
	
	public ASTRule(int nameIndex, float weight, boolean percent) {
		super(null, ERepElemType.rule);
		this.nameIndex = nameIndex;
		this.isPath = false;
		this.weight = weight <= 0.0 ? 1.0f : weight;
		this.weightType = percent ? EWeightType.PercentWeight : EWeightType.ExplicitWeight;
		this.cachedPath = null;
	}

	public ASTRule(int nameIndex) {
		super(null, ERepElemType.rule);
		this.nameIndex = nameIndex;
		this.isPath = false;
		this.weight = 1.0f;
		this.weightType = EWeightType.NoWeight;
		this.cachedPath = null;
	}

	protected ASTRule(int nameIndex, boolean dummy) {
		super(null, ERepElemType.rule);
		this.nameIndex = nameIndex;
		this.isPath = true;
		this.weight = 1.0f;
		this.weightType = EWeightType.NoWeight;
		this.cachedPath = null;
		
		//TODO completare
	}
	
	public ASTRepContainer getRuleBody() {
		return ruleBody;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public EWeightType getWeightType() {
		return weightType;
	}
	
	public boolean isPath() {
		return isPath;
	}
	
	public void setPath(boolean isPath) {
		this.isPath = isPath;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	@Override
	public void compile(ECompilePhase ph) {
	}

	@Override
	public void traverse(Shape parent, boolean tr, RTI rti) {
	}
	
	public void traversePath(Shape parent, RTI rti) {
	}
}