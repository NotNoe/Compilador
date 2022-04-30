package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.NullPointer;
import ast.tipo.Tipo;

public class Null extends E {
	
	public Null(int fila, int columna) {
		super(fila, columna);
	}

	public String toString() {
		return "null()";
	}

	@Override
	public KindE kind() {
		return KindE.NULL;
	}
	public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.tipo = new NullPointer();
	}

	@Override
	public String generateCode(String code, int delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

}
