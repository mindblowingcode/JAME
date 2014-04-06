package net.sf.jame.contextfree.parser;

import java.util.ArrayDeque;
import java.util.Queue;

public class ASTCompiledPath {
	private static Long globalPathUID = new Long(100);
	private boolean complete;
	private PathStorage pathStorage = new PathStorage();
	private Queue<CommandInfo> commandInfo = new ArrayDeque<CommandInfo>();
	private ASTPathCommand terminalCommand = new ASTPathCommand();
	private boolean useTerminal;
	private StackRule parameters;
	private Long pathUID;
	
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
