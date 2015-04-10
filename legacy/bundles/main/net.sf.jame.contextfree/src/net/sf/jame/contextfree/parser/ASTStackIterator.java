package net.sf.jame.contextfree.parser;


class ASTStackIterator {
	private ASTExpIterator iter;
	private ASTExpIterator iter_end;
	private ASTStackType[] s;
	private int index;
	
	public ASTStackIterator(ASTStackType[] s) {
		this.s = s;
		if (s != null && s[index] != null && s[index].getRuleHeader().getParamCount() > 0) {
			iter = s[index + 1].getTypeInfo().begin();
			iter_end = s[index + 1].getTypeInfo().end();
			index += 2;
		}
	}
	
	public ASTStackIterator() {
		this(null);
	}
	
	public void next() {
		if (s != null) {
			index += ((ASTParameter) iter.getExpression()).getTupleSize();
			iter.next();
			if (iter.getExpression() == iter_end.getExpression()) {
				s = null;
				index = 0;
			}
		}
	}
	
	public ASTStackType current() {
		if (s != null) {
			return s[index];
		}
		return null;
	}
}
