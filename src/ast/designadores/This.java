package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Tipo;

public class This extends Designador {
	
	public This(int fila, int columna) {
		super(fila, columna);
	}

	public String toString() {
		return "this()";
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}
	
	@Override
	public KindDes kindDes() {
		return KindDes.THIS;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		//TODO Error de this fuera de ambito
		this.tipo = current_class;
	}

}
