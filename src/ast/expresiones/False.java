package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Bool;
import ast.tipo.Tipo;

public class False extends E {

	  public False(int fila, int columna) {super(fila, columna);}
	  public String val() {return "false";}
	  public KindE kind() {return KindE.FALSE;}   
	  public String toString() {return "false";}  
	  public void bind (Stack<Map<String, ASTNode>> pila) {
	  }
	
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.tipo = new Bool();
	}
	@Override
	public String generateCode(String code, int delta, int depth) {
		return "i32.const 0\n";
	}
	@Override
	protected int precalcular(int i) {
		return i;
	}
	  
}
