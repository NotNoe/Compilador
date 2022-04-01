package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;

public class Corchetes extends Designador {
	
	private Designador opnd1;
	private E opnd2;

	
	public Corchetes(Designador opnd1, E opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		this.opnd2.bind(pila);
	}
	
	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	public String toString() {
		return "corchetes("+this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}


	@Override
	public KindDes kindDes() {
		return KindDes.CORCHETES;
	}

}
