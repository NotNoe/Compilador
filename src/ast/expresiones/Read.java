package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Int;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Read extends E {

	public Read(int fila, int columna) {
		super(fila, columna);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable)
			throws TypeMissmatchException {
		this.tipo = new Int();

	}
	
	public String toString() {
		return "read()";
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		return "call $read\n";
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) throws UndefinedVariableException {

	}

	@Override
	public KindE kind() {
		return KindE.READ;
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

}
