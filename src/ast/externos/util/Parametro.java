package ast.externos.util;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.designadores.Identificador;
import ast.tipo.Tipo;

public class Parametro implements ASTNode {

	private Tipo opnd1;
	private boolean ref;
	private Identificador opnd2;
	
	
	
	public Parametro(Tipo opnd1, boolean ref, Identificador opnd2) {
		super();
		this.opnd1 = opnd1;
		this.ref = ref;
		this.opnd2 = opnd2;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		pila.peek().put(this.opnd2.getIden(), this);
	}



	public Tipo getOpnd1() {
		return opnd1;
	}




	public boolean isRef() {
		return ref;
	}




	public Identificador getOpnd2() {
		return opnd2;
	}


	public String toString() {
		if(this.ref)
			return "parametro(" + this.opnd1.toString() + ",&," + this.opnd2.toString() + ")";  
		else
			return "parametro(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}

	public NodeKind nodeKind() {
		return NodeKind.PARAMETRO;
	}

}
