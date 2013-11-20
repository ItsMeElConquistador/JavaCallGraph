package elcon.programs.callgraph.graph;

import elcon.programs.callgraph.graph.edges.WeightedEdge;

public class WeightedDirectedGraph<N, W> extends WeightedGraph<N, W> {

	@Override
	public void addEdge(N from, N to, W weight) {
		if(containsNode(from) && containsNode(to) && !containsEdge(from, to)) {
			nodes.get(from).add(new WeightedEdge<N, W>(from, to, weight));
		}
	}

	
	public void addEdge(WeightedEdge<N, W> edge) {
		if(containsNode(edge.from) && containsNode(edge.to) && !containsEdge(edge.from, edge.to)) {
			nodes.get(edge.from).add(edge);
		}
	}
}
