package ast.expresiones;

import ast.SeqExp;
import ast.designadores.Identificador;

public class Llamada extends E {

	private Identificador iden;
	private SeqExp seq;
	public Llamada(Identificador iden, SeqExp seq) {
		this.iden = iden;
		this.seq = seq;
	}
	
	public String toString() {
		return "llamada(" + this.iden.toString() + "," + this.seq.toString() + ")";
	}
	
	public KindE kind() {
		return KindE.LLAMADA;
	}

}
