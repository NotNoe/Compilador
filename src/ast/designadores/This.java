package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Tipo;
import errors.OutOfContextControlStatementException;
import errors.TypeMissmatchException;

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
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException {
		if(current_class == null)
			throw new TypeMissmatchException("This identifier out of class ambit.", this.fila, this.columna);
		this.tipo = current_class;
	}

}
