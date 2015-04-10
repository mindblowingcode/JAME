package net.sf.jame.contextfree.parser;

class ASTIf extends ASTReplacement {
	public ASTIf(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
	}

	public ASTIf(ASTExpression expression) {
		super(null, null, expression);
	}
	
	public ASTRepContainer getThenBody() {
		return null;//TODO
	}
	
	public ASTRepContainer getElseBody() {
		return null;//TODO
	}
}
