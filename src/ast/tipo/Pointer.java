package ast.tipo;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Pointer implements Tipo {
	
	private Tipo p;
	
	public Pointer(Tipo p) {
		this.p = p;
	}
	
	public Tipo getP() {
		return p;
	}

	public String toString() {
		return "pointer(" + p.toString() + ")";
	}

	@Override
	public KindType kindType() {
		return KindType.POINTER;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			p.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
	}
	
	public Tipo getBasicType(Map<String, Tipo> globalTypes) throws TypeMissmatchException {
		this.p = p.getBasicType(globalTypes);
		return this;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		
		
	}
	
	public String printT() {
		return "pointer " + this.p.printT();
	}

}
