package elcon.programs.callgraph;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import elcon.programs.callgraph.graph.nodes.NodeClass;

public class ClassAnalyser extends ClassVisitor {

	public NodeClass node;
	
	public ClassAnalyser(NodeClass node) {
		super(Opcodes.ASM4);
		this.node = node;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		
	}
}
