package ast.Instrucciones;

import ast.ASTNode;
import ast.NodeKind;

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
	
	

}
