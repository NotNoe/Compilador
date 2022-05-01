package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Int;
import ast.tipo.Tipo;

public class Num extends E {
	private String v;
	private int value;

	public Num(String v, int fila, int columna) {
		super(fila, columna);
		this.v = v;
		this.value = Integer.parseInt(v);
	}

	public String val() {
		return v;
	}

	public KindE kind() {
		return KindE.NUM;
	}

	public String toString() {
		return v;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {

	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.tipo = new Int();
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		return "i32.const " + this.value + "\n";
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}
}
