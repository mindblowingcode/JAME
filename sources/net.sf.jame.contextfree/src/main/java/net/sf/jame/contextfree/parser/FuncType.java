package net.sf.jame.contextfree.parser;

enum FuncType {
	IllegalArguments(-2, "", ""), 
	NotAFunction(-1, "", ""), 
	Cos(0, "cos", "\u00A1\u00E7\u009C\u001A\u00AF\u007D"), 
	Sin(1, "sin", "\u00AF\u0058\u00FE\u002C\u00D4\u0053"), 
	Tan(2, "tan", "\u0095\u00FF\u0059\u0011\u0003\u0002"), 
	Cot(3, "cot", "\u0077\u00F5\u00B6\u0035\u008C\u00F0"), 
	Acos(4, "acos", "\u003A\u00CD\u0079\u003E\u00AD\u00B4"), 
	Asin(5, "asin", "\u001D\u0075\u000B\u00BC\u005F\u0052"), 
	Atan(6, "atan", "\u000B\u00C8\u0089\u00AB\u00F8\u00B7"), 
	Acot(7, "acot", "\u0069\u007C\u00C7\u001A\u00F6\u007B"), 
	Cosh(8, "cosh", "\u0048\u0043\u0043\u0035\u0062\u0081"), 
	Sinh(9, "sinh", "\u0051\u0062\u00FB\u0076\u00ED\u009C"), 
	Tanh(10, "tanh", "\u00BB\u0091\u0054\u00A9\u0063\u0084"), 
	Acosh(11, "acosh", "\u004F\u0028\u0048\u0020\u00B7\u005B"), 
	Asinh(12, "asinh", "\u006C\u009B\u0032\u00AA\u004C\u00D0"), 
	Atanh(13, "atanh", "\u0058\u00EC\u00BB\u0025\u00F8\u00B6"), 
	Log(14, "log", "\u008E\u00B8\u0062\u00A1\u0075\u000F"), 
	Log10(15, "log10", "\u004A\u006C\u00A3\u0002\u008B\u0080"), 
	Sqrt(16, "sqrt", "\u0086\u007C\u00FC\u0020\u00CB\u0097"), 
	Exp(17, "exp", "\u0088\u00A8\u0065\u00F0\u00C1\u0006"), 
	Abs(18, "abs", "\u0041\u0089\u0018\u00D1\u00AD\u0082"), 
	Floor(19, "floor", "\u00B7\u0028\u00D7\u00D7\u00A3\u00CC"), 
	Infinity(20, "infinity", "\u002C\u0028\u0050\u00CC\u00DE\u0044"), 
	Atan2(21, "atan2", "\u0099\u001B\u00C9\u00E0\u003F\u00A4"), 
	Mod(22, "mod", "\u000F\u00E3\u00FE\u005F\u00BF\u00BF"), 
	Rand_Static(23, "rand_static", "\u00C8\u00F7\u00E5\u003E\u0005\u00A3"), 
	Rand(24, "rand", "\u00DA\u0018\u005B\u00E2\u00DB\u0079"), 
	Rand2(25, "rand+/-", "\u00DC\u008D\u0009\u0015\u008A\u00C4"), 
	RandInt(26, "randint", "\u0048\u0014\u004E\u0027\u0035\u002E"),
	LastOne(27, "", "");

	int ordinal;
	private String name;
	String entropy;

	private FuncType(int ordinal, String name, String entropy) {
		this.ordinal = ordinal; 
		this.name = name;
		this.entropy = entropy;
	}
	
	public static FuncType getFuncTypeByName(String name) {
		for (FuncType type : FuncType.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return NotAFunction;
	}
}