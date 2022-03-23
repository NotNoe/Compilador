package ast.externos.util;

import ast.ASTNode;
import ast.KindP;
import ast.NodeKind;

public class Parametros implements ASTNode {

	private KindP kind;
	private Parametro opnd1;
	private Parametros opnd2;
	
	public Parametros() {
		this.kind = KindP.VACIO;
	}
	
	
	
	public Parametros(Parametro opnd1, Parametros opnd2) {
		this.kind = KindP.NO_VACIO;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}


	public Parametro getOpnd1() {
		return opnd1;
	}



	public Parametros getOpnd2() {
		return opnd2;
	}



	public KindP kindP() {
		return this.kind;
	}
	
	public NodeKind nodeKind() {
		return NodeKind.PARAMETROS;
	}

}
