package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Break extends Instruccion {

	public Break() {}
	
	public KindI kindI() {
		return KindI.BREAK;
	}

	public String toString() {
		return "break()";
	}
	public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}
}
