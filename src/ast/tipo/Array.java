package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import errors.TypeMissmatchException;

public class Array implements Tipo {
	
	private Tipo tipo;
	private int dim;
	
	public Array(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String printT() {
		return this.tipo.printT() + "[]";
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
	}

	@Override
	public KindType kindType() {
		return KindType.ARRAY;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) throws TypeMissmatchException {
		this.tipo = tipo.getBasicType(globalTypes);
		return this;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		
		
	}

	public Tipo getTipo() {
		return tipo;
	}
	

}
