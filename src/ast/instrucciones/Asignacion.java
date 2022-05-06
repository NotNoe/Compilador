package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Designador;
import ast.expresiones.E;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Asignacion extends Instruccion {
	
	private Designador opnd1;
	private E opnd2;
	
	

	public Asignacion(Designador opnd1, E opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		return "asignacion("+opnd1.toString() + "," + opnd2.toString() + ")";
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		try {
			this.opnd2.bind(pila);
			
		} catch (UndefinedVariableException e) {
			e.print();
		}
	}

	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	@Override
	public KindI kindI() {
		return KindI.ASIGNACION;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		this.opnd1.subsUserTypes(globalTypes);
		this.opnd2.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		boolean err = false;
		try {
			opnd1.type(funcion, val_switch, current_class, false, false);
		} catch (TypeMissmatchException e) {
			e.print();
			err = true;
		}
		try {
			opnd2.type(funcion, val_switch, current_class, false, false);
		} catch (TypeMissmatchException e) {
			e.print();
			err = true;
		}
		if(!err) {
			if(!Tipo.equals(this.opnd1.tipo, this.opnd2.tipo))
				(new TypeMissmatchException("Type missmatch between " + this.opnd1.tipo.printT()
				+ " and " + this.opnd2.tipo.printT()+".", this.fila, this.columna)).print();
		}
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		String aux = "";
		//TODO: asignacion array
		return  opnd1.getDir(delta) + opnd2.generateCode(code, delta, depth) + "i32.store\n";
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

}
