package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Bool;
import ast.tipo.Int;
import ast.tipo.Tipo;

public class For extends Instruccion {
	
	private Declaracion opnd1;
	private E opnd2;
	private Instruccion opnd3;
	private BloqueInstrucciones opnd4;
	
	

	public For(Declaracion opnd1, E opnd2, Instruccion opnd3, BloqueInstrucciones opnd4) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	public String toString() {
		return "for("+opnd1.toString()+","+opnd2.toString()+","+opnd3.toString()+","+opnd4.toString()+")";
	}

	@Override
	public KindI kindI() {
		return KindI.FOR;
	}

	public Declaracion getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	public Instruccion getOpnd3() {
		return opnd3;
	}

	public BloqueInstrucciones getOpnd4() {
		return opnd4;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		Map<String, ASTNode> aux = new HashMap<String, ASTNode>();
		pila.add(aux);
		opnd1.bind(pila);
		opnd2.bind(pila);
		opnd3.bind(pila);
		opnd4.bind(pila);
		pila.pop();
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
		opnd3.subsUserTypes(globalTypes);
		opnd4.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		opnd2.type(funcion, val_switch, current_class);
		opnd3.type(funcion, val_switch, current_class);
		opnd4.type(funcion, val_switch, current_class);
		if(!Tipo.equals(opnd1.getOpnd2(), new Int()) || !Tipo.equals(opnd2.tipo, new Bool())) {
			//TODO error
		}
	}
	
	

}
