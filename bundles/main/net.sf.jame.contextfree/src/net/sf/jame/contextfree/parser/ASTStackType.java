package net.sf.jame.contextfree.parser;

class ASTStackType {
        private double number;
        private ASTStackType rule;
        private ASTStackRule ruleHeader;
        private ASTParameter typeInfo;
        
		public ASTStackType(int shapeType, int argSize, int i) {
			// TODO Auto-generated constructor stub
		}
		
	    public static ASTStackType[] alloc(int name, int size, ASTParameter ti) {
	        ASTStackType[] newRule = new ASTStackType[size > 0 ? size + 2 : 1];
	        newRule[0].ruleHeader.setRuleName(name);
	        newRule[0].ruleHeader.setParamCount(size);
	        if (size > 0) {
	            newRule[1].typeInfo = ti;
	        }
	        return newRule;
	    }
	    
	    public static void evalArgs(ASTStackType[] stack, ASTExpression arguments, ASTStackType parent, RTI rti) {
	    	//TODO completare
	    	ASTStackIterator dest = new ASTStackIterator(stack);
	    	
	        ASTExpIterator arg = arguments.begin();
	        ASTExpIterator arg_end = arguments.end();
	        
	        while (arg != arg_end) {
	            switch (arg.getExpression().type) {
	                case NumericType: {
//	                    arg.expression.evaluate(dest.current().number, ((ASTParameter) dest.current().expression).tupleSize, rti);
	                    break;
	                }
	                case ModificationType: {
	                    int[] dummy = new int[1];
	                    Modification zeroMod;
//	                    Modification m = (Modification)dest.current().number;
//	                    m = zeroMod;
//	                    arg.expression.evaluate(m, null, null, false, dummy, rti);
	                    break;
	                }
	                case RuleType: {
	                    dest.current().rule = arg.getExpression().evalArgs(parent, rti);
	                    break;
	                }
	                default:
	                    break;
	            }
	            
	            arg.next();
	            dest.next();
	        }
	    }

		public double getNumber() {
			return number;
		}

		public ASTStackType getRule() {
			return rule;
		}

		public ASTStackRule getRuleHeader() {
			return ruleHeader;
		}

		public ASTParameter getTypeInfo() {
			return typeInfo;
		}
    }
