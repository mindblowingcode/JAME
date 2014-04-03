package net.sf.jame.contextfree.parser;

import java.util.List;


class ASTCons extends ASTExpression {
	private ASTExpression left;
	private ASTExpression right;

	public ASTCons(ASTExpression left, ASTExpression right) {
		super((left != null ? left.isConstant() : true) && (right != null ? right.isConstant() : true), (left != null ? left.getType() : (right != null ? right.getType() : ExpType.NoType)));
		if (left == null) {
			throw new RuntimeException("Missing expression");				
		}
		this.left = left;
		this.right = right;
	}

	@Override
	public ASTExpression current() {
		return left;
	}

	@Override
	public ASTExpression next() {
		return right;
	}

	@Override
	public int flatten(List<ASTExpression> dest) {
        int leftnum = 0;
        if (left != null)
            leftnum = left.flatten(dest);
        
        int rightnum = 0;
        if (right != null)
            rightnum = right.flatten(dest);
        
        left = null;
        right = null;
        
        return leftnum + rightnum;
	}

	@Override
	public void entropy(StringBuilder e) {
		if (left != null) left.entropy(e);
        if (right != null) right.entropy(e);
        e.append("\u00C5\u0060\u00A5\u00C5\u00C8\u0074");
	}

	@Override
	public ASTExpression simplify() {
		if (left != null) left = left.simplify();
        if (right != null) right = right.simplify();
        return this;
	}

	@Override
	public int evaluate(double[] result, int length, RTI rti) { 
		if (type != ExpType.NumericType) {
			throw new RuntimeException("Non-numeric expression in a numeric context");
        }
		double[] lresult = new double[1];
        int leftnum = left != null ? left.evaluate(result, 1, rti) : 0;
        if (leftnum <= 0) 
            return -1;
        
		double[] rresult = new double[1];
        int rightnum = right != null ? right.evaluate(result, 1, rti) : 0;
        if (rightnum <= 0) 
            return -1;
        
        result[0] = lresult[0];
        result[1] = rresult[0];//TODO da controllare
        
        return leftnum + rightnum;
	}		
	
	@Override
	public void evaluate(Modification modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) {
		left.evaluate(modification, s, width, justCheck, seedIndex, rti);
        right.evaluate(modification, s, width, justCheck, seedIndex, rti);
	}
}