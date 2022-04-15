package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Tipo;

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

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {

	}
}
