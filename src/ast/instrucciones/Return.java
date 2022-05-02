package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Return extends Instruccion {

	private E opnd1;
	
	
	public Return(E opnd1, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
	}

	
	public E getOpnd1() {
		return opnd1;
	}

	public String toString() {
		return "return(" + opnd1.toString() + ")";
	}

	public KindI kindI() {
		return KindI.RETURN;
	}


	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
	}


	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
	}


	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		try {
			opnd1.type(funcion, val_switch, current_class, false, false);
			if(!Tipo.equals(opnd1.tipo, funcion)) {
				(new TypeMissmatchException("Return type must match with function type " + funcion.printT() +
						", not " + opnd1.tipo.printT(), this.fila, this.columna)).print();
			}
		} catch (TypeMissmatchException e) {
			
		}
		
	}


	@Override
	public String generateCode(String code, int delta, int depth) {
		return this.opnd1.generateCode(code, delta, depth) + "call $freeStack\n" + "return\n";
	}


	@Override
	protected int precalcular(int i) {
		return i;
	}

}
