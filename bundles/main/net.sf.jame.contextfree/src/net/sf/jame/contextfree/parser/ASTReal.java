package net.sf.jame.contextfree.parser;


class ASTReal extends ASTExpression {
	private double value;
	private String text;

	public ASTReal(float value) {
		super(true, ExpType.NumericType);
		this.value = value;
	}

	public ASTReal(double value) {
		super(true, ExpType.NumericType);
		this.value = value;
	}

	public ASTReal(float value, boolean negative) {
		super(true, ExpType.NumericType);
		if (negative) {
			this.value = -value;
		} else {
			this.value = +value;
		}
	}

	public ASTReal(double value, boolean negative) {
		super(true, ExpType.NumericType);
		if (negative) {
			this.value = -value;
		} else {
			this.value = +value;
		}
	}

	public ASTReal(String text, boolean negative) {
		super(true, ExpType.NumericType);
		this.text = text;
		if (negative) {
			this.value = -Double.parseDouble(text);
		} else {
			this.value = +Double.parseDouble(text);
		}
	}

	@Override
	public void entropy(StringBuilder e) {
		e.append(text);
	}

	@Override
	public int evaluate(double[] result, int offset, int length, RTI rti) {
		if (result != null && length != 1)
			return -1;

		if (result != null)
			result[offset + 0] = value;
		
		return 1;
	}
}