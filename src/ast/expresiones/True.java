package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class True extends E {

	  public True() {}
	  public String val() {return "true";}
	  public KindE kind() {return KindE.TRUE;}   
	  public String toString() {return "true";}  
	  public void bind (Stack<Map<String, ASTNode>> pila) {
			
		}

}
