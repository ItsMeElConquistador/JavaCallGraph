package elcon.programs.callgraph.graph.nodes;

import java.lang.reflect.Method;

public class NodeMethod extends Node {
	
	public Method theMethod;
	
	public NodeMethod(Method theMethod) {
		this.theMethod = theMethod;
	}

	@Override
	public String getName() {
		return theMethod.getName();
	}

	@Override
	public String getDescription() {
		return "";
	}
}
