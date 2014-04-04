package net.sf.jame.contextfree.parser;


class ASTString extends ASTExpression {
	private String value;

	public ASTString(String value) {
		super(true, EExpType.StringType);
		this.value = value;
	}

	@Override
	public void entropy(StringBuilder e) {
		e.append(value);
	}

	@Override
	public int evaluate(double[] result, int length, RTI rti) {
		return 0;
	}
}