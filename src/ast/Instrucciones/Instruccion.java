package ast.Instrucciones;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Instruccion implements ASTNode{
	
	public abstract KindI kindI();
    public NodeKind nodeKind() {return NodeKind.INSTRUCCION;}
    public String toString() {return "";}

}
