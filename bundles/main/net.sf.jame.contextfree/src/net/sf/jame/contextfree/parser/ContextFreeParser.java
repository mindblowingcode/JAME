package net.sf.jame.contextfree.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

import net.sf.jame.contextfree.ContextFreeConfig;
import net.sf.jame.contextfree.cfdg.CFDGConfigElement;
import net.sf.jame.contextfree.cfdg.analysis.DepthFirstAdapter;
import net.sf.jame.contextfree.cfdg.lexer.Lexer;
import net.sf.jame.contextfree.cfdg.lexer.LexerException;
import net.sf.jame.contextfree.cfdg.node.AAlphaBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.AArg0Function;
import net.sf.jame.contextfree.cfdg.node.AArg1Function;
import net.sf.jame.contextfree.cfdg.node.AArg2Function;
import net.sf.jame.contextfree.cfdg.node.ABackgroundDeclaration;
import net.sf.jame.contextfree.cfdg.node.ABrightnessBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.ACfdg;
import net.sf.jame.contextfree.cfdg.node.AColorPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AColorShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AComposedExpression2;
import net.sf.jame.contextfree.cfdg.node.ADefaultPathopPathOperation;
import net.sf.jame.contextfree.cfdg.node.ADefaultSimplePathOperation;
import net.sf.jame.contextfree.cfdg.node.ADefaultSingleShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.ADefaultSizeDeclaration;
import net.sf.jame.contextfree.cfdg.node.ADefaultTileDeclaration;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression2;
import net.sf.jame.contextfree.cfdg.node.AGeometryPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AGeometryShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AHueBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.AIncludeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AListMultiPathOperationBody;
import net.sf.jame.contextfree.cfdg.node.AListMultiShapeReplacementBody;
import net.sf.jame.contextfree.cfdg.node.AListPathOperation;
import net.sf.jame.contextfree.cfdg.node.AListShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.AMultiPathOperation;
import net.sf.jame.contextfree.cfdg.node.AMultiPathOperationDeclaration;
import net.sf.jame.contextfree.cfdg.node.AMultiShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.AMultiShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression2;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression2;
import net.sf.jame.contextfree.cfdg.node.AOrderedPathopPathOperation;
import net.sf.jame.contextfree.cfdg.node.AOrderedSimplePathOperation;
import net.sf.jame.contextfree.cfdg.node.AOrderedSingleShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.AOrderedSizeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AOrderedTileDeclaration;
import net.sf.jame.contextfree.cfdg.node.AParametersPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.APathDeclaration;
import net.sf.jame.contextfree.cfdg.node.APathFigureDeclaration;
import net.sf.jame.contextfree.cfdg.node.APathMultiPathOperationBody;
import net.sf.jame.contextfree.cfdg.node.APathPathOperationDeclaration;
import net.sf.jame.contextfree.cfdg.node.ARuleDeclaration;
import net.sf.jame.contextfree.cfdg.node.ARuleFigureDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASaturationBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.ASingleMultiShapeReplacementBody;
import net.sf.jame.contextfree.cfdg.node.ASingleShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASize3ShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AStartshapeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AStrokePathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AZShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.PBackgroundAdjustment;
import net.sf.jame.contextfree.cfdg.node.PExpression;
import net.sf.jame.contextfree.cfdg.parser.Parser;
import net.sf.jame.contextfree.cfdg.parser.ParserException;

public class ContextFreeParser {
	public ContextFreeConfig parseConfig(String text) throws ContextFreeParserException {
		return parseConfig(new StringReader(text));
	}

