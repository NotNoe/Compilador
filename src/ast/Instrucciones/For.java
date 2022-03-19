package ast.Instrucciones;

import ast.Expresiones.E;

public class For extends Instruccion {
	
	private Declaracion opnd1;
	private E opnd2;
	private Instruccion opnd3;
	private BloqueInstrucciones opnd4;
	
	

	public For(Declaracion opnd1, E opnd2, Instruccion opnd3, BloqueInstrucciones opnd4) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	public String toString() {
		return "for("+opnd1.toString()+","+opnd2.toString()+","+opnd3.toString()+","+opnd4.toString()+")";
	}

	@Override
	public KindI kindI() {
		return KindI.FOR;
	}

	public Declaracion getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	public Instruccion getOpnd3() {
		return opnd3;
	}

	public BloqueInstrucciones getOpnd4() {
		return opnd4;
	}
	
	

}
