package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Null extends E {
	
	public String toString() {
		return "null()";
	}

	@Override
	public KindE kind() {
		return KindE.NULL;
	}
	public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}

}
