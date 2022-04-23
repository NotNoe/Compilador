package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Cuerpo_Switch implements ASTNode {
	
	private SwitchCase opnd1;
	private Cuerpo_Switch opnd2;
	
	

	public Cuerpo_Switch(SwitchCase opnd1, Cuerpo_Switch opnd2) {
		super();
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public Cuerpo_Switch() {
	}

	public String toString() {
		if(opnd1 == null) {
			return "cuerpo_Switch()";
		}else {
			return "cuerpo_Switch("+opnd1.toString() + "," + opnd2.toString()+ ")";
		}
	}


	@Override
	public NodeKind nodeKind() {
		return NodeKind.CUERPO_SWITCH;
	}

	public SwitchCase getOpnd1() {
		return opnd1;
	}

	public Cuerpo_Switch getOpnd2() {
		return opnd2;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {
		if(opnd1 != null) {
			try {
				opnd1.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
			opnd2.bind(pila);
		}
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(opnd1 != null) {
			opnd1.subsUserTypes(globalTypes);
			opnd2.subsUserTypes(globalTypes);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(opnd1 != null) {
			opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
			opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		}
	}
	
	

}
