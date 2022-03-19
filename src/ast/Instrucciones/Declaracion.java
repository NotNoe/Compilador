package ast.Instrucciones;

import ast.ArrayDimensiones;
import ast.Designadores.Identificador;
import ast.Expresiones.E;
import ast.tipo.Tipo;

public class Declaracion extends Instruccion {

	private Boolean opnd1;
	private Tipo opnd2;
	private Identificador opnd3;
	private ArrayDimensiones opnd4;
	private E opnd5;

	public Declaracion(Boolean opnd1, Tipo opnd2, Identificador opnd3, ArrayDimensiones opnd4, E opnd5) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
		this.opnd5 = opnd5;
	}

	public Declaracion(Tipo opnd2, Identificador opnd3, ArrayDimensiones opnd4) {
		super();
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	public String toString() {
		if (opnd1 == null) {
			return "declaracion(" + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ")";
		} else if (opnd1) {
			return "declaracion(const," + opnd2.toString() + "," + opnd3.toString() + "," 
					+ opnd4.toString() + "," + opnd5.toString()+")";
		}else {
			return "declaracion(" + opnd2.toString() + "," + opnd3.toString() + "," 
					+ opnd4.toString() + "," + opnd5.toString()+")";
		}
	}

	@Override
	public KindI kindI() {
		return KindI.DECLARACION;
	}

	public Boolean getOpnd1() {
		return opnd1;
	}

	public Tipo getOpnd2() {
		return opnd2;
	}

	public Identificador getOpnd3() {
		return opnd3;
	}

	public ArrayDimensiones getOpnd4() {
		return opnd4;
	}

	public E getOpnd5() {
		return opnd5;
	}
	
	

}
