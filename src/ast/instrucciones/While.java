package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Bool;
import ast.tipo.Tipo;

public class While extends Instruccion {
	
	private E opnd1;
	private BloqueInstrucciones opnd2;
	
	

	public While(E opnd1, BloqueInstrucciones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public String toString() {
		return "while("+opnd1.toString() + "," + opnd2.toString() + ")";
	}

	@Override
	public KindI kindI() {
		return KindI.INSTRUCCION;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		opnd1.bind(pila);
		pila.add(new HashMap<String, ASTNode>());
		opnd2.bind(pila);
		pila.pop();
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		if(!Tipo.equals(opnd1.tipo, new Bool())) {
			//TODO error
		}
		opnd2.type(funcion, val_switch, current_class);
	}
	
	

}
