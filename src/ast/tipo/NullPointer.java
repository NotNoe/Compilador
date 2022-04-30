package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class NullPointer implements Tipo {
	
	public int size = 4;

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {

	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {

	}

	@Override
	public KindType kindType() {
		return KindType.NULL_POINTER;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		return null;
	}
	
	public String printT() {
		return "null";
	}

	@Override
	public int getSize() {
		return this.size;
	}

}
