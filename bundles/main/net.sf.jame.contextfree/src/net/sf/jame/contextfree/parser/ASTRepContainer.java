package net.sf.jame.contextfree.parser;

import java.util.List;

class ASTRepContainer {
	private PathOpEnum pathOp;
	private int repType;
	private List<ASTReplacement> body;
	private List<ASTParameter> parameters;
	boolean isGlobal;
	private int stackCount;

	public PathOpEnum getPathOp() {
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
}