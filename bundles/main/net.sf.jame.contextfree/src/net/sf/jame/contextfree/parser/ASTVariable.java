package net.sf.jame.contextfree.parser;

import java.util.List;


class ASTVariable extends ASTExpression {
	private String text;
	private int stringIndex;
	private int stackIndex;
	private int count;

	public ASTVariable(int stringIndex, String text) {
		super(true, ExpType.NumericType);
		this.stringIndex = stringIndex;
		this.stackIndex = 0;
		this.count = 1;
		this.text = text;
	}

	public ASTVariable(ASTVariable var) {
		super(var.isConstant, var.getType());
		stringIndex = var.stringIndex;
		stackIndex = var.stackIndex;
		count = var.count;
		text = var.text;
	}

	@Override
	public int flatten(List<ASTExpression> dest) {
        int c = count;
        count = 1;
        dest.add(this);
        for (int i = 1; i < c; ++i) {
            ASTVariable next = new ASTVariable(this);
            next.stackIndex += i;
            dest.add(next);
        }
        return c;
	}

	@Override
	public void entropy(StringBuilder e) {
		e.append(text);
	}

	@Override
	public int evaluate(double[] result, int offset, int length, RTI rti) {
		if (type != ExpType.NumericType) {
			throw new RuntimeException("Non-numeric variable in a numeric context");
        }
        
		if (result != null && length < count)
            return -1;
        
        if (result != null) {
            if (rti == null) throw new DeferUntilRuntimeException();
            for (int i = 0; i < count; ++i) {
                Number stackItem = (Number) rti.logicalStack.get(stackIndex + i);
				result[i] = stackItem.doubleValue();
            }
        }
        
        return count;
	}

	@Override
	public void evaluate(Modification modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) {
		if (type != ExpType.ModificationType) {
			throw new RuntimeException("Non-adjustment variable referenced in an adjustment context");
        }
        if (rti == null) throw new DeferUntilRuntimeException();
        if (justCheck) return;
	    modification.concat((Modification) rti.logicalStack.get(stackIndex));
	}
}
