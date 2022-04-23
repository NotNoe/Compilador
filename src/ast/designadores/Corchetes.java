package ast.designadores;

import java.util.Map;
import java.util.Stack;

import javax.lang.model.type.TypeKind;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Array;
import ast.tipo.KindType;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Corchetes extends Designador {
	
	private Designador opnd1;
	private E opnd2;

	
	public Corchetes(Designador opnd1, E opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		}catch(UndefinedVariableException e) {
			e.print();
		}
		try {
			this.opnd2.bind(pila);
		}catch(UndefinedVariableException e) {
			e.print();
		}
	}
	
	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	public String toString() {
		return "corchetes("+this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}


	@Override
	public KindDes kindDes() {
		return KindDes.CORCHETES;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException {
		opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
		opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		if(opnd1.tipo.kindType() != KindType.ARRAY) {
			throw new TypeMissmatchException("Cannot index a non-array type.", this.fila, this.columna);
		}else {
			if(opnd2.tipo.kindType() != KindType.INT) {
				throw new TypeMissmatchException("Cannot index by a non-int type.", this.fila, this.columna);
			}else {
				this.tipo = ((Array) opnd1.tipo).getTipo();
			}
		}
	}

}
