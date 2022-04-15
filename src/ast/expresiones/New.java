package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.ArrayDimensiones;
import ast.designadores.Identificador;
import ast.tipo.Array;
import ast.tipo.KindType;
import ast.tipo.Pointer;
import ast.tipo.Tipo;

public class New extends E {
	
	private Tipo opnd1;
	private ArrayDimensiones opnd2;
	
	public New(Tipo opnd1, ArrayDimensiones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		this.opnd2.bind(pila);
	}

	public Tipo getOpnd1() {
		return opnd1;
	}



	public ArrayDimensiones getOpnd2() {
		return opnd2;
	}
	
	public String toString() {
		return "new("+opnd1.toString() + "," + opnd2.toString() + ")";
	}


	@Override
	public KindE kind() {
		return KindE.NEW;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		if(opnd1.kindType() == KindType.ARRAY) {
			this.tipo = new Pointer(((Array) opnd1).getTipo());
		}else {
			this.tipo = new Pointer(opnd1);
		}
	}
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
	}

}
