package net.sf.jame.contextfree.parser;


class ASTUserFunction extends ASTExpression {
		private ASTExpression arguments;
		private ASTDefine definition;
		private int nameIndex;
		private boolean isLet;
    	
    	public ASTUserFunction(String name, ASTExpression arguments, ASTDefine definition) {
    		super();
    	}
    	
    	public ASTExpression getArguments() {
    		return arguments;
    	}
    	
    	@Override
		public int evaluate(double[] result, int length, RTI rti) { 
	        return 1; 
   		}
    	
    	@Override
		public void entropy(StringBuilder e) {
    	}
    	
    	@Override
		public ASTExpression simplify() { 
            return this;
    	}

		public ASTDefine getDefinition() {
			return definition;
		}

		public int getNameIndex() {
			return nameIndex;
		}

		public boolean isLet() {
			return isLet;
		}
    }