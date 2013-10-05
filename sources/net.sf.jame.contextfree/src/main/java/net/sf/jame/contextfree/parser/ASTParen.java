package net.sf.jame.contextfree.parser;


class ASTParen extends ASTExpression {
	private ASTExpression expression;
	
	public ASTParen(ASTExpression expression) {
		super(expression.isConstant, expression.getType());
		this.expression = expression;
	}

	@Override
	public void entropy(StringBuilder e) {
        expression.entropy(e);
        e.append("\u00E8\u00E9\u00F6\u007E\u001A\u00F1");
	}

	@Override
	public int evaluate(double[] result, int offset, int length, RTI rti) {
        if (type != ExpType.NumericType) {
            throw new RuntimeException("Non-numeric/flag expression in a numeric/flag context");
        }
        return expression.evaluate(result, offset, length, rti);
	}

	@Override
	public ASTExpression simplify() {
		ASTExpression e = expression.simplify();
		expression = null;
		return e;
	}
}