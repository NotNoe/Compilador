package ast.instrucciones;

import ast.expresiones.E;

public class If extends Instruccion {

	private E opnd1;
	private BloqueInstrucciones opnd2;
	private BloqueInstrucciones opnd3;
	
	
	
	public E getOpnd1() {
		return opnd1;
	}



	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}



	public BloqueInstrucciones getOpnd3() {
		if(opnd3 == null)
			throw new UnsupportedOperationException("opnd3");
		return opnd3;
	}



	public If(E opnd1, BloqueInstrucciones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}



	public If(E opnd1, BloqueInstrucciones opnd2, BloqueInstrucciones opnd3) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
	}


	public String toString() {
		String aux = "if(" + this.opnd1.toString() + "," + this.opnd2.toString();
		if(this.opnd3 != null)
			aux += "," + this.opnd3.toString();
		return aux + ")";
	}

	public KindI kindI() {
		return KindI.IF;
	}

}
