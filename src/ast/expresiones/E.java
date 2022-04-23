package ast.expresiones;

import java.util.Map;

import ast.ASTNode;
import ast.NodeKind;
import ast.instrucciones.Instruccion;
import ast.instrucciones.KindI;
import ast.tipo.Tipo;

public abstract class E extends Instruccion implements ASTNode {

	public E(int fila, int columna) {
		super(fila, columna);
	}
	public Tipo tipo;
    public abstract KindE kind();
    public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public E opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public String val() {throw new UnsupportedOperationException("num");}
    public NodeKind nodeKind() {return NodeKind.EXPRESION;}
    public String toString() {return "";}
    public KindI kindI() {return KindI.EXPRESION;}
    public void subsUserTypes(Map<String, Tipo> globalTypes) {}

}
