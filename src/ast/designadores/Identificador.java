package ast.designadores;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.Externo;
import ast.externos.util.KindExt;
import ast.externos.util.Parametro;
import ast.instrucciones.Declaracion;
import ast.tipo.KindType;
import ast.tipo.Tipo;

public class Identificador extends Designador implements Tipo {
	
	private String iden;
	private ASTNode link;
	
	public Identificador(String iden) {
		this.iden = iden;
	}
	

	public String getIden() {
		return this.iden;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {
		Stack<Map<String, ASTNode>> aux = new Stack<Map<String, ASTNode>>();
		while(!pila.empty()) {
			Map<String, ASTNode> top = pila.peek();
			if(top.containsKey(this.iden)) {
				this.link = top.get(this.iden);
				break;
			}
			else {
				pila.pop();
				aux.push(top);
			}	
		}
		while(!aux.empty()) {
			pila.push(aux.pop());
		}
		if(this.link == null) {
			//TODO ANNADIR ERROR
		}
	}
	
	public String toString() {
		return "identificador(" + this.iden + ")";
	}
	public KindDes kindDes() {
		return KindDes.IDENFITICADOR;
	}

	@Override
	public KindType kindType() {
		return KindType.IDENTIFICADOR;
	}


	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		if(globalTypes.containsKey(this.iden)) {
			return globalTypes.get(this.iden);
		}else {
			//TODO: annadir errores
			return this;
		}
	}
	

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		if(this.link.nodeKind() == NodeKind.PARAMETRO) {
			this.tipo = ((Parametro) link).getOpnd1();
		}else {
			switch(((Externo)link).kindExt()) {
			case DECLARACION:
				this.tipo = ((Declaracion) link).getOpnd2();
				break;
			case DEF_FUNCION:
				this.tipo = ((DefFuncion) link).getOpnd1();
				break;
			case DEF_PROCEDIMIENTO:
				this.tipo = null;
				break;
			default:
				//TODO error
			}
		}
	}


	public boolean checkTipos(ArrayList<Tipo> listaTipos) {
		ArrayList<Tipo> lista2;
		if(((Externo) link).kindExt() == KindExt.DECLARACION) {
			lista2 = ((DefProcedimiento) link).getListaTipos();
		}else if(((Externo) link).kindExt() == KindExt.DEF_FUNCION) {
			lista2 = ((DefFuncion) link).getListaTipos();
		}else {
			lista2 = new ArrayList<Tipo>();
			//TODO error
		}
		if(lista2.size() == listaTipos.size()) {
			for(int i = 0; i < lista2.size(); i++) {
				if(!Tipo.equals(lista2.get(i), listaTipos.get(i))){
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}

}
