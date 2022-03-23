package ast.externos;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.util.KindExt;

public interface Externo extends ASTNode{
	
	
	public KindExt kindExt();
	
	public default NodeKind nodeKind() {
		return NodeKind.PROGRAMA;
	}

}
