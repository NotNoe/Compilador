package ast.externos;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.util.KindP;

public class Programa implements ASTNode {

	private Externo opnd1;
	private Programa opnd2;
	private KindP kind;
	
	
	public Programa() {
		this.kind = KindP.VACIO;
	}
	
	public Programa(Externo opnd1, Programa opnd2) {
		this.kind = KindP.NO_VACIO;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public KindP kindP() {return this.kind;}
	
	public Externo getOpnd1() {
		return opnd1;
	}

	public Programa getOpnd2() {return this.opnd2;}



	public NodeKind nodeKind() {
		return NodeKind.PROGRAMA;
	}
	
	public String toString() {
		if(this.kind == KindP.VACIO)
			return "fin_programa()";
		else
			return "programa(" + opnd1.toString() + "," + opnd2.toString() + ")";
	}

}
