package net.sf.jame.contextfree.parser;

class ASTTransform extends ASTReplacement {
	public ASTTransform(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
	}

	public ASTTransform(ASTExpression expression) {
		super(null, null, expression);
	}
	
	public ASTRepContainer getBody() {
		return null;//TODO
	}
}
