package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.expresiones.E;

public class Case extends SwitchCase {
	
	private E opnd1;
	private BloqueInstrucciones opnd2;
	
	

	public Case(E opnd1, BloqueInstrucciones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		pila.push(new HashMap<String, ASTNode>());
		this.opnd2.bind(pila);
		pila.pop();
	}
	
	public String toString() {
		return "case("+opnd1.toString()+","+opnd2.toString()+")";
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.CASE;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}
	
	

}
