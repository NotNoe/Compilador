package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;

public class Switch extends Instruccion {
	
	private E opnd1;
	private Cuerpo_Switch opnd2;
	
	

	public Switch(E opnd1, Cuerpo_Switch opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		return "switch("+opnd1.toString()+ "," + opnd2.toString();
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		this.opnd2.bind(pila);
	}
	
	
	@Override
	public KindI kindI() {
		return KindI.SWITCH;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public Cuerpo_Switch getOpnd2() {
		return opnd2;
	}
	
	

}
