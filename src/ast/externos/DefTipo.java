package ast.externos;

import ast.designadores.Identificador;
import ast.externos.util.KindExt;
import ast.tipo.Tipo;

public class DefTipo implements Externo {

	private Tipo opnd1;
	private Identificador opnd2;
	
	
	
	public Tipo getOpnd1() {
		return opnd1;
	}



	public Identificador getOpnd2() {
		return opnd2;
	}



	public DefTipo(Tipo opnd1, Identificador opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public String toString() {
		return "defTipo(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}

	public KindExt kindExt() {
		return KindExt.DEF_TIPO;
	}

}
