package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Switch extends Instruccion {
	
	private E opnd1;
	private Cuerpo_Switch opnd2;
	
	

	public Switch(E opnd1, Cuerpo_Switch opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		return "switch("+opnd1.toString()+ "," + opnd2.toString();
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		this.opnd2.bind(pila);
	}
	
	
	@Override
	public KindI kindI() {
		return KindI.SWITCH;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public Cuerpo_Switch getOpnd2() {
		return opnd2;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		try {
			opnd1.type(funcion, val_switch, current_class, false, false);
		} catch (TypeMissmatchException e) {
			e.print();
		}
		opnd2.type(funcion, opnd1.tipo, current_class, continuable, true);
	}

	@Override
	public String generateCode(String code, int delta) {
		// TODO solo numeros
		return null;
	}

	@Override
	protected int precalcular(int i) {
		return this.opnd2.precalcular(i);
	}
	
	

}
