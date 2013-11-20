package elcon.programs.callgraph.graph.edges;

public class Edge<N> {

	public N from;
	public N to;
	private String label;
	
	public Edge(N from, N to) {
		this.from = from;
		this.to = to;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Edge<?>)) {
			return false;
		}
		Edge<?> edge = (Edge<?>) object;
		return from.equals(edge.from) && to.equals(edge.to);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Edge from %s to %s", from, to));
		return sb.toString();
	}
}
