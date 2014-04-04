package net.sf.jame.contextfree.parser;

import java.util.List;

class ASTRepContainer {
	private EPathOp pathOp;
	private int repType;
	private List<ASTReplacement> body;
	private List<ASTParameter> parameters;
	boolean isGlobal;
	private int stackCount;

	public void setStackCount(int stackCount) {
		this.stackCount = stackCount;
	}

	public EPathOp getPathOp() {
		return pathOp;
	}

	public int getRepType() {
		return repType;
	}

	public List<ASTReplacement> getBody() {
		return body;
	}

	public List<ASTParameter> getParameters() {
		return parameters;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public int getStackCount() {
		return stackCount;
	}

	public void addParameter(String type, int nameIndex) {
		// TODO Auto-generated method stub
		
	}

	public void addDefParameter(int nameIndex, ASTDefine def) {
		// TODO Auto-generated method stub
		
	}

	public void setRepType(int repType) {
		this.repType = repType;
	}

	public void setPathOp(EPathOp pathOp) {
		this.pathOp = pathOp;
	}
}