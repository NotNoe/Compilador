package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Int implements Tipo{
	
	 public String toString() {return "int()";}  
	 
	 public Int(){}
	 
	@Override
	public KindType kindType() {
		return KindType.INT;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		// TODO Auto-generated method stub
		
	}

}
