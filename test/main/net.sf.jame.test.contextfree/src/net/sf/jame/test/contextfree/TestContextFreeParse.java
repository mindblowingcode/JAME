/*
 * JAME 6.1 
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
 *
 * This file is part of JAME.
 *
 * JAME is an application for creating fractals and other graphics artifacts.
 *
 * JAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAME.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.sf.jame.test.contextfree;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import net.sf.jame.contextfree.cfdg.analysis.DepthFirstAdapter;
import net.sf.jame.contextfree.cfdg.lexer.Lexer;
import net.sf.jame.contextfree.cfdg.lexer.LexerException;
import net.sf.jame.contextfree.cfdg.node.AArg0Function;
import net.sf.jame.contextfree.cfdg.node.AArg1Function;
import net.sf.jame.contextfree.cfdg.node.AArg2Function;
import net.sf.jame.contextfree.cfdg.node.ABackgroundDeclaration;
import net.sf.jame.contextfree.cfdg.node.ACfdg;
import net.sf.jame.contextfree.cfdg.node.AColorPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AColorShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AComposedExpression2;
import net.sf.jame.contextfree.cfdg.node.ADefaultPathopPathOperation;
import net.sf.jame.contextfree.cfdg.node.ADefaultShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.ADefaultSimplePathOperation;
import net.sf.jame.contextfree.cfdg.node.ADefaultSizeDeclaration;
import net.sf.jame.contextfree.cfdg.node.ADefaultTileDeclaration;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression;
import net.sf.jame.contextfree.cfdg.node.AFunctionExpression2;
import net.sf.jame.contextfree.cfdg.node.AGeometryPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AGeometryShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AIncludeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AListLoopPathOperationBody;
import net.sf.jame.contextfree.cfdg.node.AListLoopShapeReplacementBody;
import net.sf.jame.contextfree.cfdg.node.AListPathOperation;
import net.sf.jame.contextfree.cfdg.node.AListShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.ALoopPathOperation;
import net.sf.jame.contextfree.cfdg.node.ALoopPathOperationDeclaration;
import net.sf.jame.contextfree.cfdg.node.ALoopShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.ALoopShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression;
import net.sf.jame.contextfree.cfdg.node.ANestedExpression2;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression;
import net.sf.jame.contextfree.cfdg.node.ANumberExpression2;
import net.sf.jame.contextfree.cfdg.node.AOrderedPathopPathOperation;
import net.sf.jame.contextfree.cfdg.node.AOrderedShapeReplacement;
import net.sf.jame.contextfree.cfdg.node.AOrderedSimplePathOperation;
import net.sf.jame.contextfree.cfdg.node.AOrderedSizeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AOrderedTileDeclaration;
import net.sf.jame.contextfree.cfdg.node.AParametersPathAdjustment;
import net.sf.jame.contextfree.cfdg.node.APathDeclaration;
import net.sf.jame.contextfree.cfdg.node.APathFigureDeclaration;
import net.sf.jame.contextfree.cfdg.node.APathLoopPathOperationBody;
import net.sf.jame.contextfree.cfdg.node.APathPathOperationDeclaration;
import net.sf.jame.contextfree.cfdg.node.ARuleDeclaration;
import net.sf.jame.contextfree.cfdg.node.ARuleFigureDeclaration;
import net.sf.jame.contextfree.cfdg.node.AShapeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AShapeLoopShapeReplacementBody;
import net.sf.jame.contextfree.cfdg.node.AShapeShapeReplacementDeclaration;
import net.sf.jame.contextfree.cfdg.node.ASize3ShapeAdjustment;
import net.sf.jame.contextfree.cfdg.node.AStartshapeDeclaration;
import net.sf.jame.contextfree.cfdg.node.AStrokePathAdjustment;
import net.sf.jame.contextfree.cfdg.node.AZShapeAdjustment;
import net.sf.jame.contextfree.cfdg.parser.Parser;
import net.sf.jame.contextfree.cfdg.parser.ParserException;

import org.junit.Test;

public class TestContextFreeParse {
	@Test
	public void parse() {
		String programAsText = "" +
				"startshape Foo\n" +
				"include stuff.cfdg\n" +
				"include \"stuff.cfdg\"\n" +
				"background { b -1 a (sin( 4 * tan()) * 5) }\n" +
				"tile { s 3 4 }\n" +
				"size { s 3 4 x 1 y 2}\n" +
				"rule Foo 0.1 {\n" +
				"SQUARE { x ((sin(45 * sin(5) )) + 6 * (6 / tan(6) - 9)) }\n" +
				"6 * { |a 1 s 1 3 sin(2) } SQUARE [ x tan(6) a 6| |sat 4 ]\n" +
				"2 * { s 1 } { CIRCLE [ a 6 ]\n TRI [ sat 4 ] }\n" +
				"}\n" +
				"";
		try {
			Parser parser = new Parser(new Lexer(new PushbackReader(new StringReader(programAsText))));
			CFInterpreter interp = new CFInterpreter();
			parser.parse().apply(interp);
		}
		catch (ParserException e) {
			e.printStackTrace();
		}
		catch (LexerException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class CFInterpreter extends DepthFirstAdapter {
		/**
		 * 
		 */
		@Override
		public void inACfdg(ACfdg node) {
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
		}

		/**
		 * 
		 */
		@Override
		public void inAIncludeDeclaration(AIncludeDeclaration node) {
			System.out.println("IncludeDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inABackgroundDeclaration(ABackgroundDeclaration node) {
			System.out.println("BackgroundDeclaration " + node);
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
		public void inAShapeDeclaration(AShapeDeclaration node) {
			System.out.println("ShapeDeclaration " + node);
		}

		/**
		 *
		 */
		@Override
		public void inALoopShapeReplacementDeclaration(ALoopShapeReplacementDeclaration node) {
			System.out.println("LoopShapeReplacementDeclaration " + node);
		}

		/**
		 *
		 */
		@Override
		public void inAShapeShapeReplacementDeclaration(AShapeShapeReplacementDeclaration node) {
			System.out.println("ShapeShapeReplacementDeclaration " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inADefaultShapeReplacement(ADefaultShapeReplacement node) {
			System.out.println("DefaultShapeReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAOrderedShapeReplacement(AOrderedShapeReplacement node) {
			System.out.println("OrderedShapeReplacement " + node);
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
		public void inALoopShapeReplacement(ALoopShapeReplacement node) {
			System.out.println("LoopShapeReplacement " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAShapeLoopShapeReplacementBody(AShapeLoopShapeReplacementBody node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAListLoopShapeReplacementBody(AListLoopShapeReplacementBody node) {
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
		public void inALoopPathOperationDeclaration(ALoopPathOperationDeclaration node) {
			System.out.println("LoopPathOperationDeclaration " + node);
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
		public void inALoopPathOperation(ALoopPathOperation node) {
			System.out.println("LoopPathOperation " + node);
		}

		/**
		 * 
		 */
		@Override
		public void inAPathLoopPathOperationBody(APathLoopPathOperationBody node) {
		}

		/**
		 * 
		 */
		@Override
		public void inAListLoopPathOperationBody(AListLoopPathOperationBody node) {
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
	}
}
