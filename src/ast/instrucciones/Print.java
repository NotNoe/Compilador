package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Bool;
import ast.tipo.Int;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Print extends Instruccion {
	
	E exp;
	
	public String toString() {
		return "print(" + this.exp.toString() + ")";
	}

	public Print(int fila, int columna, E expresion) {
		super(fila, columna);
		this.exp = expresion;
	}
	
	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		this.exp.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable)
			throws TypeMissmatchException {
		this.exp.type(funcion, val_switch, current_class, continuable, breakeable);
		if(!((Tipo.equals(this.exp.tipo, new Int()))||(Tipo.equals(this.exp.tipo, new Bool()))))
			throw new TypeMissmatchException("Can only print basi types.", super.fila, super.columna);
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		return this.exp.generateCode(code, delta, depth) + "call $print\n";
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) throws UndefinedVariableException {
		this.exp.bind(pila);
	}

	@Override
	public KindI kindI() {
		return KindI.PRINT;
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

}
