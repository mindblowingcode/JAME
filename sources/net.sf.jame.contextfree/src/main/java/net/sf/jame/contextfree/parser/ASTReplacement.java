package net.sf.jame.contextfree.parser;


class ASTReplacement {
	private PathOpEnum pathOp;
	private RepElemListEnum repType;
	private String name;
	private ASTExpression modification;
	private ASTRuleSpecifier ruleSpecifier;
	
	public ASTReplacement(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification, RepElemListEnum repType) {
		this.name = name;
		this.modification = modification;
		this.ruleSpecifier = ruleSpecifier;
		this.repType = repType;
	}
	
	public ASTReplacement(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		this.name = name;
		this.modification = modification;
		this.ruleSpecifier = ruleSpecifier;
		this.repType = RepElemListEnum.replacement;
	}
	
	public void replace(Shape s, double[] width) {}
	
 	public void replaceShape(Shape s) {}

	public ASTRuleSpecifier getRuleSpecifier() {
		return ruleSpecifier;
	}

	public PathOpEnum getPathOp() {
		return pathOp;
	}

	public RepElemListEnum getRepType() {
		return repType;
	}

	public String getName() {
		return name;
	}

	public ASTExpression getModification() {
		return modification;
	}

	public void unify() {
	}
}