package net.sf.jame.contextfree.parser;

class ASTStackRule {
    private int ruleName;
    private long paramCount;
    
	public ASTStackRule(int ruleName, long paramCount) {
		// TODO controllare
		this.ruleName = ruleName;
		this.paramCount = paramCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (paramCount ^ (paramCount >>> 32));
		result = prime * result + ruleName;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ASTStackRule other = (ASTStackRule) obj;
		if (paramCount != other.paramCount)
			return false;
		if (ruleName != other.ruleName)
			return false;
		return true;
	}

	public int getRuleName() {
		return ruleName;
	}

	public long getParamCount() {
		return paramCount;
	}

	public void setRuleName(int ruleName) {
		this.ruleName = ruleName;
	}

	public void setParamCount(long paramCount) {
		this.paramCount = paramCount;
	}
}
