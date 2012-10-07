package net.sf.jame.contextfree.parser;


class ASTExpIterator {
	private ASTExpression expression;
	
	public ASTExpIterator(ASTExpression expression) {
		this.expression = expression;
	}
	
	public ASTExpIterator() {
	}
	
	public void next() {
		if (expression != null) {
			expression = expression.next();
		}
	}

	public ASTExpression getExpression() {
		return expression;
	}
}