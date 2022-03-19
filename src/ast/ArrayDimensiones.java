package ast;

import ast.Expresiones.E;

public class ArrayDimensiones implements ASTNode {
	
	private E opnd1;
	private ArrayDimensiones opnd2;
	
	public ArrayDimensiones() {
	}
	
	public ArrayDimensiones(E opnd1, ArrayDimensiones opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	

	public E getOpnd1() {
		return opnd1;
	}

	public ArrayDimensiones getOpnd2() {
		return opnd2;
	}
	
	public String toString() {
		if(opnd1 == null) {
			return "array_dim()";
		}else {
			return "array_dim("+opnd1.toString() + "," + opnd2.toString() + ")";
		}
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.ARRAY_DIM;
	}

}
