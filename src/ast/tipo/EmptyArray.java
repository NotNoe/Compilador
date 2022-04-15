package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class EmptyArray implements Tipo {

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		
	}

	@Override
	public KindType kindType() {
		return KindType.ARRAY_VACIO;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		return null;
	}

}
