package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class EmptyArray implements Tipo {
	
	public int size = 0;

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		
	}

	@Override
	public KindType kindType() {
		return KindType.ARRAY_VACIO;
	}
	
	public String printT() {
		return "[]";
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		return null;
	}

	@Override
	public int getSize() {
		return 0;
	}

}
