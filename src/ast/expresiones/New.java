package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
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
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		this.opnd2.bind(pila);
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
