package net.sf.jame.contextfree.parser;

class ASTLoop extends ASTReplacement {
	public ASTLoop(ASTRuleSpecifier ruleSpecifier, String name, ASTExpression modification) {
		super(ruleSpecifier, name, modification);
	}

	public ASTLoop(ASTExpression arguments, ASTExpression modification) {
		super(null, "loop", modification);
	}

	public ASTLoop(int count, ASTExpression modification) {
		super(null, "loop", modification);
	}

	public ASTLoop(int nameIndex, String name, ASTExpression arguments, ASTExpression modification) {
		super(null, "loop", modification);
	}
	
	public ASTRepContainer getLoopBody() {
		return null;//TODO
	}
	
	public ASTRepContainer getFinallyBody() {
		return null;//TODO
	}
}