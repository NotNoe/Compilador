package ast.Expresiones;

public class OpUn extends E {

	private E opnd1;
	private KindE op;
	
	public OpUn(E opnd1, String op) {
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
