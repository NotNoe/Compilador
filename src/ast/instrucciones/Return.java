package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Tipo;

public class Return extends Instruccion {

	private E opnd1;
	
	
	public Return(E opnd1) {
		this.opnd1 = opnd1;
	}

	
	public E getOpnd1() {
		return opnd1;
	}

	public String toString() {
		return "return(" + opnd1.toString() + ")";
	}

	public KindI kindI() {
		return KindI.RETURN;
	}


	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		opnd1.bind(pila);
	}


	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
	}


	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		if(!Tipo.equals(opnd1.tipo, funcion)) {
			//TODO error
		}
	}

}
