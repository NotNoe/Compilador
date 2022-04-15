package ast.tipo;

import java.util.Map;

import ast.ASTNode;
import ast.NodeKind;

public abstract interface Tipo extends ASTNode {
	public default NodeKind nodeKind() {
		return NodeKind.TIPO;
	}
	public abstract KindType kindType();
	public abstract Tipo getBasicType(Map<String, Tipo> globalTypes);
	public default void subsUserTypes(Map<String, Tipo> globalTypes) {
		this.getBasicType(globalTypes);
	}
	public static boolean equals(Tipo tipo1, Tipo tipo2) {
		if(tipo1.kindType() != tipo2.kindType()) {
			if(tipo1.kindType() == KindType.ARRAY && tipo2.kindType() == KindType.ARRAY_VACIO ||
					tipo2.kindType() == KindType.ARRAY && tipo1.kindType() == KindType.ARRAY_VACIO ||
					tipo1.kindType() == KindType.POINTER && tipo2.kindType() == KindType.NULL_POINTER ||
					tipo2.kindType() == KindType.POINTER && tipo1.kindType() == KindType.NULL_POINTER) {
				return true;
			}else {
				return false;
			}
		}else {
			switch(tipo1.kindType()) {
			case INT, BOOL, CHAR:
				return true;
			case STRUCT, CLASE:
				return tipo1 == tipo2;
			case POINTER:
				return equals(((Pointer) tipo1).getP(), ((Pointer) tipo2).getP());
			case ARRAY:
				return equals(((Array) tipo1).getTipo(), ((Array) tipo2).getTipo());
			case NULL_POINTER, ARRAY_VACIO:
				return true;
			default:
				return false;
				//TODO:error
			}
		}
	}

}
