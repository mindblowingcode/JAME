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
package net.sf.jame.contextfree.test;

import java.io.FileReader;

import net.sf.jame.contextfree.parser.CFDGLexer;
import net.sf.jame.contextfree.parser.CFDGParser;
import net.sf.jame.contextfree.parser.CFDGParser.ChooseContext;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class TestCF3Parser {
	@Test
	public void parse() {
		try {
			ANTLRInputStream is = new ANTLRInputStream(new FileReader("cfdg/test1.cfdg"));
			CFDGLexer lexer = new CFDGLexer(is);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CFDGParser parser = new CFDGParser(tokens);
			ChooseContext choose = parser.choose();
            if (choose != null) {
//            	CommonTree tree = (CommonTree) parser.cfdg().getTree();
//            	System.out.println(tree.toStringTree());               
            }

//            CommonTreeNodeStream nodes = new CommonTreeNodeStream((Tree) r.getTree());
//            nodes.setTokenStream(tokens);
//            TP walker = new TP(nodes); // created from TP.g
//            TP.startRule_return r2 = walker.startRule();
//            CommonTree rt = ((CommonTree) r2.tree);
//            if (rt != null) System.out.println(((CommonTree) r2.getTree()).toStringTree());               
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
