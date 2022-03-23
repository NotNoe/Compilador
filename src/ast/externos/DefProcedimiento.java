package ast.externos;

import ast.designadores.Identificador;
import ast.externos.util.KindExt;
import ast.externos.util.Parametros;
import ast.instrucciones.BloqueInstrucciones;

public class DefProcedimiento implements Externo {
	private Identificador opnd1;
	private Parametros opnd2;
	private BloqueInstrucciones opnd3;
	
	public DefProcedimiento(Identificador opnd1, Parametros opnd2, BloqueInstrucciones opnd3) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
	}

	public Identificador getOpnd1() {
		return opnd1;
	}

	public Parametros getOpnd2() {
		return opnd2;
	}

	public BloqueInstrucciones getOpnd3() {
		return opnd3;
	}

	@Override
	public KindExt kindExt() {
		return KindExt.DEF_PROCEDIMIENTO;
	}
	
	public String toString() {
		return "defProcedimiento(" + this.opnd1.toString() + "," + this.opnd2.toString() + ","
				+this.opnd3.toString() + ")";
	}
	
	 
}
