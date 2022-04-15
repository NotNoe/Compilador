package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.KindType;
import ast.tipo.Pointer;
import ast.tipo.Tipo;

public class Asterisco extends Designador {
	
	private Designador opnd1;
	
	public Asterisco(Designador op1) {
		this.opnd1 = op1;
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
	}
	
	public E opnd1() {return this.opnd1;} 

	@Override
	public KindDes kindDes() {
		return KindDes.ASTERISCO;
	}
	
	public String toString() {
		return "acceso("+this.opnd1.toString()+")";
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, null);
		if(opnd1.tipo.kindType() == KindType.POINTER) {
			this.tipo = ((Pointer) opnd1.tipo).getP();
		}else {
			//TODO: error
		}
	}

}
