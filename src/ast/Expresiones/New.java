package ast.Expresiones;

import ast.ArrayDimensiones;
import ast.tipo.Tipo;

public class New extends E {
	
	private Tipo opnd1;
	private ArrayDimensiones opnd2;
	
	public New(Tipo opnd1, ArrayDimensiones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	

	public Tipo getOpnd1() {
		return opnd1;
	}



	public ArrayDimensiones getOpnd2() {
		return opnd2;
	}
	
	public String toString() {
		return "new("+opnd1.toString() + "," + opnd2.toString() + ")";
	}


	@Override
	public KindE kind() {
		// TODO Auto-generated method stub
		return null;
	}

}
