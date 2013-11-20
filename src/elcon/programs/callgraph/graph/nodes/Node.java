package elcon.programs.callgraph.graph.nodes;

public abstract class Node {

	private String label;
	private boolean visited;
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
