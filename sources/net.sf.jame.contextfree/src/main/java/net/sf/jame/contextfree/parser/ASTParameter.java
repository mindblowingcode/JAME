package net.sf.jame.contextfree.parser;

import java.util.Iterator;
import java.util.List;

class ASTParameter extends ASTExpression {
    	public static boolean Impure;
		private EExpType type;
    	private boolean isParameter;
    	private boolean isLoopIndex;
    	private int nameIndex;
    	private ASTExpression expression;
    	private ASTModification modification;
    	private int stackIndex;
    	private int tupleSize;
    	private ELocality localityType;
    	
    	public ASTParameter() {
    		super(true, false, EExpType.NoType);
    		nameIndex = -1;
    		stackIndex = -1;
    		tupleSize = 1;
    	}
    	
		public ASTParameter(int i, ASTDefine definition) {
			// TODO Auto-generated constructor stub
		}

		public void init(String name, int nameIndex) {
			this.nameIndex = nameIndex;
			type = expression.type;
			expression = expression.simplify();

			switch (type) {
				case ModType: {
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
					tupleSize = expression.evaluate((double[])null, 0, null);
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
			modification.getModData().getRand48Seed().init();
			modification.getModData().getRand48Seed().xorString(name, i);
		}

		public void init(String typeName, String name, int nameIndex) {
			this.nameIndex = nameIndex;
			expression = null;

			if (typeName.equals("number")) {
				type = EExpType.NumericType;
			} else if (typeName.equals("adjustment")) {
				tupleSize = ASTModification.SIZE;
				type = EExpType.ModType;
			} else if (typeName.equals("shape")) {
				type = EExpType.RuleType;
				tupleSize = 1;
			} else if (typeName.startsWith("vector") && typeName.length() == 7) {
//				String size = typeName.substring(6, 7);
				if (typeName.charAt(6) > '0' && typeName.charAt(6) < '9') {
					type = EExpType.NumericType;
					tupleSize = typeName.charAt(6) - '0';
					if (tupleSize < 1 || tupleSize > 8) {
						throw new RuntimeException("Illegal vector size (<1 or >8)");
					}
				}
			} else {
				type = EExpType.NoType;
			}

			int[] i = new int[1];
			modification.getModData().getRand48Seed().init();
			modification.getModData().getRand48Seed().xorString(name, i);
		}
        
        public void check() 
        { 
            if (type == EExpType.NoType)
            	throw new RuntimeException("Unknown parameter type");
            if (nameIndex == -1)
            	throw new RuntimeException("Reserved keyword used for parameter name");
        }

        public boolean compare(ASTParameter p) {
            if (type != p.type) return true;
            if (type == EExpType.NumericType && tupleSize != p.tupleSize) return true;
            return false;
        }
        
        public boolean compare(ASTExpression e) {
            if (type != e.type) return true;
            if (type == EExpType.NumericType && tupleSize != e.evaluate((double[])null, 0, null)) return true;
            return false;
        }
        
        public static int checkType(List<? extends ASTParameter> types, ASTExpression args, boolean chackNumber)
        {
//            // Walks down the right edge of an expression tree checking that the types
//            // of the children match the specified argument types
//            if ((types == null || types.isEmpty()) && (args == null)) return 0;
//            if (types == null || types.isEmpty()) {
//            	throw new RuntimeException("Arguments are not expected.");
////                return -1;
//            }
//            if (args == null) {
//            	throw new RuntimeException("Arguments are expected.");
////                return -1;
//            }
//            boolean justCount = args == null || args.type == EExpType.NoType;
//            
//            int count = 0;
//            ASTExpIterator arg = args.begin();
//            ASTExpIterator arg_end = args.end();
//            
//            for (Iterator<? extends ASTParameter> it = types.iterator(); it.hasNext();) {
//            	ASTParameter param = it.next();
//                if (!justCount && arg == arg_end) {
//                	throw new RuntimeException(args != null ? "Not enough arguments" : "arguments expected");
////                    return -1;
//                }
//                if (!justCount && param.type != arg.getExpression().type) {
//                	throw new RuntimeException("Incorrect argument type.");
////                	throw new RuntimeException(param_it->mLocation, "This is the expected type.");
////                    return -1;
//                }
//                count += param.tupleSize;
//                
//                arg.next();
//            }
//            
//            if (arg != arg_end) {
//            	throw new RuntimeException("Too many arguments.");
////                return -1;
//            }
//            
//            if (justCount && types != parent) {
//                if (parent == null) {
//                	throw new RuntimeException("Parameter reuse not allowed in this context.");
////                    return -1;
//                }
//                Iterator<? extends ASTExpression> pit = parent.iterator();
//                for (Iterator<? extends ASTExpression> it = types.iterator(); it.hasNext();) {
//                    ASTExpression param = it.next();
//					ASTExpression pparam = pit.next();
//					if (!pit.hasNext() || param.equals(pparam)) {
//                    	throw new RuntimeException("Parameter reuse only allowed when type signature is identical.");
////                        return -1;
//                    }
//                }
//            }
//            
            return 0;
        }

		@Override
		public EExpType getType() {
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

		public void setStackIndex(int stackIndex) {
			this.stackIndex = stackIndex;
		}

		public ELocality getLocality() {
			return localityType;
		}

		public void setLocality(ELocality localityType) {
			this.localityType = localityType;
		}

		public String getLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		public ASTExpression constCopy(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public void setDefinition(ASTDefine definition) {
			// TODO Auto-generated method stub
			
		}

		public ASTDefine getDefinition() {
			// TODO Auto-generated method stub
			return null;
		}
    }