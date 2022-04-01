package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class This extends Designador {
	
	public String toString() {
		return "this()";
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}
	
	@Override
	public KindDes kindDes() {
		return KindDes.THIS;
	}

}
