package ast.externos;

import ast.designadores.Identificador;
import ast.externos.util.Declaraciones;
import ast.externos.util.KindExt;

public class DefStruct implements Externo {

	private Declaraciones opnd1;
	private Identificador opnd2;
	
	
	
	public DefStruct(Declaraciones opnd1, Identificador opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	
	public String toString() {
		return "defStruct(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}


	public Declaraciones getOpnd1() {
		return opnd1;
	}




	public Identificador getOpnd2() {
		return opnd2;
	}




	public KindExt kindExt() {
		return KindExt.DEF_STRUCT;
	}

}
