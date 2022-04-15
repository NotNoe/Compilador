package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Bool;
import ast.tipo.Int;
import ast.tipo.KindType;
import ast.tipo.Pointer;
import ast.tipo.Tipo;

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

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		switch(this.op) {
		case REF:
			this.tipo = new Pointer(this.opnd1.tipo);
			break;
		case NEG:
			if(this.opnd1.tipo.kindType() == KindType.BOOL) {
				this.tipo = new Bool();
			}else {
				//TODO: error
			}
			break;
		case MENOS:
			if(this.opnd1.tipo.kindType() == KindType.INT) {
				this.tipo = new Int();
			}else {
				//TODO: error
			}
			break;
		default:
			//TODO error
		}
	}

}
