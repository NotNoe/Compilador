package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Designador;
import ast.tipo.Tipo;

public class Delete extends Instruccion {

	private Designador opnd1;
	
	public String toString() {
		return "delete("+opnd1.toString()+")";
	}
	
	public Designador getOpnd1() {
		return opnd1;
	}

	public Delete(Designador opnd1) {
		this.opnd1 = opnd1;
	}



	public KindI kindI() {
		return KindI.DELETE;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		opnd1.bind(pila);
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
	}

}
