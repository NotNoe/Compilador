package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.ArrayDimensiones;
import ast.designadores.Identificador;
import ast.expresiones.E;
import ast.externos.Externo;
import ast.externos.util.KindExt;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Declaracion extends Instruccion implements Externo {

	private boolean opnd1; // Indica si la variable es constante
	private Tipo opnd2;
	private Identificador opnd3;
	private ArrayDimensiones opnd4;
	private E opnd5;

	public KindExt kindExt() {
		return KindExt.DECLARACION;
	}

	public Declaracion(boolean opnd1, Tipo opnd2, Identificador opnd3, ArrayDimensiones opnd4, E opnd5, int fila,
			int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
		this.opnd5 = opnd5;
	}

	public Declaracion(Tipo opnd2, Identificador opnd3, ArrayDimensiones opnd4, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = false;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	public String toString() {
		if (opnd5 == null) {
			return "declaracion(" + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ")";
		} else if (opnd1) {
			return "declaracion(const," + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ","
					+ opnd5.toString() + ")";
		} else {
			return "declaracion(" + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ","
					+ opnd5.toString() + ")";
		}
	}

	@Override
	public KindI kindI() {
		return KindI.DECLARACION;
	}

	public Boolean getOpnd1() {
		return opnd1;
	}

	public Tipo getOpnd2() {
		return opnd2;
	}

	public Identificador getOpnd3() {
		return opnd3;
	}

	public ArrayDimensiones getOpnd4() {
		return opnd4;
	}

	public E getOpnd5() {
		return opnd5;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {
		pila.peek().put(opnd3.getIden(), this);
		opnd4.bind(pila);
		if (opnd5 != null) {
			try {
				opnd5.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
		}
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		try {
			this.opnd2 = this.opnd2.getBasicType(globalTypes);
		} catch (TypeMissmatchException e) {
			e.print();
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.opnd2 = opnd4.tipar(opnd2);
		if (opnd5 != null) {
			try {
				this.opnd5.type(funcion, val_switch, current_class, false, false);
				if (!Tipo.equals(opnd2, opnd5.tipo))
					(new TypeMissmatchException(
							"Type doesn't match between " + this.opnd2.printT() + " and " + this.opnd5.tipo.printT(),
							this.fila, this.columna)).print();
			} catch (TypeMissmatchException e) {
				e.print();
			}

		}
	}

}
