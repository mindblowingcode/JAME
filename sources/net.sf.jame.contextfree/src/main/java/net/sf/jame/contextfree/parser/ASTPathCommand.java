package net.sf.jame.contextfree.parser;

class ASTPathCommand extends ASTReplacement {
	public ASTPathCommand(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
		// TODO Auto-generated constructor stub
	}

	public ASTPathCommand(String op, ASTExpression modification) {
		// TODO Auto-generated constructor stub
		super(null, null, modification);
	}

	public ASTPathCommand(String s, ASTModification modification, ASTExpression params) {
		// TODO Auto-generated constructor stub
		super(null, null, modification);
	}
}