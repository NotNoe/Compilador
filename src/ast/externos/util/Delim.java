package ast.externos.util;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.KindType;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;

public class Delim implements Tipo {

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		
	}

	@Override
	public KindType kindType() {
		return KindType.DELIM;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) throws TypeMissmatchException {
		return this;
	}

	@Override
	public String printT() {
		return "Delim";
	}
	
	
}
