package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.SeqExp;

public class Lista extends E {

	private SeqExp seq;
	public Lista(SeqExp seq) {
		this.seq = seq;
	}
	
	public String toString() {
		return "lista("+seq.toString()+")";
	}
	
	public KindE kind() {
		return KindE.LISTA;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.seq.bind(pila);
	}

}
