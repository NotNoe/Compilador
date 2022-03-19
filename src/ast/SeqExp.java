package ast;

import ast.Expresiones.E;

public class SeqExp implements ASTNode {

	private E izq;
	private SeqExp der;
	
	public SeqExp(E izq, SeqExp der) {
		this.izq = izq;
		this.der = der;
	}
	
	public SeqExp() {
		
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