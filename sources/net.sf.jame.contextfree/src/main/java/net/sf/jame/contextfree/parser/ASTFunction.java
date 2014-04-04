package net.sf.jame.contextfree.parser;


class ASTFunction extends ASTExpression {
		private ASTExpression arguments;
		private EFuncType funcType;
		private ASTRand48 random;
    	
    	public ASTFunction(String name, ASTExpression arguments) {
    		this(name, arguments, null);
    	}
    	
    	public ASTFunction(String name, ASTExpression arguments, ASTRand48 seed) {
    		super(arguments != null ? arguments.isConstant : true, EExpType.NumericType);
    		this.funcType = EFuncType.NotAFunction;
    		this.arguments = arguments;
    		
            if (name == null || name.trim().length() == 0) {
                throw new RuntimeException("Invalid function name");
            }
            
            int argcount = arguments != null ? arguments.evaluate((double[])null, 0, null) : 0;
            
            funcType = EFuncType.getFuncTypeByName(name);
            
            if (funcType == EFuncType.NotAFunction) {
                throw new RuntimeException("Unknown function");
            }
            
            if (funcType == EFuncType.Infinity && argcount == 0) {
                arguments = new ASTReal(1.0f);
            }
            
            if (funcType.ordinal >= EFuncType.Rand_Static.ordinal && funcType.ordinal <= EFuncType.RandInt.ordinal) {
                if (funcType == EFuncType.Rand_Static) {
                	random = seed;
                } else {
                    isConstant = false;
                } 
                
                switch (argcount) {
                    case 0:
                    	arguments = new ASTCons(new ASTReal(0.0f), new ASTReal(1.0f));
                        break;
                    case 1:
                    	arguments = new ASTCons(new ASTReal(0.0f), arguments);
                        break;
                    case 2:
                        break;
                    default:
                        throw new RuntimeException("Illegal argument(s) for random function");
                }
                
                if (!isConstant && funcType == EFuncType.Rand_Static) {
                    throw new RuntimeException("Argument(s) for rand_static() must be constant");
                }

                this.arguments = arguments;
            } else {
            	if (funcType.ordinal < EFuncType.Atan2.ordinal) {
            		if (argcount != 1) {
                        throw new RuntimeException(funcType == EFuncType.Infinity ? "Function takes zero or one arguments" : "Function takes one argument");
            		}
            	} else {
            		if (argcount != 2) {
                        throw new RuntimeException("Function takes two arguments");
            		}
            	}
            	
                this.arguments = arguments;
            }            
        }
    	
    	public ASTExpression getArguments() {
    		return arguments;
    	}
    	
    	public EFuncType getFuncType() {
    		return funcType;
    	}
    	
    	@Override
		public int evaluate(double[] result, int length, RTI rti) { 
	        if (type != EExpType.NumericType) {
	    	   throw new RuntimeException("Non-numeric expression in a numeric context");
	        }
	        
	        if ((result != null && length < 1) || (funcType.ordinal <= EFuncType.NotAFunction.ordinal) || (funcType.ordinal >= FuncType.EFuncType.ordinal))
	            return -1;
	        
	        if (result == null)
	            return 1;
	        
	        // no need to check the argument count, the constructor already checked it

	        double[] a = new double[2];
	        arguments.evaluate(a, 2, rti);
	        
	        switch (funcType) {
	            case  Cos:  
	                result[0] = Math.cos(a[0] * 0.0174532925199);
	                break;
	            case  Sin:  
	                result[0] = Math.sin(a[0] * 0.0174532925199);
	                break;
	            case  Tan:  
	                result[0] = Math.tan(a[0] * 0.0174532925199);
	                break;
	            case  Cot:  
	                result[0] = 1.0 / Math.tan(a[0] * 0.0174532925199);
	                break;
	            case  Acos:  
	                result[0] = Math.acos(a[0]) * 57.29577951308;
	                break;
	            case  Asin:  
	                result[0] = Math.asin(a[0]) * 57.29577951308;
	                break;
	            case  Atan:  
	                result[0] = Math.atan(a[0]) * 57.29577951308;
	                break;
	            case  Acot:  
	                result[0] = Math.atan(1.0 / a[0]) * 57.29577951308;
	                break;
	            case  Cosh:  
	                result[0] = Math.cosh(a[0]);
	                break;
	            case  Sinh:  
	                result[0] = Math.sinh(a[0]);
	                break;
	            case Tanh:  
	                result[0] = Math.tanh(a[0]);
	                break;
	            case Acosh:  
	                result[0] = Math.log(a[0] + Math.sqrt(a[0] * a[0] - 1));
	                break;
	            case Asinh:  
	                result[0] = Math.log(a[0] + Math.sqrt(a[0] * a[0] + 1));
	                break;
	            case Atanh:  
	                result[0] = Math.log((1 / a[0] + 1) / (1 / a[0] - 1)) / 2;
	                break;
	            case Log:  
	                result[0] = Math.log(a[0]);
	                break;
	            case Log10:  
	                result[0] = Math.log10(a[0]);
	                break;
	            case Sqrt:  
	                result[0] = Math.sqrt(a[0]);
	                break;
	            case Exp:  
	                result[0] = Math.exp(a[0]);
	                break;
	            case Abs:  
	                result[0] = Math.abs(a[0]);
	                break;
	            case Infinity:
	                result[0] = (a[0] < 0.0) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
	                break;
	            case Atan2: 
	                result[0] = Math.atan2(a[0], a[1]) * 57.29577951308;
	                break;
	            case Mod: 
	                result[0] = Math.hypot(a[0], a[1]);
	                break;
	            case Floor:
	                result[0] = Math.floor(a[0]);
	                break;
	            case Rand_Static: 
	                result[0] = random.getDouble(false) * Math.abs(a[1] - a[0]) + Math.min(a[0], a[1]);
	                break;
	            case Rand: 
	            	//TODO
//	                if (rti == NULL) throw DeferUntilRuntime();
//	                rti->mRandUsed = true;
//	                result[0] = rti->mCurrentSeed.getDouble() * Math.abs(a[1] - a[0]) + Math.min(a[0], a[1]);
	                break;
	            case Rand2: 
	            	//TODO
//	                if (rti == NULL) throw DeferUntilRuntime();
//	                rti->mRandUsed = true;
//	                result[0] = (rti->mCurrentSeed.getDouble() * 2.0 - 1.0) * a[1] + a[0];
	                break;
	            case RandInt: 
	            	//TODO
//	                if (rti == NULL) throw DeferUntilRuntime();
//	                rti->mRandUsed = true;
//	                result[0] = floor(rti->mCurrentSeed.getDouble() * Math.abs(a[1] - a[0]) + Math.min(a[0], a[1]));
	                break;
	            default:
	                return -1;
	        }
	        
	        return 1; 
   		}
    	
    	@Override
		public void entropy(StringBuilder e) {
            if (funcType.ordinal <= EFuncType.NotAFunction.ordinal || funcType.ordinal >= FuncType.EFuncType.ordinal) return;
            arguments.entropy(e);
            e.append(funcType.entropy);
    	}
    	
    	@Override
		public ASTExpression simplify() { 
            if (isConstant) {
                double[] result = new double[1];
                if (evaluate(result, 1, null) != 1) {
                    return this;
                }
                return new ASTReal(result[0]);
            } else {
                arguments = arguments.simplify();
            }
            return this;
    	}
    }