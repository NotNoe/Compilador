package ast;

import java.util.Map;
import java.util.Stack;

public interface ASTNode {
    // public ?? type() // for the future
	// public ?? generateCode() // for the future
    public void bind(Stack<Map<String, ASTNode>> pila);
    public NodeKind nodeKind();
    public String toString();
}
