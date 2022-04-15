package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Tipo;

public class Continue extends Instruccion {
	
	public Continue() {}

	public KindI kindI() {
		return KindI.CONTINUE;
	}

	public String toString() {
		return "continue()";
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
	
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {

	}

}
