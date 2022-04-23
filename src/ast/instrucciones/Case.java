package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.expresiones.E;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Case extends SwitchCase {

	private E opnd1;
	private BloqueInstrucciones opnd2;
	public int fila, columna;

	public Case(E opnd1, BloqueInstrucciones opnd2, int fila, int columna) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.fila = fila;
		this.columna = columna;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		pila.push(new HashMap<String, ASTNode>());
		this.opnd2.bind(pila);
		pila.pop();
	}

	public String toString() {
		return "case(" + opnd1.toString() + "," + opnd2.toString() + ")";
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.CASE;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if (val_switch != null) {
			try {
				opnd1.type(funcion, val_switch, current_class, false, false);
				if (!Tipo.equals(opnd1.tipo, val_switch)) {
					(new TypeMissmatchException(
							"Switch case type " + opnd1.tipo.printT() + "doesn't match " + val_switch.printT() + ".",
							this.fila, this.columna)).print();
				}
			} catch (TypeMissmatchException e) {
				e.print();
			}
		}

		opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
	}

}
