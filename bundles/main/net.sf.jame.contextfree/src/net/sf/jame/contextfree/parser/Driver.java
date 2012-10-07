package net.sf.jame.contextfree.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;

public class Driver {
    private String basedir = System.getProperty("user.home", "/");
	
	public Driver() {
	}

	public static void createStatement(Object a) {
		System.out.println(a);
	}

	public ASTRepContainer paramDecls = new ASTRepContainer();
	public List<ASTExpression> canonicalMods = new ArrayList<ASTExpression>();
	public Stack<ASTRepContainer> containerStack = new Stack<ASTRepContainer>();
	public Stack<ASTSwitch> switchStack = new Stack<ASTSwitch>();
	public Map<String, Integer> flagNames = new HashMap<String, Integer>();
	public boolean inPathContainer;
	public String currentPath;
	public int pathCount;
	public int currentShape;
	public int localStackDepth;
	public ASTRand48 seed;
	public int includeDepth;
	public int[] declDepth = new int[5];

	public void includeFile(String name) {
		try {
			CharStream inputstream = null;
			inputstream = new ANTLRFileStream(basedir + "/" + name);
			CFDGLexer innerlexer = new CFDGLexer(inputstream);
			// CFDGParser innerparser = new CFDGParser(new
			// CommonTokenStream(innerlexer));
			// innerparser.driver = Driver.this;
			// CommonTree includetree =
			// (CommonTree)(innerparser.cfdg().getTree());
		} catch (Exception fnf) {
			throw new Error("Cannot open file " + name);
		}
	}

	public void initialize(ASTReplacement initial) {
		debug("Initialize replacement " + initial.getName());
	}

	public void background(ASTReplacement background) {
	}

	public void debug(String message) {
		System.out.println(message);
	}

	public ASTExpression checkModification(ASTExpression expression) {
		return expression;
	}

	public void nextParameterDecl(String type, String var) {
	}

	public void buildTileTransform(ASTReplacement tile) {
	}

	public void buildSizeTransform(ASTReplacement size) {
	}

	public void setShape(ASTShape shape) {
	}

	public void addRule(ASTRule rule) {
	}

	public int stringToShape(String name) {
		return 0;
	}

	public void pushRep(ASTReplacement rep, boolean global) {
	}

	public void popRepContainer(ASTReplacement rep) {
	}

	public void pushRepContainer(ASTRepContainer c) {
	}

	public void processRepContainer(ASTRepContainer c) {
	}

	public String pushParam() {
		return null;
	}

	public ASTParameter findExpression(int nameIndex) {
		return null;
	}

	public ASTRuleSpecifier makeRuleSpecifier(String name, ASTExpression modification) {
		return new ASTRuleSpecifier(name, -1);// TODO controllare
	}

	public ASTExpression makeVariable(String name) {
		return null;
	}

	public ASTExpression makeFunction(String name, ASTExpression arguments,	boolean consAllowed) {
		return null;
	}

	public ASTReplacement makeElement(String command, ASTExpression modification, ASTExpression parameter, boolean subPath) {
		return null;
	}

	public ASTExpression nextParameter(String name, ASTExpression expression) {
		return null;
	}

	public ASTExpression makeModTerm(ModTypeEnum type, String modification) {
		return null;
	}

	public ASTExpression makeModTerm(String type, ASTExpression modification) {
		return null;
	}

	public void retroPath(ASTRule rule) {
	}

	public Stack<ASTSwitch> switchStack() {
		return switchStack;
	}

	public void inColor() {
	}

	public void timeWise() {
	}
}
