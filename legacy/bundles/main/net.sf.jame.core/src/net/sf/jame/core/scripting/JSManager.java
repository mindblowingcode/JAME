/*
 * JAME 6.2
 * http://jame.sourceforge.net
 *
 * Copyright 2001, 2015 Andrea Medeghini
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
package net.sf.jame.core.scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.jame.core.tree.Node;
import net.sf.jame.core.util.RenderContext;

import org.mozilla.javascript.ClassShutter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;

/**
 * @author Andrea Medeghini
 */
public class JSManager {
	/**
	 * 
	 */
	private JSManager() {
	}

	/**
	 * @param node
	 * @param basedir
	 * @param file
	 * @throws JSException
	 */
	public static void execute(final RenderContext renderContext, final JSContext jsContext, final Node node, final File basedir, final File file) throws JSException {
		try {
			execute(renderContext, jsContext, node, basedir, new FileInputStream(file), file.getName());
		}
		catch (FileNotFoundException x) {
			throw new JSException(x.getMessage(), x);
		}
	}

	/**
	 * @param node
	 * @param basedir
	 * @param is
	 * @throws JSException
	 */
	private static void execute(final RenderContext renderContext, final JSContext jsContext, final Node node, final File basedir, final InputStream is, final String name) throws JSException {
		try {
			Context context = new ContextFactory().enterContext();
			context.setClassShutter(new ClassShutterImpl());
			Scriptable scope = context.initStandardObjects();
			DefaultJSTree jsTree = new DefaultJSTree(renderContext, node);
			scope.put("context", scope, jsContext);
			scope.put("tree", scope, jsTree);
			String script = loadScript(basedir, is);
			context.evaluateString(scope, script, name, 1, null);
		}
		catch (Exception x) {
			x.printStackTrace();
			throw new JSException(x.getMessage(), x);
		}
		finally {
			Context.exit();
		}
	}

	private static String loadScript(final File basedir, final InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#include ")) {
					String fileName = line.substring(9);
					if (fileName.startsWith("\"") && fileName.endsWith("\"")) {
						fileName = fileName.substring(1, fileName.length() - 1);
						String script = loadScript(basedir, new FileInputStream(new File(basedir, fileName)));
						builder.append(script);
					}
				}
				else {
					builder.append(line);
				}
				builder.append("\n");
			}
			return builder.toString();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	private static class ClassShutterImpl implements ClassShutter {
		private List<String> whiteList = new LinkedList<String>();
		private List<String> blackList = new LinkedList<String>();

		/**
		 * 
		 */
		public ClassShutterImpl() {
			whiteList.add("org[.]mozilla[.]javascript[.].*");
			whiteList.add("net[.]sf[.]jame[.].*");
			whiteList.add("java[.]math[.].*");
			whiteList.add("java[.]lang[.]Math");
			whiteList.add("java[.]lang[.]Class");
			whiteList.add("java[.]lang[.]Object");
			whiteList.add("java[.]lang[.]Integer");
			whiteList.add("java[.]lang[.]Long");
			whiteList.add("java[.]lang[.]Short");
			whiteList.add("java[.]lang[.]Float");
			whiteList.add("java[.]lang[.]Double");
			whiteList.add("java[.]lang[.]String");
			whiteList.add("java[.]lang[.]Boolean");
			whiteList.add("java[.]util[.]ArrayList");
			whiteList.add("java[.]util[.]LinkedList");
			// whiteList.add("java[.]lang[.]reflect[.].*");
			// whiteList.add("java[.]lang[.].*");
			// whiteList.add("java[.]util[.].*");
			// whiteList.add("java[.]text[.].*");
			// blackList.add("java[.]lang[.]Thread.*");
			// blackList.add("java[.]lang[.]System.*");
			// blackList.add("java[.]lang[.]Runtime.*");
			// blackList.add("java[.]lang[.]Process.*");
			// blackList.add("java[.]lang[.]Compiler.*");
			// blackList.add("java[.]lang[.]ClassLoader.*");
			// blackList.add("java[.]util[.]concurrent.*");
			// blackList.add("java[.]util[.]zip.*");
			// blackList.add("java[.]util[.]jar.*");
			// blackList.add("java[.]util[.]prefs.*");
			// blackList.add("java[.]util[.]logging.*");
		}

		/**
		 * @see org.mozilla.javascript.ClassShutter#visibleToScripts(java.lang.String)
		 */
		public boolean visibleToScripts(String fullClassName) {
			for (String regexp : blackList) {
				if (Pattern.matches(regexp, fullClassName)) {
					return false;
				}
			}
			for (String regexp : whiteList) {
				if (Pattern.matches(regexp, fullClassName)) {
					return true;
				}
			}
			return false;
		}
	}
}
