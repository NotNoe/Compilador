package ast;

import java.util.Stack;
import java.util.Map;
import ast.expresiones.E;
import ast.expresiones.Num;
import ast.tipo.Array;
import ast.tipo.Int;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class ArrayDimensiones implements ASTNode {
	
	private E opnd1;
	private ArrayDimensiones opnd2;
	private int size = 0;
	
	
	public ArrayDimensiones() {
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		if (this.opnd1 != null) {
			try {
				this.opnd1.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
			this.opnd2.bind(pila);
		}
			
	}
	
	public ArrayDimensiones(E opnd1, ArrayDimensiones opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	

	public E getOpnd1() {
		return opnd1;
	}

	public ArrayDimensiones getOpnd2() {
		return opnd2;
	}
	
	public String toString() {
		if(opnd1 == null) {
			return "array_dim()";
		}else {
			return "array_dim("+opnd1.toString() + "," + opnd2.toString() + ")";
		}
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.ARRAY_DIM;
	}
	
	public Tipo tipar(Tipo t) {
		if(opnd1 == null) {
			return t;
		}else {
			this.size = ((Num) this.opnd1).getValue();
			return new Array(opnd2.tipar(t), ((Num) this.opnd1).getValue());
		}
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(!(this.opnd1 == null)) {
			try {
				this.opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
				if(!Tipo.equals(this.opnd1.tipo, new Int())){
					(new TypeMissmatchException("Array dimensions must be integer, not: " +
							this.opnd1.tipo.printT(), this.opnd1.fila, this.opnd1.columna)).print();
				}
				this.opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
			} catch (TypeMissmatchException e) {
				e.print();
			}
		}
		
	}

}
