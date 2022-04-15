package ast;

import java.util.Stack;
import java.util.Map;
import ast.expresiones.E;
import ast.tipo.Array;
import ast.tipo.Tipo;

public class ArrayDimensiones implements ASTNode {
	
	private E opnd1;
	private ArrayDimensiones opnd2;
	
	public ArrayDimensiones() {
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		if (this.opnd1 != null) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
			
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
	
	public Tipo tipar(Tipo t) {
		if(opnd1 == null) {
			return t;
		}else {
			return new Array(opnd2.tipar(t));
		}
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		// TODO Auto-generated method stub
		
	}

}
