package ast.tipo;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Tipo implements ASTNode {
	public NodeKind nodeKind() {
		return NodeKind.TIPO;
	}
	public abstract KindType kindType();

}
