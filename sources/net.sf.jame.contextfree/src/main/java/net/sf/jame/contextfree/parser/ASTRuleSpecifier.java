package net.sf.jame.contextfree.parser;

import java.util.List;


class ASTRuleSpecifier extends ASTExpression {
    	public static ASTRuleSpecifier ZERO = new ASTRuleSpecifier("", 0);
    	private int shapeType;
    	private int argSize;
    	private StringBuilder entropy;
    	private ArgSource argSource;
    	private ASTExpression arguments;
    	private ASTStackType simpleRule;
    	private int stackIndex;
    	private List<ASTParameter> typeSignature;
    	
    	public ASTRuleSpecifier(String name, int stackIndex) {
    		this(0, name, null, null, null);
    		this.stackIndex = stackIndex;
    	}
    	
    	public ASTRuleSpecifier(ASTRuleSpecifier spec, String name) {
    		this(spec.shapeType, name, null, null, null);
    		this.stackIndex = spec.stackIndex;
    		this.typeSignature = spec.typeSignature;
            if (spec.argSource == ArgSource.SimpleArgs) {
            	ASTStackType simp = new ASTStackType(shapeType, argSize, 0);
                argSource = ArgSource.SimpleArgs;
                simpleRule = simp;
                if (argSize > 0) {
                    for (int i = 1; i < argSize + 2; ++i) {
//                    	simp.item[i] = spec.simpleRule.item[i];TODO completare
                    }
                }
            }
    	}
    	
    	public ASTRuleSpecifier(ASTRuleSpecifier spec) {
    		this(spec.shapeType, spec.entropy.toString(), null, null, null);
    		this.type = spec.type;
    		this.isConstant = spec.isConstant;
    		this.argSource = spec.argSource;
    		this.simpleRule = spec.simpleRule;
    		this.stackIndex = spec.stackIndex;
    		this.typeSignature = spec.typeSignature;
    		spec.arguments = null;
    		spec.simpleRule = null;
    	}
    	    	
    	public ASTRuleSpecifier(int shapeType, String name, List<ASTParameter> typeSignature, List<ASTParameter> parent) {
    		this(shapeType, name, null, typeSignature, parent);
    	}

    	public ASTRuleSpecifier(int shapeType, String name, ASTExpression arguments, List<ASTParameter> typeSignature, List<ASTParameter> parent) {
    		super(arguments == null || arguments.isConstant, ExpType.RuleType);
    		this.entropy = new StringBuilder();
    		this.shapeType = shapeType;
    		this.argSource = ArgSource.DynamicArgs;
    		this.arguments = arguments;
    		this.typeSignature = typeSignature;
    		this.stackIndex = 0;
    		this.simpleRule = null;
    		this.entropy.append(name);
            if (typeSignature != null && typeSignature.size() == 0) {
            	typeSignature = null;
            	this.typeSignature = null;
            }
            if (parent != null && parent.size() == 0)
                parent = null;
            
            argSize = ASTParameter.checkType(typeSignature, parent, arguments);
            if (argSize < 0) return;
                
            if (arguments != null && arguments.type != ExpType.NoType) {
                arguments.entropy(entropy);
                if (arguments.isConstant) {
//                    StackType simp = evalArgs();//TODO
//                    simp[0].ruleHeader.mRefCount = StackRule::MaxRefCount;
//                    simpleRule = simp;
                    argSource = ArgSource.SimpleArgs;
                } else {
                    arguments = arguments.simplify();
                }
            } else if (arguments != null && arguments.type == ExpType.NoType) {
                argSource = ArgSource.ParentArgs;
            } else {
                argSource = ArgSource.NoArgs;
//                simpleRule = StackType::alloc(shapeType, 0, types);//TODO
//                simpleRule[0].ruleHeader.mRefCount = StackRule::MaxRefCount;
            }
    	}

		@Override
		public ASTStackType evalArgs(ASTStackType parent, RTI rti) {
            switch (argSource) {
	            case NoArgs:
	            case SimpleArgs:
	                return simpleRule;
	            case StackArgs: {
//	                ASTStackType ret = (rti.mLogicalStackTop + mStackIndex).rule;
//	                ret.retain(rti);
//	                return ret;TODO completare
	            }
	            case ParentArgs:
//	                assert(parent != null);
//	                assert(rti != null);
//	                parent.retain(rti);
//	                return parent;TODO completare
	            case DynamicArgs: {
//	                ASTStackType ret = new ASTStackType(shapeType, argSize, typeSignature);
//	                ret.evalArgs(rti, arguments, parent);
//	                return ret;TODO completare
	            }
	            default: {
	                assert(false);
	            }
			}
			return null;
		}

		@Override
		public int evaluate(double[] result, int offset, int length, RTI rti) {
			throw new RuntimeException("Improper evaluation of a rule specifier");
		}

		@Override
		public void entropy(StringBuilder e) {
			e.append(entropy.toString());
		}

		@Override
		public ASTExpression simplify() {
			if (arguments != null) {
				arguments = arguments.simplify();
			}			
			return this;
		}

		public StringBuilder getEntropy() {
			return entropy;
		}

		public int getShapeType() {
			return shapeType;
		}

		public int getArgSize() {
			return argSize;
		}

		public ArgSource getArgSource() {
			return argSource;
		}

		public ASTExpression getArguments() {
			return arguments;
		}

		public ASTStackType getSimpleRule() {
			return simpleRule;
		}

		public int getStackIndex() {
			return stackIndex;
		}

		public List<ASTParameter> getTypeSignature() {
			return typeSignature;
		}

		public void setTypeSignature(List<ASTParameter> typeSignature) {
			this.typeSignature = typeSignature;
		}
    }