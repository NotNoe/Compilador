package ast.externos;

import ast.designadores.Identificador;
import ast.externos.util.CuerpoClase;
import ast.externos.util.KindExt;

public class DefClase implements Externo {

	private CuerpoClase opnd1;
	private Identificador opnd2;
	
	
	
	public DefClase(CuerpoClase opnd1, Identificador opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	



	public CuerpoClase getOpnd1() {
		return opnd1;
	}





	public Identificador getOpnd2() {
		return opnd2;
	}

	public String toString() {
		return "defClase(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}



	public KindExt kindExt() {
		return KindExt.DEF_CLASE;
	}

}
