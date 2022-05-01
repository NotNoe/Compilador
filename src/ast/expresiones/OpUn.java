package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Bool;
import ast.tipo.Int;
import ast.tipo.KindType;
import ast.tipo.Pointer;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class OpUn extends E {

	private E opnd1;
	private KindE op;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
	}
	
	public OpUn(E opnd1, String op, int fila, int columna) {
		super(fila, columna);
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
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException {
		opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
		switch(this.op) {
		case REF:
			this.tipo = new Pointer(this.opnd1.tipo);
			break;
		case NEG:
			if(this.opnd1.tipo.kindType() == KindType.BOOL) {
				this.tipo = new Bool();
			}else {
				throw new TypeMissmatchException("Type expected for not operator is bool, not (" +
						opnd1.tipo.printT() + ".", this.fila, this.columna);
			}
			break;
		case MENOS:
			if(this.opnd1.tipo.kindType() == KindType.INT) {
				this.tipo = new Int();
			}else {
				throw new TypeMissmatchException("Type expected for minus operator is int, not (" +
						opnd1.tipo.printT() + ".", this.fila, this.columna);
			}
			break;
		default:
			throw new RuntimeException("Algo ha explotado");
		}
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		switch(this.op) {
		case REF:
			//TODO:
			return null;
		case NEG:
			return this.opnd1.generateCode(code, delta, depth) + "i32.eqz\n";
		case MENOS:
			return "i32.const 0\n" + this.opnd1.generateCode(code, delta, depth) + "i32.sub\n";
		default:
			return null;
		}
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

}
