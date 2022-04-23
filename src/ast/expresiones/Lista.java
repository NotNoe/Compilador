package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.SeqExp;
import ast.tipo.Array;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;

public class Lista extends E {

	private SeqExp seq;
	public Lista(SeqExp seq, int fila, int columna) {
		super(fila, columna);
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
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException {
		seq.type(funcion, val_switch, current_class, continuable, breakeable);
		this.tipo = new Array(seq.tipo);
	}

}
