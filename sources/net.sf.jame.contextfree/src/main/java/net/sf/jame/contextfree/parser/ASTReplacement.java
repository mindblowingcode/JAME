package net.sf.jame.contextfree.parser;

class ASTReplacement {
	private ASTRuleSpecifier shapeSpec;
	private ASTModification childChange = new ASTModification();
	private ERepElemType repType;
	private EPathOp pathOp;
	
	public ASTReplacement(ASTRuleSpecifier shapeSpec, ASTModification childChange, ERepElemType repType) {
		this.repType = repType;
		this.childChange = childChange;
		this.shapeSpec = shapeSpec;
		this.pathOp = EPathOp.UNKNOWN;
	}

	public ASTReplacement(ASTRuleSpecifier shapeSpec, ASTModification childChange) {
		this(shapeSpec, childChange, ERepElemType.empty);
	}

	public ASTReplacement(ASTModification childChange, ERepElemType repType) {
		this(new ASTRuleSpecifier(), childChange, repType);
	}

	public ASTReplacement(String name) {
		this(new ASTRuleSpecifier(), new ASTModification(), ERepElemType.op);
		this.pathOp = EPathOp.pathOpTypeByName(name);
		if (this.pathOp == EPathOp.UNKNOWN) {
			error("Unknown path operation type");
		}
	}

	public ASTRuleSpecifier getShapeSpecifier() {
		return shapeSpec;
	}

	public ERepElemType getRepType() {
		return repType;
	}
	
	public void setRepType(ERepElemType repType) {
		this.repType = repType;
	}
	
	public EPathOp getPathOp() {
		return pathOp;
	}

	public void setPathOp(EPathOp pathOp) {
		this.pathOp = pathOp;
	}

	public ASTModification getChildChange() {
		return childChange;
	}

	public void compile(ECompilePhase ph) {
		ASTExpression r = shapeSpec.compile(ph);
		assert(r == null);
		r = childChange.compile(ph);
		assert(r == null);
		
		switch (ph) {
			case TypeCheck: 
				childChange.addEntropy(shapeSpec.getEntropy());
				if (Builder.currentBuilder().isInPathContainer()) {
					// This is a subpath
					if (shapeSpec.getArgSource() == EArgSource.ShapeArgs || shapeSpec.getArgSource() == EArgSource.StackArgs || PrimShape.isPrimShape(shapeSpec.getShapeType())) {
						if (repType != ERepElemType.op) {
							error("Error in subpath specification");
						} else {
							ASTRule rule = Builder.currentBuilder().getRule(shapeSpec.getShapeType());
							if (rule == null || rule.isPath()) {
								error("Subpath can only refer to a path");
							} else if (rule.getRuleBody().getRepType() != repType.getType()) {
								error("Subpath type mismatch error");
							}
						}
					}
				}
				break;
	
			case Simplify: 
				r = shapeSpec.simplify();
				assert(r == shapeSpec);
				r = childChange.simplify();
				assert(r == childChange);
				break;
	
			default:
				break;
		}
	}

	public void replace(Shape s, RTI rti) {
		if (shapeSpec.getArgSource() == EArgSource.NoArgs) {
			s.setShapeType(shapeSpec.getShapeType());
			s.setParameters(null);
		} else {
			s.setParameters(shapeSpec.evalArgs(rti, s.getParameters()));
			if (shapeSpec.getArgSource() == EArgSource.SimpleParentArgs) {
				s.setShapeType(shapeSpec.getShapeType());
			} else {
				s.setShapeType(s.getParameters().getRuleName());
			}
			if (s.getParameters().getParamCount() == 0) {
				s.setParameters(null);
			}
		}
		rti.getCurrentSeed().add(childChange.getModData().getRand64Seed());
		rti.getCurrentSeed().bump();
		Modification[] mod = new Modification[1];
		childChange.evaluate(mod, true, rti);
		s.setWorldState(mod[0]);
		s.setAreaCache(s.getWorldState().area());
	}

	public void traverse(Shape parent, boolean tr, RTI rti) {
		Shape child = parent;
		switch (repType) {
		case replacement:
			replace(child,  rti);
			child.getWorldState().setRand64Seed(rti.getCurrentSeed());
			child.getWorldState().getRand64Seed().bump();
			rti.processShape(child);
			break;

		case op:
			if (!tr) child.getWorldState().setTransform(null);
		case mixed:
		case command:
			replace(child, rti);
			rti.processSubpath(child, tr || repType == ERepElemType.op, repType);
			break;

		default:
			throw new RuntimeException("Subpaths must be all path operation or all path command");
		}
	}

	protected void error(String message) {
		System.out.println(message);
	}
}