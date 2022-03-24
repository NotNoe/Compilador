package ast.instrucciones;

import ast.designadores.Designador;

public class Delete extends Instruccion {

	private Designador opnd1;
	
	public String toString() {
		return "delete("+opnd1.toString()+")";
	}
	
	public Designador getOpnd1() {
		return opnd1;
	}



	public Delete(Designador opnd1) {
		this.opnd1 = opnd1;
	}



	public KindI kindI() {
		return KindI.DELETE;
	}

}
