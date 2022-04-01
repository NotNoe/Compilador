package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Caracter extends E {

	 private String v;
	  public Caracter(String v) {
	   this.v = String.valueOf(v.charAt(1));  
	  }
	  public String val() {return v;}
	  public KindE kind() {return KindE.CHAR;}   
	  public String toString() {return v;} 

	  public void bind (Stack<Map<String, ASTNode>> pila) {
			
	}
}
