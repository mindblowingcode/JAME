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
package net.sf.jame.devtools.processor.extension;

import java.io.File;
import java.util.Map;

import net.sf.jame.core.extension.ExtensionRuntime;
import net.sf.jame.devtools.DevToolsException;
import net.sf.jame.devtools.ProcessorParameters;

/**
 * @author Andrea Medeghini
 */
public abstract class ProcessorExtensionRuntime extends ExtensionRuntime {
	/**
	 * @param path
	 * @param parameters
	 * @param variables
	 * @throws DevToolsException
	 */
	public abstract void process(File path, ProcessorParameters parameters, Map<String, String> variables) throws DevToolsException;
	
	/**
	 * @return
	 */
	public abstract String getName();
}
