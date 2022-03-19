package ast.Expresiones;

import ast.ASTNode;
import ast.NodeKind;
import ast.Instrucciones.Instruccion;
import ast.Instrucciones.KindI;

public abstract class E extends Instruccion implements ASTNode {
    public abstract KindE kind();
    public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public E opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public String val() {throw new UnsupportedOperationException("num");}
    public NodeKind nodeKind() {return NodeKind.EXPRESION;}
    public String toString() {return "";}
    public KindI kindI() {return KindI.EXPRESION;}

}
