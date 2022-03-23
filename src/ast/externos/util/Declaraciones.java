package ast.externos.util;

import ast.ASTNode;
import ast.NodeKind;
import ast.instrucciones.Declaracion;

public class Declaraciones implements ASTNode {

	private KindD kind;
	private Declaracion opnd1;
	private Declaraciones opnd2;
	
	public Declaraciones() {
		this.kind = KindD.VACIO;
	}
	
	
	
	public Declaraciones(Declaracion opnd1, Declaraciones opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.kind = KindD.NO_VACIO;
	}
	
	



	public KindD kindD() {
		return kind;
	}



	public Declaracion getOpnd1() {
		return opnd1;
	}



	public Declaraciones getOpnd2() {
		return opnd2;
	}



	public NodeKind nodeKind() {
		return NodeKind.DECLARACIONES;
	}

}
