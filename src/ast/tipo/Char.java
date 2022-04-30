package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Char implements Tipo {
	
	int size = 4;
	public String toString() {return "char()";}

	@Override
	public KindType kindType() {
		return KindType.CHAR;
	}
	
	public String printT() {
		return "char";
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

	@Override
	public int getSize() {
		return this.size;
	}

}
