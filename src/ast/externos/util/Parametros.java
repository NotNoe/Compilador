package ast.externos.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.KindP;
import ast.NodeKind;
import ast.tipo.Tipo;

public class Parametros implements ASTNode {

	private KindP kind;
	private Parametro opnd1;
	private Parametros opnd2;
	
	public Parametros() {
		this.kind = KindP.VACIO;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(this.kind == KindP.NO_VACIO) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
	}
	
	public Parametros(Parametro opnd1, Parametros opnd2) {
		this.kind = KindP.NO_VACIO;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		if(this.kind == KindP.VACIO) {
			return "parametros()";
		}else {
			return "parametros("+opnd1.toString()+","+opnd2.toString()+")";
		}
	}


	public Parametro getOpnd1() {
		return opnd1;
	}



	public Parametros getOpnd2() {
		return opnd2;
	}



	public KindP kindP() {
		return this.kind;
	}
	
	public NodeKind nodeKind() {
		return NodeKind.PARAMETROS;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(this.kind == KindP.NO_VACIO) {
			opnd1.subsUserTypes(globalTypes);
			opnd2.subsUserTypes(globalTypes);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(this.kind == KindP.NO_VACIO) {
			opnd1.type(funcion, val_switch, null, continuable, breakeable);
			opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		}
	}

	public void getListaTipos(ArrayList<Tipo> listaTipos) {
		if(this.kind == KindP.NO_VACIO) {
			listaTipos.add(this.opnd1.getOpnd1());
			this.opnd2.getListaTipos(listaTipos);
		}
	}

	public int precalcular(int i) {
		return this.opnd2.precalcular(this.opnd1.precalcular(i));
	}

	@Override
	public String generateCode(String code, int delta) {
		// TODO Auto-generated method stub
		return null;
	}

}
