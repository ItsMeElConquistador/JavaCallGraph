package elcon.programs.callgraph.graph.edges;

public class EdgeClassExtends<N> extends Edge<N>  {

	public EdgeClassExtends(N from, N to) {
		super(from, to);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s extends %s", from, to));
		return sb.toString();
	}
}
