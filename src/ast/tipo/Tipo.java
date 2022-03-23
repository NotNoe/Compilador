package ast.tipo;

import ast.ASTNode;
import ast.NodeKind;

public abstract interface Tipo extends ASTNode {
	public default NodeKind nodeKind() {
		return NodeKind.TIPO;
	}
	public abstract KindType kindType();

}
