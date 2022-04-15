package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Array implements Tipo {
	
	private Tipo tipo;
	private int dim;
	
	public Array(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
	}

	@Override
	public KindType kindType() {
		return KindType.ARRAY;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		this.tipo = tipo.getBasicType(globalTypes);
		return this;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		// TODO Auto-generated method stub
		
	}

	public Tipo getTipo() {
		return tipo;
	}
	

}
