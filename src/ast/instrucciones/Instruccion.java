package ast.instrucciones;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Instruccion implements ASTNode{
	
	public abstract KindI kindI();
    public NodeKind nodeKind() {return NodeKind.INSTRUCCION;}
    public String toString() {return "";}
    public int fila, columna;
    public Instruccion(int fila, int columna) {
    	this.fila = fila;
    	this.columna = columna;
    }
	protected abstract int precalcular(int i);
	

}
