package net.sf.jame.contextfree.parser;

class ASTReal extends ASTExpression {
	private double value;
	private String text;

	public ASTReal(double value) {
		super(true, false, EExpType.NumericType);
		this.value = value;
		isNatural = Math.floor(value) == value && value >= 0 && value < 9007199254740992.0;
		locality = ELocality.PureLocal;
	}

	public ASTReal(String text, boolean negative) {
		super(true, false, EExpType.NumericType);
		if (negative) {
			this.text = "-" + text;
			this.value = Double.parseDouble(text);
		} else {
			this.text = text;
			this.value = Double.parseDouble(text);
		}
		isNatural = Math.floor(value) == value && value >= 0 && value < 9007199254740992.0;
		locality = ELocality.PureLocal;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void entropy(StringBuilder e) {
		e.append(text);
	}

	@Override
	public int evaluate(double[] result, int length, RTI rti) {
		if (result != null && length < 1) {
			return -1;
		}
		if (result != null) {
			result[0] = value;
		}
		return 1;
	}
}