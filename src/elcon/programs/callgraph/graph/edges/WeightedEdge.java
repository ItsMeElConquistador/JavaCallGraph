package elcon.programs.callgraph.graph.edges;

public class WeightedEdge<N, W> extends Edge<N> {

	public W weight;
	private String label;
	
	public WeightedEdge(N from, N to, W weight) {
		super(from, to);
		this.weight = weight;
	}

	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof WeightedEdge<?, ?>)) {
			return false;
		}
		WeightedEdge<?, ?> edge = (WeightedEdge<?, ?>) object;
		if(weight == null || edge.weight == null) {
			return from.equals(edge.from) && to.equals(edge.to);
		}
		return from.equals(edge.from) && to.equals(edge.to) && weight.equals(edge.weight);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("WeigtedEdge from %s to %s with weight: %s", from, to, weight));
		return sb.toString();
	}
}
