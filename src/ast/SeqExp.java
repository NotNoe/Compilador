package ast;

import java.util.Map;
import java.util.Stack;

import ast.expresiones.E;

public class SeqExp implements ASTNode {

	private E izq;
	private SeqExp der;
	
	public SeqExp(E izq, SeqExp der) {
		this.izq = izq;
		this.der = der;
	}
	
	public SeqExp() {
		
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		if(this.izq != null) {
			this.izq.bind(pila);
			this.der.bind(pila);
		}
	}
	
	public String toString() {
		if(izq == null)
			return "seqExp()";
		else
			return "seqExp("+izq.toString()+","+der.toString()+")";
	}


	public NodeKind nodeKind() {
		return NodeKind.SEQ_EXPRE;
	}
}