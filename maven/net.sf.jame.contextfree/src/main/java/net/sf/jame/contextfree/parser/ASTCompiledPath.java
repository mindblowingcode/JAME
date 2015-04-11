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
package net.sf.jame.contextfree.parser;

import java.util.ArrayDeque;
import java.util.Queue;

import org.antlr.v4.runtime.Token;

public class ASTCompiledPath {
	private static Long globalPathUID = new Long(100);
	private boolean complete;
	private PathStorage pathStorage;
	private Queue<CommandInfo> commandInfo;
	private ASTPathCommand terminalCommand;
	private boolean useTerminal;
	private StackRule parameters;
	private Long pathUID;
	
	public ASTCompiledPath(Token location) {
		pathStorage = new PathStorage();
		commandInfo = new ArrayDeque<CommandInfo>();
		terminalCommand = new ASTPathCommand(location);
	}
	
	public StackRule getParameters() {
		return parameters;
	}

	public void setParameters(StackRule parameters) {
		this.parameters = parameters;
	}

	public Queue<CommandInfo> getCommandInfo() {
		return commandInfo;
	}

	public PathStorage getPath() {
		return pathStorage;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setIsComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean useTerminal() {
		return useTerminal;
	}

	public void setUseTerminal(boolean useTerminal) {
		this.useTerminal = useTerminal;
	}

	public void finish(boolean b, RTI rti) {
		// TODO da copletare
	}
	
	public ASTPathCommand getTerminalCommand() {
		return terminalCommand;
	}

	public static Long nextPathUID() {
		return globalPathUID = new Long(globalPathUID.longValue() + 1);
	}

	public void setPathUID(Long pathUID) {
		this.pathUID = pathUID;
	}
	
	public Long getPathUID() {
		return pathUID;
	}

	public void addPathOp(ASTPathOp pathOp, double[] opData, Shape parent, boolean tr, RTI rti) {
		// TODO da copletare
	}
}
