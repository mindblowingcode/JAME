package net.sf.jame.contextfree.parser;


class ASTReplacement {
	private EPathOp pathOp;
	private ERepElemType repType;
	private String name;
	private ASTModification modification;
	private ASTRuleSpecifier ruleSpecifier;
	
	public ASTReplacement(ASTRuleSpecifier ruleSpecifier, String name, ASTModification modification, ERepElemType repType) {
		this.name = name;
		this.modification = modification;
		this.ruleSpecifier = ruleSpecifier;
		this.repType = repType;
	}
	
	public ASTReplacement(ASTRuleSpecifier ruleSpecifier, String name, ASTModification modification) {
		this.name = name;
		this.modification = modification;
		this.ruleSpecifier = ruleSpecifier;
		this.repType = ERepElemType.replacement;
	}
	
	public ASTReplacement(ASTRuleSpecifier ruleSpecifier, ASTModification mods,
			ERepElemType t) {
		// TODO Auto-generated constructor stub
	}

	public ASTReplacement(ASTRuleSpecifier ruleSpecifier, ASTModification mods) {
		this(ruleSpecifier, mods, ERepElemType.empty);
	}

	public void replace(Shape s, double[] width) {}
	
 	public void replaceShape(Shape s) {}

	public ASTRuleSpecifier getRuleSpecifier() {
		return ruleSpecifier;
	}

	public EPathOp getPathOp() {
		return pathOp;
	}

	public ERepElemType getRepType() {
		return repType;
	}
	
	public void setRepType(ERepElemType repType) {
		this.repType = repType;
	}

	public String getName() {
		return name;
	}

	public void unify() {
	}

	public void setPathOp(EPathOp pathOp) {
		this.pathOp = pathOp;
	}

	public ASTModification getChildChange() {
		return modification;
	}
}