package net.sf.jame.contextfree.parser;

class ASTShape extends ASTReplacement {
	private ASTRepContainer rules;
	private boolean isPath;
	private int nameIndex;
	
	public ASTShape(ASTRuleSpecifier ruleSpecifier, boolean isPath) {
		super(ruleSpecifier, "shape", null);
		this.isPath = isPath;
		this.rules.isGlobal = true;
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
