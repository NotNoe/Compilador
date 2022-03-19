package ast.Instrucciones;

import ast.NodeKind;

public class Default extends SwitchCase {
	
	private BloqueInstrucciones opnd1;

	public Default(BloqueInstrucciones opnd1) {
		super();
		this.opnd1 = opnd1;
	}

	public String toString() {
		return "default("+opnd1.toString()+")";
	}
	
	
	public NodeKind nodeKind() {
		return NodeKind.DEFAULT;
	}

	public BloqueInstrucciones getOpnd1() {
		return opnd1;
	}
	
	

}
