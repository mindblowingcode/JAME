package net.sf.jame.contextfree.parser;

import java.util.Map;

class ASTSwitch extends ASTReplacement {
	public ASTSwitch(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
	}

	public ASTSwitch(ASTExpression caseVal) {
		this(null, null, null);
	}

	public ASTRepContainer getElseBody() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, ASTRepContainer> getCaseStatements() {
		// TODO Auto-generated method stub
		return null;
	}
}