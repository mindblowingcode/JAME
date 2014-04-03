package net.sf.jame.contextfree.parser;

import java.util.List;

class ASTExpression {
	protected boolean isConstant;
	protected ExpType type;

	public ASTExpression() {
		this(false, ExpType.NoType);
	}

	public ASTExpression(boolean isConstant) {
		this(isConstant, ExpType.NoType);
	}

	public ASTExpression(boolean isConstant, ExpType type) {
		this.isConstant = isConstant;
		this.type = type;
	}

	public boolean isConstant() {
		return isConstant;
	}

	public ExpType getType() {
		return type;
	}

	public int flatten(List<ASTExpression> dest) {
		dest.add(this);
		return 1;
	}

	public void entropy(StringBuilder e) {
	}

	public ASTExpression simplify() {
		return this;
	}

	public ASTExpression current() {
		return this;
	}

	public ASTExpression next() {
		return null;
	}

	public int evaluate(double[] result, int length, RTI rti) {
		return 0;
	}

	public int evaluate(Modification[] result, int length, RTI rti) {
		return 0;
	}

	public void evaluate(Modification modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) {
		throw new RuntimeException("Cannot convert this expression into an adjustment"); 
	}

	public ASTStackType evalArgs(ASTStackType parent, RTI rti) {
		throw new RuntimeException("Cannot convert this expression into a shape"); 
	}
	
	public ASTExpIterator begin() {
		return new ASTExpIterator(this); 
	}
	
	public ASTExpIterator end() {
		return new ASTExpIterator();
	}

	public List<ASTExpression> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setType(ExpType type) {
		this.type = type;
	}

	public ASTExpression append(ASTExpression args) {
		// TODO Auto-generated method stub
		return null;
	}
}