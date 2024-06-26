package ast.externos.util;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.ArrayDimensiones;
import ast.NodeKind;
import ast.designadores.Identificador;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;

public class Parametro implements ASTNode {

	private Tipo opnd1;
	private boolean ref;
	private Identificador opnd2;
	private ArrayDimensiones opnd3;
	public int fila, columna;
	public int delta = 0;
	
	
	
	public Parametro(Tipo opnd1, boolean ref, Identificador opnd2, ArrayDimensiones opnd3, int fila, int columna) {
		super();
		this.opnd1 = opnd1;
		this.ref = ref;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.fila = fila;
		this.columna = columna;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		opnd3.bind(pila);
		pila.peek().put(this.opnd2.getIden(), this);
	}



	public Tipo getOpnd1() {
		return opnd1;
	}




	public boolean isRef() {
		return ref;
	}




	public Identificador getOpnd2() {
		return opnd2;
	}


	public String toString() {
		if(this.ref)
			return "parametro(" + this.opnd1.toString() + ",&," + this.opnd2.toString() + opnd3.toString() + ")";  
		else
			return "parametro(" + this.opnd1.toString() + "," + this.opnd2.toString() + opnd3.toString() +")";
	}

	public NodeKind nodeKind() {
		return NodeKind.PARAMETRO;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		try {
			this.opnd1 = this.opnd1.getBasicType(globalTypes);
		} catch (TypeMissmatchException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.opnd1 = opnd3.tipar(this.opnd1);
		this.opnd3.type(funcion, val_switch, null, continuable, breakeable);
	}

	public int precalcular(int i) {
		this.delta = i;
		if(this.ref) {
			return i + 4;
		}else {
			return i + this.opnd1.getSize();
		}
		
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
