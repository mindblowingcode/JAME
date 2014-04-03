package net.sf.jame.contextfree.parser;

import java.util.List;

class ASTStartSpecifier extends ASTRuleSpecifier {
	public ASTStartSpecifier(int shapeType, String name, ASTExpression arguments, List<ASTParameter> typeSignature,	List<ASTParameter> parent) {
		super(shapeType, name, arguments, typeSignature, parent);
	}

	public ASTStartSpecifier(ASTRuleSpecifier rule, ASTModification mod) {
		super(rule);
		// TODO Auto-generated constructor stub
	}

	public ASTStartSpecifier(ASTExpression exp, ASTModification mod) {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public ASTStartSpecifier(int nameIndex, String name, ASTExpression args, ASTModification mod) {
		super(null);
		// TODO Auto-generated constructor stub
	}
}