	public ContextFreeConfig parseConfig(File file) throws ContextFreeParserException {
		try {
			return parseConfig(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new ContextFreeParserException(e);
		}
	}

	public ContextFreeConfig parseConfig(Reader reader) throws ContextFreeParserException {
		try {
			Parser parser = new Parser(new Lexer(new PushbackReader(reader)));
			ContextFreeConfig config = new ContextFreeConfig();
			CFInterpreter interpreter = new CFInterpreter(config);
			parser.parse().apply(interpreter);
			return config;
		}
		catch (ParserException e) {
			throw new ContextFreeParserException(e);
		}
		catch (LexerException e) {
			throw new ContextFreeParserException(e);
		}
		catch (IOException e) {
			throw new ContextFreeParserException(e);
		}
	}

	public class CFInterpreter extends DepthFirstAdapter {
		private ContextFreeConfig config;
		
		public CFInterpreter(ContextFreeConfig config) {
			this.config = config;
		}

		/**
		 * 
		 */
		@Override
		public void inACfdg(ACfdg node) {
			config.setCFDG(new CFDGConfigElement());
		}

		/**
		 * 
		 */
		@Override
		public void inARuleFigureDeclaration(ARuleFigureDeclaration node) {
			System.out.println("RuleFigureDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAPathFigureDeclaration(APathFigureDeclaration node) {
			System.out.println("PathFigureDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAStartshapeDeclaration(AStartshapeDeclaration node) {
			System.out.println("StartshapeDeclaration " + node);
			config.getCFDG().setStartshape(node.getStartshape().getText());
		}

		/**
		 * 
		 */
		@Override
		public void inAIncludeDeclaration(AIncludeDeclaration node) {
			System.out.println("IncludeDeclaration " + node);
			try {
				ContextFreeConfig tmpConfig = parseConfig(new File(node.getFilename().getText()));
				System.out.println(tmpConfig);
			} catch (ContextFreeParserException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		@Override
		public void inABackgroundDeclaration(ABackgroundDeclaration node) {
			System.out.println("BackgroundDeclaration " + node);
			for (PBackgroundAdjustment adjustment : node.getBackgroundAdjustment()) {
				if (adjustment instanceof AHueBackgroundAdjustment) {
					float value = evaluateExpression(((AHueBackgroundAdjustment) adjustment).getExpression());
				} else if (adjustment instanceof ABrightnessBackgroundAdjustment) {
					float value = evaluateExpression(((ABrightnessBackgroundAdjustment) adjustment).getExpression());
				} else if (adjustment instanceof ASaturationBackgroundAdjustment) {
					float value = evaluateExpression(((ASaturationBackgroundAdjustment) adjustment).getExpression());
				} else if (adjustment instanceof AAlphaBackgroundAdjustment) {
					float value = evaluateExpression(((AAlphaBackgroundAdjustment) adjustment).getExpression());
				}
			}
			//config.getCFDG().setBackground(null);
		}

		/**
		 * 
		 */
		@Override
		public void inADefaultTileDeclaration(ADefaultTileDeclaration node) {
			System.out.println("DefaultTileDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAOrderedTileDeclaration(AOrderedTileDeclaration node) {
			System.out.println("OrderedTileDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inADefaultSizeDeclaration(ADefaultSizeDeclaration node) {
			System.out.println("DefaultSizeDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAOrderedSizeDeclaration(AOrderedSizeDeclaration node) {
			System.out.println("OrderedSizeDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAPathDeclaration(APathDeclaration node) {
			System.out.println("PathDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inARuleDeclaration(ARuleDeclaration node) {
			System.out.println("RuleDeclaration " + node);
		}

		/**
		 *
		 */
		@Override
		public void inAMultiShapeReplacementDeclaration(AMultiShapeReplacementDeclaration node) {
			System.out.println("MultiShapeReplacementDeclaration " + node);
		}

		/**
		 *
		 */
		@Override
		public void inASingleShapeReplacementDeclaration(ASingleShapeReplacementDeclaration node) {
			System.out.println("SingleShapeReplacementDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inADefaultSingleShapeReplacement(ADefaultSingleShapeReplacement node) {
			System.out.println("DefaultSingleShapeReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAOrderedSingleShapeReplacement(AOrderedSingleShapeReplacement node) {
			System.out.println("OrderedSingleShapeReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAListShapeReplacement(AListShapeReplacement node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAMultiShapeReplacement(AMultiShapeReplacement node) {
			System.out.println("MultiShapeReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inASingleMultiShapeReplacementBody(ASingleMultiShapeReplacementBody node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAListMultiShapeReplacementBody(AListMultiShapeReplacementBody node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAColorShapeAdjustment(AColorShapeAdjustment node) {
			System.out.println("ColorShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAGeometryShapeAdjustment(AGeometryShapeAdjustment node) {
			System.out.println("GeometryShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAZShapeAdjustment(AZShapeAdjustment node) {
			System.out.println("ZShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inASize3ShapeAdjustment(ASize3ShapeAdjustment node) {
			System.out.println("Size3ShapeAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAMultiPathOperationDeclaration(AMultiPathOperationDeclaration node) {
			System.out.println("MultiPathOperationDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inADefaultSimplePathOperation(ADefaultSimplePathOperation node) {
			System.out.println("DefaultSimplePathOperation " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAOrderedSimplePathOperation(AOrderedSimplePathOperation node) {
			System.out.println("OrderedSimplePathOperation " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inADefaultPathopPathOperation(ADefaultPathopPathOperation node) {
			System.out.println("DefaultPathopPathOperation " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAOrderedPathopPathOperation(AOrderedPathopPathOperation node) {
			System.out.println("OrderedPathopPathOperation " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAPathPathOperationDeclaration(APathPathOperationDeclaration node) {
			System.out.println("PathPathOperationDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAListPathOperation(AListPathOperation node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAMultiPathOperation(AMultiPathOperation node) {
			System.out.println("MultiPathOperation " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAPathMultiPathOperationBody(APathMultiPathOperationBody node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAListMultiPathOperationBody(AListMultiPathOperationBody node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAColorPathAdjustment(AColorPathAdjustment node) {
			System.out.println("ColorPathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAGeometryPathAdjustment(AGeometryPathAdjustment node) {
			System.out.println("GeometryPathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAStrokePathAdjustment(AStrokePathAdjustment node) {
			System.out.println("StrokePathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAParametersPathAdjustment(AParametersPathAdjustment node) {
			System.out.println("ParametersPathAdjustment " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg0Function(AArg0Function node) {
			System.out.println("Arg0Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg1Function(AArg1Function node) {
			System.out.println("Arg1Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAArg2Function(AArg2Function node) {
			System.out.println("Arg2Function " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANumberExpression(ANumberExpression node) {
			System.out.println("NumberExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANestedExpression(ANestedExpression node) {
			System.out.println("NestedExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAFunctionExpression(AFunctionExpression node) {
			System.out.println("FunctionExpression " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANumberExpression2(ANumberExpression2 node) {
			System.out.println("NumberExpression2 " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inANestedExpression2(ANestedExpression2 node) {
			System.out.println("NestedExpression2 " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAFunctionExpression2(AFunctionExpression2 node) {
			System.out.println("FunctionExpression2 " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAComposedExpression2(AComposedExpression2 node) {
			System.out.println("ComposedExpression2 " + node);
		}
		
		public float evaluateExpression(PExpression expression) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
