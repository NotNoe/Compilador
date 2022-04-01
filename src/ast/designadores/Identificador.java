package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
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

}
