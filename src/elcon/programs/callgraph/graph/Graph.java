package elcon.programs.callgraph.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import elcon.programs.callgraph.graph.edges.Edge;

public class Graph<N> implements IGraph<N> {

	public HashMap<N, LinkedList<Edge<N>>> nodes = new HashMap<N, LinkedList<Edge<N>>>();
	
	@Override
	public void addNode(N node) {
		if(!containsNode(node)) {
			nodes.put(node, new LinkedList<Edge<N>>());
		}
	}
	
	@Override
	public void addEdge(N from, N to) {
		if(containsNode(from) && containsNode(to) && !containsEdge(from, to)) {
			nodes.get(from).add(new Edge<N>(from, to));
			nodes.get(to).add(new Edge<N>(to, from));
		}
	}

	@Override
	public boolean containsNode(N node) {
		return nodes.containsKey(node);
	}
	
	@Override
	public boolean containsEdge(N from, N to) {
		if(containsNode(from) && containsNode(to)) {
			return nodes.get(from).contains(new Edge<N>(from, to));
		}
		return false;
	}

	@Override
	public boolean containsEdge(Edge<N> edge) {
		if(containsNode(edge.from) && containsNode(edge.to)) {
			return nodes.get(edge.from).contains(edge);
		}
		return false;
	}
	
	@Override
	public void removeNode(N node) {
		if(nodes.containsKey(node)) {
			removeEdgesFrom(node);
			removeEdgesTo(node);
			nodes.remove(node);
		}
	}
	
	@Override
	public void removeEdge(N from, N to) {
		if(containsNode(from) && containsNode(to)) {
            nodes.get(from).remove(new Edge<N>(to, from));
        }
	}
	
	@Override
	public void removeEdge(Edge<N> edge) {
		if(containsNode(edge.from) && containsNode(edge.to)) {
            nodes.get(edge.from).remove(edge);
        }
	}
	
	@Override
	public void removeEdgesFrom(N from) {
		if(containsNode(from)) {
			nodes.get(from).clear();
		}
	}
	
	@Override
	public void removeEdgesTo(N to) {
		if(containsNode(to)) {
			for(N node : nodes.keySet()) {
				List<Edge<N>> edgesFrom = edgesFrom(node);
				for(Edge<N> edge : edgesFrom) {
					if(edge.to.equals(to)) {
						nodes.get(node).remove(edge);
					}
				}
			}
		}
	}
	
	@Override
	public void removeEdgesBetween(N from, N to) {
		if(containsNode(from) && containsNode(to)) {
			for(Edge<N> edge : edgesFrom(from)) {
				if(edge.to.equals(to)) {
					nodes.get(from).remove(edge);
				}
			}			
			for(Edge<N> edge : edgesFrom(to)) {
				if(edge.to.equals(from)) {
					nodes.get(to).remove(edge);
				}
			}
		}
	}

	@Override
	public List<N> getNodes() {
		LinkedList<N> list = new LinkedList<N>();
		if(nodes.keySet() != null) {
			for(N node : nodes.keySet()) {
				list.add(node);
			}
		}
		return list;
	}
	
	@Override
	public List<Edge<N>> edgesFrom(N... fromList) {
		LinkedList<Edge<N>> list = new LinkedList<Edge<N>>();
		if(fromList.length == 1) {
			N from = fromList[0];
			if(containsNode(from)) {
				for(Edge<N> edge : nodes.get(from)) {
					list.add(edge);
				}
			}
		} else if(fromList.length > 1) {
			for(int i = 0; i < fromList.length; i++) {
				edgesFrom(fromList[i]);
			}
		}
		return list;
	}
	
	@Override
	public List<Edge<N>> edgesTo(N... toList) {
		LinkedList<Edge<N>> list = new LinkedList<Edge<N>>();
		if(toList.length == 1) {
			N to = toList[0];
			if(containsNode(to)) {
				for(N node : getNodes()) {
					if(!node.equals(to)) {
						List<Edge<N>> edgesFrom = edgesFrom(node);
						for(Edge<N> edge : edgesFrom) {
							if(edge.to.equals(to)) {
								list.add(edge);
							}
						}
					}
				}
			}
		} else if(toList.length > 1) {
			for(int i = 0; i < toList.length; i++) {
				edgesTo(toList[i]);
			}
		}
		return list;
	}
	
	@Override
	public List<Edge<N>> edgesBetween(N from, N to) {
		LinkedList<Edge<N>> list = new LinkedList<Edge<N>>();
		if(containsNode(from)) {
			List<Edge<N>> edgesFrom = edgesFrom(from);
			for(Edge<N> edge : edgesFrom) {
				if(edge.to.equals(to)) {
					list.add(edge);
				}
			}
		}
		return list;
	}

	@Override
	public Iterator<N> iterator() {
		return getNodes().iterator();
	}
	
	@Override
	public int size() {
		return nodes.size();
	}
	
	@Override
	public boolean isEmpty() {
		return nodes.isEmpty();
	}
	
	@Override
	public String toString() {
		return nodes.toString();
	}
}
