package ast.externos.util;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.instrucciones.Declaracion;
import ast.tipo.Tipo;

public class Declaraciones implements ASTNode {

	private KindD kind;
	private Declaracion opnd1;
	private Declaraciones opnd2;
	
	public Declaraciones() {
		this.kind = KindD.VACIO;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(this.kind == KindD.NO_VACIO) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
	}
	
	
	public Declaraciones(Declaracion opnd1, Declaraciones opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.kind = KindD.NO_VACIO;
	}
	
	public String toString() {
		if(this.kind == KindD.VACIO) {
			return "fin_declaraciones()";
		}else {
			return "declaraciones("+opnd1.toString()+","+opnd2.toString()+")";
		}
	}



	public KindD kindD() {
		return kind;
	}



	public Declaracion getOpnd1() {
		return opnd1;
	}



	public Declaraciones getOpnd2() {
		return opnd2;
	}



	public NodeKind nodeKind() {
		return NodeKind.DECLARACIONES;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(this.kind != KindD.VACIO) {
			opnd1.subsUserTypes(globalTypes);
			opnd2.subsUserTypes(globalTypes);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(this.kind != KindD.VACIO) {
			opnd1.type(funcion, val_switch, null, continuable, breakeable);
			opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		}
	}

	public int precalcular(int delta) {
		if(this.kind == KindD.NO_VACIO) {
			return this.opnd2.precalcular(this.opnd1.precalcular(delta));
		}else {
			return delta;
		}
	}

	public String getCodeDec(int delta) {
		if(this.opnd1!=null) {
			return this.opnd1.getCodeExtern(delta) + this.opnd2.getCodeDec(delta);
		}else {
			return "";
		}
		
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

}
