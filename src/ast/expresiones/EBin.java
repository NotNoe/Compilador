package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Bool;
import ast.tipo.Int;
import ast.tipo.Tipo;

public class EBin extends E {
	private E opnd1;
	private E opnd2;
	private KindE op;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		this.opnd2.bind(pila);
	}

	public EBin(E opnd1, E opnd2, String op) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;

		switch (op) {
		case "+":
			this.op = KindE.SUMA;
			break;
		case "-":
			this.op = KindE.RESTA;
			break;
		case "*":
			this.op = KindE.MUL;
			break;
		case "/":
			this.op = KindE.DIV;
			break;
		case "%":
			this.op = KindE.MOD;
			break;
		case "&&":
			this.op = KindE.AND;
			break;
		case "||":
			this.op = KindE.OR;
			break;
		case "<=":
			this.op = KindE.MENIG;
			break;
		case ">=":
			this.op = KindE.MAYIG;
			break;
		case "<":
			this.op = KindE.MEN;
			break;
		case ">":
			this.op = KindE.MAY;
			break;
		case "==":
			this.op = KindE.IGU;
			break;
		case "!=":
			this.op = KindE.DESIG;
			break;

		}
	}

	public KindE kind() {
		return op;
	}

	public String toString() {

		switch (this.op) {
		case SUMA:
			return "suma(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case RESTA:
			return "resta(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case MUL:
			return "mul(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case DIV:
			return "div(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case MOD:
			return "mod(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case AND:
			return "and(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case OR:
			return "or(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case MENIG:
			return "menig(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case MAYIG:
			return "mayig(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case MEN:
			return "men(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case MAY:
			return "may(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case IGU:
			return "igu(" + opnd1().toString() + "," + opnd2().toString() + ")";
		case DESIG:
			return "desig(" + opnd1().toString() + "," + opnd2().toString() + ")";
		default:
			return null;
		}
	}

	public E opnd1() {
		return opnd1;
	}

	public E opnd2() {
		return opnd2;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		opnd2.type(funcion, val_switch, current_class);
		if(!(Tipo.equals(opnd1.tipo, opnd2.tipo))) {
			//TODO error
		}
		switch (this.op) {
		case SUMA, RESTA, MUL, DIV, MOD:
			if(Tipo.equals(opnd1.tipo, new Int())) {
				this.tipo = new Int();
			}else {
				//TODO: error
				this.tipo = null;
			}
			break;
		case AND, OR:
			if(Tipo.equals(opnd1.tipo, new Bool())) {
				this.tipo = new Bool();
			}else {
				//TODO: error
				this.tipo = null;
			}
			break;
		case MENIG, MAYIG, MEN, MAY:
			if(Tipo.equals(opnd1.tipo, new Int())) {
				this.tipo = new Bool();
			}else {
				//TODO: error
				this.tipo = null;
			}
			break;
		case IGU, DESIG:
			this.tipo = new Bool();
		default:
			//TODO: error
		}
	}
}
