package elcon.programs.callgraph.graph.nodes;

import java.lang.reflect.Field;

public class NodeField extends Node {

	public Field theField;
	
	public NodeField(Field theField) {
		this.theField = theField;
	}
	
	@Override
	public String getName() {
		return theField.getName();
	}
	
	@Override
	public String getDescription() {
		return "";
	}
}
