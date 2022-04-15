package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Bool;
import ast.tipo.Tipo;

public class If extends Instruccion {

	private E opnd1;
	private BloqueInstrucciones opnd2;
	private BloqueInstrucciones opnd3;
	
	
	
	public E getOpnd1() {
		return opnd1;
	}



	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}



	public BloqueInstrucciones getOpnd3() {
		if(opnd3 == null)
			throw new UnsupportedOperationException("opnd3");
		return opnd3;
	}



	public If(E opnd1, BloqueInstrucciones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}



	public If(E opnd1, BloqueInstrucciones opnd2, BloqueInstrucciones opnd3) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
	}


	public String toString() {
		String aux = "if(" + this.opnd1.toString() + "," + this.opnd2.toString();
		if(this.opnd3 != null)
			aux += "," + this.opnd3.toString();
		return aux + ")";
	}

	public KindI kindI() {
		return KindI.IF;
	}



	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		opnd1.bind(pila);
		pila.add(new HashMap<String, ASTNode>());
		opnd2.bind(pila);
		pila.pop();
		if(opnd3 != null) {
			pila.add(new HashMap<String, ASTNode>());
			opnd3.bind(pila);
			pila.pop();
		}
	}



	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
		if(opnd3 != null) {
			opnd3.subsUserTypes(globalTypes);
		}
	}



	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		if(!Tipo.equals(opnd1.tipo, new Bool())) {
			//TODO error
		}
		opnd2.type(funcion, val_switch, current_class);
		if(opnd3 != null) {
			opnd3.type(funcion, val_switch, current_class);
		}
	}

}
