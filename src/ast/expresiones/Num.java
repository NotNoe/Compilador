package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;

public class Num extends E {
  private String v;
  public Num(String v) {
   this.v = v;   
  }
  public String val() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;} 
  public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}
}
