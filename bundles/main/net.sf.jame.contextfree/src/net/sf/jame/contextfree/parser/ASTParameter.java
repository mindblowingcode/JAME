package net.sf.jame.contextfree.parser;

import java.util.Iterator;
import java.util.List;

class ASTParameter extends ASTExpression {
    	private ExpType type;
    	private boolean isParameter;
    	private boolean isLoopIndex;
    	private int nameIndex;
    	private ASTExpression expression;
    	private ASTModification modification;
    	private int stackIndex;
    	private int tupleSize;
    	
    	public ASTParameter() {
    		super(true, ExpType.NoType);
    		nameIndex = -1;
    		stackIndex = -1;
    		tupleSize = 1;
    	}
    	
		public void init(String name, int nameIndex) {
			this.nameIndex = nameIndex;
			type = expression.type;
			expression = expression.simplify();

			switch (type) {
				case ModificationType: {
					tupleSize = ASTModification.SIZE;
					ASTExpression mod = expression;
					expression = null;
					modification.init(mod);
					break;
				}
				case RuleType: {
					tupleSize = 1;
					break;
				}
				case NumericType: {
					tupleSize = expression.evaluate(null, 0, 0, null);
					if (tupleSize == 0)
						tupleSize = 1; // loop index
					if (tupleSize < 1 || tupleSize > 8) {
						throw new RuntimeException("Illegal vector size (<1 or >8)");
					}
					break;
				}
				default: {
					throw new RuntimeException("Definition expression has mixed type");
				}
			}

			// Set the Modification entropy to parameter name, not its own contents
			int[] i = new int[1];
			modification.getData().getRand48Seed().init();
			modification.getData().getRand48Seed().xorString(name, i);
		}

		public void init(String typeName, String name, int nameIndex) {
			this.nameIndex = nameIndex;
			expression = null;

			if (typeName.equals("number")) {
				type = ExpType.NumericType;
			} else if (typeName.equals("adjustment")) {
				tupleSize = ASTModification.SIZE;
				type = ExpType.ModificationType;
			} else if (typeName.equals("shape")) {
				type = ExpType.RuleType;
				tupleSize = 1;
			} else if (typeName.startsWith("vector") && typeName.length() == 7) {
//				String size = typeName.substring(6, 7);
				if (typeName.charAt(6) > '0' && typeName.charAt(6) < '9') {
					type = ExpType.NumericType;
					tupleSize = typeName.charAt(6) - '0';
					if (tupleSize < 1 || tupleSize > 8) {
						throw new RuntimeException("Illegal vector size (<1 or >8)");
					}
				}
			} else {
				type = ExpType.NoType;
			}

			int[] i = new int[1];
			modification.getData().getRand48Seed().init();
			modification.getData().getRand48Seed().xorString(name, i);
		}
        
        public void check() 
        { 
            if (type == ExpType.NoType)
            	throw new RuntimeException("Unknown parameter type");
            if (nameIndex == -1)
            	throw new RuntimeException("Reserved keyword used for parameter name");
        }

        public boolean compare(ASTParameter p) {
            if (type != p.type) return true;
            if (type == ExpType.NumericType && tupleSize != p.tupleSize) return true;
            return false;
        }
        
        public boolean compare(ASTExpression e) {
            if (type != e.type) return true;
            if (type == ExpType.NumericType && tupleSize != e.evaluate(null, 0, 0, null)) return true;
            return false;
        }
        
        public static int checkType(List<? extends ASTParameter> types, List<? extends ASTParameter> parent, ASTExpression args)
        {
            // Walks down the right edge of an expression tree checking that the types
            // of the children match the specified argument types
            if ((types == null || types.isEmpty()) && (args == null)) return 0;
            if (types == null || types.isEmpty()) {
            	throw new RuntimeException("Arguments are not expected.");
//                return -1;
            }
            if (args == null) {
            	throw new RuntimeException("Arguments are expected.");
//                return -1;
            }
            boolean justCount = args == null || args.type == ExpType.NoType;
            
            int count = 0;
            ASTExpIterator arg = args.begin();
            ASTExpIterator arg_end = args.end();
            
            for (Iterator<? extends ASTParameter> it = types.iterator(); it.hasNext();) {
            	ASTParameter param = it.next();
                if (!justCount && arg == arg_end) {
                	throw new RuntimeException(args != null ? "Not enough arguments" : "arguments expected");
//                    return -1;
                }
                if (!justCount && param.type != arg.getExpression().type) {
                	throw new RuntimeException("Incorrect argument type.");
//                	throw new RuntimeException(param_it->mLocation, "This is the expected type.");
//                    return -1;
                }
                count += param.tupleSize;
                
                arg.next();
            }
            
            if (arg != arg_end) {
            	throw new RuntimeException("Too many arguments.");
//                return -1;
            }
            
            if (justCount && types != parent) {
                if (parent == null) {
                	throw new RuntimeException("Parameter reuse not allowed in this context.");
//                    return -1;
                }
                Iterator<? extends ASTExpression> pit = parent.iterator();
                for (Iterator<? extends ASTExpression> it = types.iterator(); it.hasNext();) {
                    ASTExpression param = it.next();
					ASTExpression pparam = pit.next();
					if (!pit.hasNext() || param.equals(pparam)) {
                    	throw new RuntimeException("Parameter reuse only allowed when type signature is identical.");
//                        return -1;
                    }
                }
            }
            
            return count;
        }

		@Override
		public ExpType getType() {
			return type;
		}

		public boolean isParameter() {
			return isParameter;
		}

		public boolean isLoopIndex() {
			return isLoopIndex;
		}

		public int getNameIndex() {
			return nameIndex;
		}

		public ASTExpression getExpression() {
			return expression;
		}

		public ASTModification getModification() {
			return modification;
		}

		public int getStackIndex() {
			return stackIndex;
		}

		public int getTupleSize() {
			return tupleSize;
		}
    }