package net.sf.jame.contextfree.parser;

import java.util.ArrayList;
import java.util.List;

class ASTDefine extends ASTReplacement {
	private EDefineType defineType;
	private ASTExpression exp;
	private int tupleSize;
	private EExpType expType;
	private boolean isNatural;
	private List<ASTParameter> parameters = new ArrayList<ASTParameter>();
	private int stackCount;
	private String name;
	private int configDepth;
	
	public ASTDefine(String name) {
		super(null, null, null, null);
		this.defineType = EDefineType.StackDefine;
		this.expType = EExpType.NoType;
		this.isNatural = false;
		this.stackCount = 0;
		this.name = name;
		this.configDepth = -1;
//		getModification().getModData().setEntropy(name);//TODO da rivedere
	}

	public int getLocation() {
		return 0;
	}
	
	public EDefineType getDefineType() {
		return defineType;
	}

	public void setDefineType(EDefineType defineType) {
		this.defineType = defineType;
	}

	public ASTExpression getExp() {
		return exp;
	}

	public void setExp(ASTExpression exp) {
		this.exp = exp;
	}

	public int getTupleSize() {
		return tupleSize;
	}

	public void setTupleSize(int tupleSize) {
		this.tupleSize = tupleSize;
	}

	public EExpType getExpType() {
		return expType;
	}

	public void setExpType(EExpType expType) {
		this.expType = expType;
	}

	public boolean isNatural() {
		return isNatural;
	}

	public void setIsNatural(boolean isNatural) {
		this.isNatural = isNatural;
	}

	public List<ASTParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ASTParameter> parameters) {
		this.parameters = parameters;
	}

	public int getStackCount() {
		return stackCount;
	}

	public void setStackCount(int stackCount) {
		this.stackCount = stackCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getConfigDepth() {
		return configDepth;
	}

	public void setConfigDepth(int configDepth) {
		this.configDepth = configDepth;
	}
}