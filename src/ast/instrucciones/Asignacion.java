package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Designador;
import ast.expresiones.E;

public class Asignacion extends Instruccion {
	
	private Designador opnd1;
	private E opnd2;
	
	

	public Asignacion(Designador opnd1, E opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		return "asignacion("+opnd1.toString() + "," + opnd2.toString() + ")";
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		this.opnd2.bind(pila);
	}

	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	@Override
	public KindI kindI() {
		return KindI.ASIGNACION;
	}

}
