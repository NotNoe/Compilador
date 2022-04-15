package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Bool;
import ast.tipo.Tipo;

public class False extends E {

	  public False() {}
	  public String val() {return "false";}
	  public KindE kind() {return KindE.FALSE;}   
	  public String toString() {return "false";}  
	  public void bind (Stack<Map<String, ASTNode>> pila) {
	  }
	
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		this.tipo = new Bool();
	}
	  
}
