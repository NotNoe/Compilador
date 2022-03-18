package ast;

public class EBin extends E {
	private E opnd1;
	private E opnd2;
	private KindE op;

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
		case ".":
			this.op = KindE.PUNTO;
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
		case PUNTO:
			return "punto(" + opnd1().toString() + "," + opnd2().toString() + ")";
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
}
