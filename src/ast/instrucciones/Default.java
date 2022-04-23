package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.tipo.Tipo;

public class Default extends SwitchCase {
	
	private BloqueInstrucciones opnd1;

	public Default(BloqueInstrucciones opnd1) {
		super();
		this.opnd1 = opnd1;
	}

	public String toString() {
		return "default("+opnd1.toString()+")";
	}
	
	
	public NodeKind nodeKind() {
		return NodeKind.DEFAULT;
	}

	public BloqueInstrucciones getOpnd1() {
		return opnd1;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		pila.push(new HashMap<String, ASTNode>());
		this.opnd1.bind(pila);
		pila.pop();
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
	}

}
