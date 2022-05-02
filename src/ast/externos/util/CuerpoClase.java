package ast.externos.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.Externo;
import ast.instrucciones.Declaracion;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class CuerpoClase implements ASTNode {

	private Externo opnd1;
	private CuerpoClase opnd2;
	private KindC kind;
	
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(this.kind != KindC.VACIO) {
			try {
				this.opnd1.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
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

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(this.kind != KindC.VACIO) {
			opnd1.subsUserTypes(globalTypes);
			opnd2.subsUserTypes(globalTypes);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(this.kind != KindC.VACIO) {
			try {
				opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
			} catch (TypeMissmatchException e) {
				e.print();
			}
			opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		}
	}

	public void preBinding(Map<String, ASTNode> globalBind) {
		if(this.kind != KindC.VACIO) {
			switch(this.opnd1.kindExt()) {
			case DECLARACION:
				globalBind.put(((Declaracion) opnd1).getOpnd3().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_FUNCION:
				globalBind.put(((DefFuncion) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_PROCEDIMIENTO:
				globalBind.put(((DefProcedimiento) opnd1).getOpnd1().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			default:
				throw new RuntimeException("Error en prebinding");
			
			}
		}
		
	}

	public int precalcular(int delta) {
		if(this.kind != KindC.VACIO) {
			switch(this.opnd1.kindExt()) {
			case DECLARACION:
				return this.opnd2.precalcular(((Declaracion) opnd1).precalcular(delta));
			default:
				return this.opnd2.precalcular(delta);
			}
		}else {
			return delta;
		}
	}

	public String getCode(int delta) {
		if(this.kind != KindC.VACIO) {
			if(this.opnd1.kindExt() == KindExt.DECLARACION) {
				return ((Declaracion) opnd1).getCodeExtern(delta) + this.opnd2.getCode(delta);
			}else {
				return this.opnd2.getCode(delta);
			}
		}else {
			return "";
		}
		
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		if(this.kind != KindC.VACIO) {
			switch(this.opnd1.kindExt()) {
			case DEF_FUNCION:
				((DefFuncion) opnd1).precalcular(delta);
				return opnd1.generateCode(code, delta, depth) + this.opnd2.generateCode(code, delta, depth);
			case DEF_PROCEDIMIENTO:
				((DefProcedimiento) opnd1).precalcular(delta);
				return opnd1.generateCode(code, delta, depth) + this.opnd2.generateCode(code, delta, depth);
			default:
				return this.opnd2.generateCode(code, delta, depth);
			}
		}else {
			return "";
		}
	}

	public void getDecList(ArrayList<Declaracion> decList) {
		if(this.kind != KindC.VACIO && this.opnd1 instanceof Declaracion) {
			decList.add((Declaracion)this.opnd1);
		}
	}

	
	
	
	
}
