package net.sf.jame.contextfree.parser;

class ASTDefine extends ASTReplacement {
	public ASTDefine(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
	}
}