package net.sf.jame.contextfree.parser;

class ASTLet extends ASTExpression {
	public ASTLet(ASTRepContainer vars, ASTDefine def) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void entropy(StringBuilder e) {
        e.append("\u00E8\u00E9\u00F6\u007E\u001A\u00F1");
	}

	@Override
	public int evaluate(double[] result, int length, RTI rti) {
		return 0;
	}

	@Override
	public ASTExpression simplify() {
		return this;
	}
}