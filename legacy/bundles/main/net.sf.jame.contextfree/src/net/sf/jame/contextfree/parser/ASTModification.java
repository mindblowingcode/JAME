package net.sf.jame.contextfree.parser;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


class ASTModification extends ASTExpression {
	public static final int SIZE = 9;
	private Modification data;
	private ModClassEnum modClass;
	private ASTExpression modifications;
	private List<ASTExpression> expressions;
	private AffineTransform transform = new AffineTransform();
	
	public ASTModification() {
		super(true, ExpType.ModificationType);
		this.modClass = ModClassEnum.NotAClass;
		this.expressions = null;
	}

	public ASTModification(ASTExpression expression) {
		super(true, ExpType.ModificationType);
		//TODO da controllare
		this.modClass = ModClassEnum.NotAClass; 
		this.expressions = null;
		init(expression);
	}

	public ASTModification(ASTModification modifications) {
		super(true, ExpType.ModificationType);
		this.modClass = modifications.modClass;
		this.expressions = null;
		ASTModification[] mods = new ASTModification[1];//TODO ma � necessario il vettore?
		mods[0] = modifications;
		init(mods, null, null);
	}

	public ASTModification(ASTModification modifications, String name) {
		super(true, ExpType.ModificationType);
		this.modClass = modifications.modClass;
		this.expressions = null;
		ASTModification[] mods = new ASTModification[1];//TODO ma � necessario il vettore?
		mods[0] = modifications;
		init(mods, name, null, null);
	}

    public void init(ASTExpression[] modifications, String name, String p, double[] width) {
    	StringBuilder e = new StringBuilder();
    	if (modifications[0] != null)
    		modifications[0].entropy(e);
		e.append(name);
		ASTExpression ent = new ASTModTerm(ModTypeEnum.Entropy, null, e.toString());//TODO verificare
		if (modifications[0] != null) {
			modifications[0] = new ASTCons(modifications[0].simplify(), ent);
		} else {
			modifications[0] = ent;
		}
		evalConst(modifications[0], p, width);
	}
	
	public void init(ASTExpression[] modifications, String p, double[] width) {
		init(modifications, "it doesn't matter", p, width);
	}

	public void init(ASTExpression expression) {
		// TODO verificare
		ASTExpression[] mods = new ASTExpression[1];//TODO ma � necessario il vettore?
		mods[0] = expression;
		init(mods, "it doesn't matter", null, null);
	}

	private void evalConst(ASTExpression modifications, String p, double[] width) {
		int[] seedIndex = new int[1];
        int nonConstant = 0;
        int modClass = 0;
        
        List<ASTExpression> temp = new ArrayList<ASTExpression>();
        modifications.flatten(temp);
        
		for (ASTExpression expression : temp) {
            boolean keepThisOne = false;
            ASTModTerm mod = (ASTModTerm)expression;
            if (mod == null) {
                throw new RuntimeException("Unknown term in shape adjustment");
            }
            
            // Put in code for separating color changes and target color changes
            int mc = mod.getModType().ordinal();
            modClass |= mod.getModType().ordinal();
            if (!mod.isConstant)
                nonConstant |= mc;
            boolean justCheck = (mc & nonConstant) != 0;
            
            try {
                mod.evaluate(data, p, width, justCheck, seedIndex, null);
            } catch (DeferUntilRuntimeException e) {
                keepThisOne = true;
            }
            
            if (justCheck || keepThisOne) {
            	if (expressions == null) {
            		expressions = new ArrayList<ASTExpression>();
            	}
            	expressions.add(mod);
            }
		}
	}

	@Override
	public void evaluate(Modification modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) {
        if (expressions == null) {
            modification.concat(data);
        } else {
            Modification[] mod = new Modification[0];
            setVal(mod, s, width, justCheck, seedIndex, rti);
            modification.concat(mod[0]);
        }
	}

	public void setVal(Modification[] modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) {
		modification[0] = data;
		for (ASTExpression expression : expressions) {
			expression.evaluate(modification[0], s, width, justCheck, seedIndex, rti);
		}
	}

	@Override
	public int evaluate(double[] result, int offset, int length, RTI rti) {
		throw new RuntimeException("Improper evaluation of an adjustment expression");
	}

	public Modification getData() {
		return data;
	}

	public ModClassEnum getModClass() {
		return modClass;
	}

	public ASTExpression getModifications() {
		return modifications;
	}

	public List<ASTExpression> getExpressions() {
		return expressions;
	}
}
