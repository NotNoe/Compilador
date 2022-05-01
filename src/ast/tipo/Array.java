package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import errors.TypeMissmatchException;

public class Array implements Tipo {
	
	private Tipo tipo;
	public int size;
	
	public Array(Tipo tipo, int size) {
		this.tipo = tipo;
		this.size = size;
	}
	
	public String printT() {
		return this.tipo.printT() + "[" + this.size + "]";
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

	@Override
	public int getSize() {
		return this.size * this.tipo.getSize();
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
