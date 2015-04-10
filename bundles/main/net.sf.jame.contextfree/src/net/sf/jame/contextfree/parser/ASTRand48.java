package net.sf.jame.contextfree.parser;

import java.util.Random;

class ASTRand48 {
	private static long RAND64_SEED  = 0x3DF41234;

    private long seed;
    private Random random = new Random();
//    private static final ASTRand48 common = new ASTRand48();

	public ASTRand48() {
		this(RAND64_SEED);
	}

	public ASTRand48(long seed) {
		this.seed = seed;
		random.setSeed(seed);
	}

	public double getDouble(boolean bump) {
		return random.nextDouble();
	}
	
	public long getLong(boolean bump) {
		return random.nextLong();
	}
	
	public long getPositive(boolean bump) {
		return random.nextLong();
	}
	
	public long getUnsigned(boolean bump) {
		return random.nextLong();
	}
    
	public void seed(long seed) {
		this.seed = seed;
		random.setSeed(this.seed);
	}
	
	public void init() {
		seed(RAND64_SEED);
	}
    
	public void xorString(String t, int[] i) {
	    for (int j = 0; j < t.length(); j++) {
	        xorChar(t.charAt(j), j);
	        bump();
	        i[0] = (i[0] + 1) & 7;
	    }
	}
    
    public void xorChar(char c, long i) {
        seed ^= ((long)c) << (i * 8);
	}
    
    public void bump() {
	}
}
