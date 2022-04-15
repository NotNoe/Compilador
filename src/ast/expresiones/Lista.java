package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.SeqExp;
import ast.tipo.Tipo;

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

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		seq.type(funcion, val_switch, current_class);
		this.tipo = seq.tipo;
	}

}
