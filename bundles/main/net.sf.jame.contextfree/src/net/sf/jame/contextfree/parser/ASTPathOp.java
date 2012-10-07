package net.sf.jame.contextfree.parser;

class ASTPathOp extends ASTReplacement {
	public ASTPathOp(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
		// TODO Auto-generated constructor stub
	}

	public ASTPathOp(String op, ASTExpression modification, boolean flag) {
		// TODO Auto-generated constructor stub
		super(null, null, modification);
	}
}