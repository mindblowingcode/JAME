package net.sf.jame.contextfree.parser;

enum EFuncType {
	IllegalArguments("", ""), 
	NotAFunction("", ""), 
	Cos("cos", "\u00A1\u00E7\u009C\u001A\u00AF\u007D"), 
	Sin("sin", "\u00AF\u0058\u00FE\u002C\u00D4\u0053"), 
	Tan("tan", "\u0095\u00FF\u0059\u0011\u0003\u0002"), 
	Cot("cot", "\u0077\u00F5\u00B6\u0035\u008C\u00F0"), 
	Acos("acos", "\u003A\u00CD\u0079\u003E\u00AD\u00B4"), 
	Asin("asin", "\u001D\u0075\u000B\u00BC\u005F\u0052"), 
	Atan("atan", "\u000B\u00C8\u0089\u00AB\u00F8\u00B7"), 
	Acot("acot", "\u0069\u007C\u00C7\u001A\u00F6\u007B"), 
	Cosh("cosh", "\u0048\u0043\u0043\u0035\u0062\u0081"), 
	Sinh("sinh", "\u0051\u0062\u00FB\u0076\u00ED\u009C"), 
	Tanh("tanh", "\u00BB\u0091\u0054\u00A9\u0063\u0084"), 
	Acosh("acosh", "\u004F\u0028\u0048\u0020\u00B7\u005B"), 
	Asinh("asinh", "\u006C\u009B\u0032\u00AA\u004C\u00D0"), 
	Atanh("atanh", "\u0058\u00EC\u00BB\u0025\u00F8\u00B6"), 
	Log("log", "\u008E\u00B8\u0062\u00A1\u0075\u000F"), 
	Log10("log10", "\u004A\u006C\u00A3\u0002\u008B\u0080"), 
	Sqrt("sqrt", "\u0086\u007C\u00FC\u0020\u00CB\u0097"), 
	Exp("exp", "\u0088\u00A8\u0065\u00F0\u00C1\u0006"), 
	Abs("abs", "\u0041\u0089\u0018\u00D1\u00AD\u0082"), 
	Floor("floor", "\u00B7\u0028\u00D7\u00D7\u00A3\u00CC"), 
	Infinity("infinity", "\u002C\u0028\u0050\u00CC\u00DE\u0044"), 
	Atan2("atan2", "\u0099\u001B\u00C9\u00E0\u003F\u00A4"), 
	Mod("mod", "\u000F\u00E3\u00FE\u005F\u00BF\u00BF"), 
	Rand_Static("rand_static", "\u00C8\u00F7\u00E5\u003E\u0005\u00A3"), 
	Rand("rand", "\u00DA\u0018\u005B\u00E2\u00DB\u0079"), 
	Rand2("rand+/-", "\u00DC\u008D\u0009\u0015\u008A\u00C4"), 
	RandInt("randint", "\u0048\u0014\u004E\u0027\u0035\u002E");

	private String name;
	String entropy;

	private EFuncType(String name, String entropy) {
		this.name = name;
		this.entropy = entropy;
	}
	
	public static EFuncType getFuncTypeByName(String name) {
		for (EFuncType type : EFuncType.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return NotAFunction;
	}
}