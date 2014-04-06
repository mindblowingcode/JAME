package net.sf.jame.contextfree.parser;

class ASTShape extends ASTReplacement {
	private ASTRepContainer rules;
	private boolean isPath;
	private int nameIndex;
	
	public ASTShape(ASTRuleSpecifier ruleSpecifier, boolean isPath) {
		super(null, null, null, ERepElemType.rule);
		this.isPath = isPath;
	}

	public ASTRepContainer getRules() {
		return rules;
	}

	public boolean isPath() {
		return isPath;
	}

	public int getNameIndex() {
		return nameIndex;
	}
}