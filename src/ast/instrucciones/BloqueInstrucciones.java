package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;

public class BloqueInstrucciones implements ASTNode {
	
	private Instruccion opnd1;
	private BloqueInstrucciones opnd2;
	
	

	public BloqueInstrucciones(Instruccion opnd1, BloqueInstrucciones opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(this.opnd1 != null) {
			this.opnd1.bind(pila);
			this.opnd2.bind(pila);
		}
	}

	public BloqueInstrucciones() {
		super();
	}

	public String toString() {
		if(opnd1 != null) {
			return "bloque_inst("+opnd1.toString()+","+opnd2.toString()+")";
		}else {
			return "bloque_inst()";
		}
	}


	public Instruccion getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.BLOQUE_INST;
	}

}
