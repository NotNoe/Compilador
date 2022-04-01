package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class OpUn extends E {

	private E opnd1;
	private KindE op;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
	}
	
	public OpUn(E opnd1, String op) {
		this.opnd1 = opnd1;
		switch(op) {
		case "&":
			this.op = KindE.REF;
			break;
		case "!":
			this.op = KindE.NEG;
			break;
		case "-":
			this.op = KindE.MENOS;
		}
	}
	
	public String toString() {
		switch(this.op) {
		case REF:
			return "ref("+this.opnd1.toString()+")";
		case NEG:
			return "neg(" + this.opnd1.toString()+")";
		case MENOS:
			return "menos(" + this.opnd1.toString() +")";
		default:
			return null;
		}
	}
	
	
	public KindE kind() {
		return this.op;
	}
	
	
	
	public E opnd1() {
		return opnd1;
	}

}
