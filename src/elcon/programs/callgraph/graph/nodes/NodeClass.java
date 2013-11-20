package elcon.programs.callgraph.graph.nodes;

import java.util.LinkedList;

public class NodeClass extends Node {
	
	public Class<?> theClass;
	
	public NodeClass superClass;
	public LinkedList<NodeClass> interfaces = new LinkedList<NodeClass>();
	
	public LinkedList<NodeField> fields = new LinkedList<NodeField>();
	public LinkedList<NodeMethod> methods = new LinkedList<NodeMethod>();
	
	public NodeClass(Class<?> theClass) {
		this.theClass = theClass;
	}
	
	@Override
	public String getName() {
		return theClass.getSimpleName();
	}
	
	@Override
	public String getDescription() {
		return "";
	}
}
