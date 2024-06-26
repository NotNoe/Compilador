package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Char;
import ast.tipo.Tipo;

public class Caracter extends E {

	 private String v;
	  public Caracter(String v, int fila, int columna) {
		  super(fila, columna);
	   this.v = String.valueOf(v.charAt(1));  
	  }
	  public String val() {return v;}
	  public KindE kind() {return KindE.CHAR;}   
	  public String toString() {return v;} 

	  public void bind (Stack<Map<String, ASTNode>> pila) {
			
	}
	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.tipo = new Char();
	}
	@Override
	public String generateCode(String code, int delta, int depth) {
		return "";
	}
	@Override
	protected int precalcular(int i) {
		return i;
	}
}
