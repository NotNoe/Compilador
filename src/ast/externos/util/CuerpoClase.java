package ast.externos.util;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.Externo;
import ast.instrucciones.Declaracion;

public class CuerpoClase implements ASTNode {

	private Externo opnd1;
	private CuerpoClase opnd2;
	private KindC kind;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(this.kind != KindC.VACIO) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
	}
	
	public CuerpoClase(Declaracion opnd1, CuerpoClase opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.kind = KindC.DECLARACION;
	}
	
	public CuerpoClase(DefFuncion opnd1, CuerpoClase opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.kind = KindC.DEF_FUNCION;
	}
	
	public CuerpoClase(DefProcedimiento opnd1, CuerpoClase opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.kind = KindC.DEF_PROCEDIMIENTO;
	}
	
	public CuerpoClase() {
		this.kind = KindC.VACIO;
	}

	public Externo getOpnd1() {
		return opnd1;
	}

	public CuerpoClase getOpnd2() {
		return opnd2;
	}

	public KindC kindC() {
		return kind;
	}
	
	public String toString() {
		if(this.kind == KindC.VACIO)
			return "cuerpoClase()";
		else
			return "cuerpoClase(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.CUERPO_CLASE;
	}
	
	
	
	
}
