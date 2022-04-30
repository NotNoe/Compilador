package ast.instrucciones;

import ast.ASTNode;
import ast.NodeKind;
import ast.tipo.Tipo;

public abstract class Instruccion implements ASTNode{
	
	public abstract KindI kindI();
    public NodeKind nodeKind() {return NodeKind.INSTRUCCION;}
    public String toString() {return "";}
    public int fila, columna;
    public Instruccion(int fila, int columna) {
    	this.fila = fila;
    	this.columna = columna;
    }
    //public abstract void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable);
	protected abstract int precalcular(int i);
	

}
