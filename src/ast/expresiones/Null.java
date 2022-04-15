package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.NullPointer;
import ast.tipo.Tipo;

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

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		this.tipo = new NullPointer();
	}

}
