package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Tipo;
import errors.OutOfContextControlStatementException;

public class Break extends Instruccion {

	public Break(int fila, int columna) {super(fila, columna);}
	
	public KindI kindI() {
		return KindI.BREAK;
	}

	public String toString() {
		return "break()";
	}
	public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(!breakeable)
			(new OutOfContextControlStatementException(this.fila, this.columna)).print();
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
