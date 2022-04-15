package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Bool;
import ast.tipo.Tipo;

public class True extends E {

	  public True() {}
	  public String val() {return "true";}
	  public KindE kind() {return KindE.TRUE;}   
	  public String toString() {return "true";}  
	  public void bind (Stack<Map<String, ASTNode>> pila) {
			
		}
	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		this.tipo = new Bool();
	}

}
