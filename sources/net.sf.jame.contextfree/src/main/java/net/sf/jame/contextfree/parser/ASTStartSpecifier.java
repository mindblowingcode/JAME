package net.sf.jame.contextfree.parser;

class ASTStartSpecifier extends ASTRuleSpecifier {
	private ASTModification modification;
	
	public ASTStartSpecifier(int nameIndex, String name, ASTExpression args, ASTModification mod) {
		super(nameIndex, name, args, null);
		this.modification = mod;
	}
	
	public ASTStartSpecifier(int nameIndex, String name, ASTModification mod) {
		super(nameIndex, name, null, null);
		this.modification = mod;
	}
	
	public ASTStartSpecifier(ASTRuleSpecifier rule, ASTModification mod) {
		super(rule);
		this.modification = mod;
	}

	public ASTStartSpecifier(ASTExpression exp, ASTModification mod) {
		super(exp);
		this.modification = mod;
	}

	public ASTModification getModification() {
		return modification;
	}

	@Override
	public ASTExpression simplify() {
		super.simplify();
		if (modification != null) {
			ASTExpression m = modification.simplify();
			assert(m == modification);
		}
		return this;
	}

	@Override
	public ASTExpression compile(ECompilePhase ph) {
		super.compile(ph);
		if (modification != null) {
			modification.compile(ph);
		}
		return null;
	}

	@Override
	public void entropy(StringBuilder e) {
		e.append(getEntropy());
		if (modification != null) {
			modification.entropy(e);
		}
	}
}