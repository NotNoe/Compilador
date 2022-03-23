package ast.instrucciones;

import ast.expresiones.E;

public class Return extends Instruccion {

	private E opnd1;
	
	
	public Return(E opnd1) {
		this.opnd1 = opnd1;
	}

	
	public E getOpnd1() {
		return opnd1;
	}

	public String toString() {
		return "return(" + opnd1.toString() + ")";
	}

	public KindI kindI() {
		return KindI.RETURN;
	}

}
