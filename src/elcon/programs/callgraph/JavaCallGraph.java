package elcon.programs.callgraph;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.ClassReader;

import elcon.programs.callgraph.graph.DirectedGraph;
import elcon.programs.callgraph.graph.edges.EdgeClassExtends;
import elcon.programs.callgraph.graph.edges.EdgeClassInterface;
import elcon.programs.callgraph.graph.nodes.Node;
import elcon.programs.callgraph.graph.nodes.NodeClass;

public class JavaCallGraph {

	public DirectedGraph<Node> graph;
	
	public JavaCallGraph(String packageName) {
		graph = new DirectedGraph<Node>();
		List<Class<?>> classes = getClassesInPackage(packageName);
		System.out.println("Classes found: ");
		for(Class<?> c : classes) {
			System.out.println("    " + c.getSimpleName());
		}
		System.out.println();
		for(Class<?> c : classes) {
			NodeClass node = getOrCreateNodeClass(c);
			if(!node.getVisited()) {
				graph.addNode(node);
				if(c.getSuperclass() != null) {
					node.superClass = getOrCreateNodeClass(c.getSuperclass());
					graph.addEdge(new EdgeClassExtends<Node>(node, node.superClass));
				}
				Class<?>[] interfaces = c.getInterfaces();
				if(interfaces != null) {
					for(int i = 0; i < interfaces.length; i++) {
						NodeClass n = getOrCreateNodeClass(interfaces[i]);
						node.interfaces.add(n);
						graph.addEdge(new EdgeClassInterface<Node>(node, n));
					}
				}
				try {
					ClassReader reader = new ClassReader(c.getName());
					ClassAnalyser analyser = new ClassAnalyser(node);
					reader.accept(analyser, 0);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public NodeClass getOrCreateNodeClass(Class<?> c) {
		for(Node node : graph.getNodes()) {
			if(node instanceof NodeClass && ((NodeClass) node).theClass == c) {
				return (NodeClass) node;
			}
		}
		return new NodeClass(c);
	}

	public List<Class<?>> getClassesInPackage(String packageName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		LinkedList<Class<?>> classList = new LinkedList<Class<?>>();
		Enumeration<URL> resources;
		try {
			resources = classLoader.getResources(packageName.replace('.', '/'));
			while(resources.hasMoreElements()) {
				File file = new File(resources.nextElement().getFile());
				if(file.isDirectory()) {
					File[] subFiles = file.listFiles();
					for(int i = 0; i < subFiles.length; i++) {
						String subFileName = subFiles[i].getName();
						if(subFiles[i].isDirectory()) {
							classList.addAll(getClassesInPackage(packageName + "." + subFileName));
						} else if(subFileName.endsWith(".class")) {
							try {
								classList.add(Class.forName(packageName + "." + subFileName.substring(0, subFileName.length() - 6)));
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	public static void main(String[] args) {
		new JavaCallGraph("elcon.programs.callgraph");
	}
}
