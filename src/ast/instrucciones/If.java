package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Bool;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class If extends Instruccion {

	private E opnd1;
	private BloqueInstrucciones opnd2;
	private BloqueInstrucciones opnd3;
	
	
	
	public E getOpnd1() {
		return opnd1;
	}



	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}



	public BloqueInstrucciones getOpnd3() {
		if(opnd3 == null)
			throw new UnsupportedOperationException("opnd3");
		return opnd3;
	}



	public If(E opnd1, BloqueInstrucciones opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}



	public If(E opnd1, BloqueInstrucciones opnd2, BloqueInstrucciones opnd3, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
	}


	public String toString() {
		String aux = "if(" + this.opnd1.toString() + "," + this.opnd2.toString();
		if(this.opnd3 != null)
			aux += "," + this.opnd3.toString();
		return aux + ")";
	}

	public KindI kindI() {
		return KindI.IF;
	}



	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		pila.add(new HashMap<String, ASTNode>());
		opnd2.bind(pila);
		pila.pop();
		if(opnd3 != null) {
			pila.add(new HashMap<String, ASTNode>());
			opnd3.bind(pila);
			pila.pop();
		}
	}



	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
		if(opnd3 != null) {
			opnd3.subsUserTypes(globalTypes);
		}
	}



	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		try {
			opnd1.type(funcion, val_switch, current_class, false, false);
			if(!Tipo.equals(opnd1.tipo, new Bool()))
				(new TypeMissmatchException("Type doesn't match, it must be bool, not "+
					opnd1.tipo.printT(), this.fila, this.columna)).print();
		} catch (TypeMissmatchException e) {
			e.print();
		}
		opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		if(opnd3 != null) {
			opnd3.type(funcion, val_switch, current_class, continuable, breakeable);
		}
	}

}
