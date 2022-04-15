package ast.externos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.util.KindP;
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
		Stack<Map<String, ASTNode>> global = new Stack<Map<String, ASTNode>>();
		global.push(new HashMap<String, ASTNode>());
		this.bind(global);
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(opnd1 != null) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
	}
	
	public void tipar() {
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
