package ast;

import java.util.Map;
import java.util.Stack;

import ast.tipo.Tipo;

public interface ASTNode {
	public void subsUserTypes(Map<String, Tipo> globalTypes);
    public void type(Tipo funcion, Tipo val_switch, Tipo current_class); 
	// public ?? generateCode() // for the future
    public void bind(Stack<Map<String, ASTNode>> pila);
    public NodeKind nodeKind();
    public String toString();
}
