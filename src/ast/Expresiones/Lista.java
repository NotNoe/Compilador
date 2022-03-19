package ast.Expresiones;

import ast.SeqExp;

public class Lista extends E {

	private SeqExp seq;
	public Lista(SeqExp seq) {
		this.seq = seq;
	}
	
	public String toString() {
		return "lista("+seq.toString()+")";
	}
	
	public KindE kind() {
		return KindE.LISTA;
	}

}
