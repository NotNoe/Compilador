package ast.Designadores;

import ast.Expresiones.E;
import ast.Expresiones.Llamada;

public class Punto extends Designador {
	
	private Designador opnd1;
	private E opnd2;
	
	public Punto(Designador opnd1, Identificador opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public Punto(Designador opnd1, Llamada opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		return "punto("+this.opnd1.toString()+","+this.opnd2.toString()+")";
	}

	@Override
	public KindDes kindDes() {
		return KindDes.PUNTO;
	}

	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	
	
}
