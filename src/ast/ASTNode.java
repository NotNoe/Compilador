package ast;

import java.util.Map;
import java.util.Stack;

import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public interface ASTNode {
	public void subsUserTypes(Map<String, Tipo> globalTypes);
    public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException; 
	public String generateCode(String code, int delta); // for the future
    public void bind(Stack<Map<String, ASTNode>> pila) throws UndefinedVariableException;
    public NodeKind nodeKind();
    public String toString();
}
