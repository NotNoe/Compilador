package ast.instrucciones;

import ast.expresiones.E;

public class While extends Instruccion {
	
	private E opnd1;
	private BloqueInstrucciones opnd2;
	
	

	public While(E opnd1, BloqueInstrucciones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public String toString() {
		return "while("+opnd1.toString() + "," + opnd2.toString() + ")";
	}

	@Override
	public KindI kindI() {
		return KindI.INSTRUCCION;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}
	
	

}
