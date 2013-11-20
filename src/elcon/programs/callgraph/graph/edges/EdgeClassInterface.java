package elcon.programs.callgraph.graph.edges;

public class EdgeClassInterface<N> extends Edge<N>  {

	public EdgeClassInterface(N from, N to) {
		super(from, to);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s has interface %s", from, to));
		return sb.toString();
	}
}
