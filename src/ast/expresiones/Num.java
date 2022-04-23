package ast.expresiones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.tipo.Int;
import ast.tipo.Tipo;

public class Num extends E {
  private String v;
  public Num(String v, int fila, int columna) {
	  super(fila, columna);
   this.v = v;   
  }
  public String val() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;} 
  public void bind (Stack<Map<String, ASTNode>> pila) {
		
	}
@Override
public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
	this.tipo = new Int();
}
}
