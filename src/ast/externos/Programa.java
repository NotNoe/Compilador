package ast.externos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.util.KindP;
import ast.instrucciones.Declaracion;
import ast.tipo.Tipo;

public class Programa implements ASTNode {

	private Externo opnd1;
	private Programa opnd2;
	private KindP kind;
	
	
	public Programa() {
		this.kind = KindP.VACIO;
	}
	
	public Programa(Externo opnd1, Programa opnd2) {
		this.kind = KindP.NO_VACIO;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void vincular() {
		HashMap<String, ASTNode> globalAmb = new HashMap<String, ASTNode>();
		this.preBinding(globalAmb);
		Stack<Map<String, ASTNode>> global = new Stack<Map<String, ASTNode>>();
		global.push(globalAmb);
		this.bind(global);
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(opnd1 != null) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
	}
	
	public void compilar() {
		this.vincular();
		Map<String, Tipo> globalTypes = new HashMap<String, Tipo>();
		getUserTypes(globalTypes);
		this.subsUserTypes(globalTypes);
		this.type(null, null, null);
	}
	
	
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(this.kind == KindP.NO_VACIO) {
			this.opnd1.subsUserTypes(globalTypes);
			this.opnd2.subsUserTypes(globalTypes);
		}
	}

	private Map<String, Tipo> getUserTypes(Map<String, Tipo> globalTypes){
		if(this.kind == KindP.NO_VACIO) {
			switch(this.opnd1.kindExt()) {
			case DEF_CLASE:
				globalTypes.put(((DefClase) opnd1).getOpnd2().getIden(), (DefClase) opnd1);
				break;
			case DEF_STRUCT:
				globalTypes.put(((DefStruct) opnd1).getOpnd2().getIden(), (DefStruct) opnd1);
				break;
			case DEF_TIPO:
				globalTypes.put(((DefTipo) opnd1).getOpnd2().getIden(),((DefTipo) opnd1).getBasicType(globalTypes));
				break;
			default:
				break;
			}
			return opnd2.getUserTypes(globalTypes);
		}else {
			return globalTypes;
		}
	}
	

	public KindP kindP() {return this.kind;}
	
	private void preBinding(HashMap<String, ASTNode> globalBind) {
		if(this.kind != KindP.VACIO)
			switch(this.opnd1.kindExt()) {
			case DECLARACION:
				globalBind.put(((Declaracion) opnd1).getOpnd3().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_CLASE:
				globalBind.put(((DefClase) opnd1).getOpnd2().getIden(), opnd1);
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
			case DEF_STRUCT:
				globalBind.put(((DefStruct) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_TIPO:
				globalBind.put(((DefTipo) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			default:
				throw new RuntimeException("Error en prebinding");
			
			}
	}
	
	public Externo getOpnd1() {
		return opnd1;
	}

	public Programa getOpnd2() {return this.opnd2;}



	public NodeKind nodeKind() {
		return NodeKind.PROGRAMA;
	}
	
	public String toString() {
		if(this.kind == KindP.VACIO)
			return "fin_programa()";
		else
			return "programa(" + opnd1.toString() + "," + opnd2.toString() + ")";
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		if(this.kind == KindP.NO_VACIO) {
			opnd1.type(null, null, null);
			opnd2.type(null, null, null);
		}
	}

}
