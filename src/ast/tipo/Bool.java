package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Bool implements Tipo {
	
	public String toString() {return "bool()";}

	@Override
	public KindType kindType() {
		return KindType.BOOL;
	}

	public String printT() {
		return "bool";
	}
	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		return this;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		
		
	}

}
