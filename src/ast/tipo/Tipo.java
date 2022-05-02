package ast.tipo;

import java.util.Map;

import ast.ASTNode;
import ast.NodeKind;
import errors.TypeMissmatchException;

public abstract interface Tipo extends ASTNode {
	public default NodeKind nodeKind() {
		return NodeKind.TIPO;
	}
	public int getSize();
	public default String generateCode(String code, int delta, int depth) {
		return "";
	};
	public abstract KindType kindType();
	public abstract Tipo getBasicType(Map<String, Tipo> globalTypes) throws TypeMissmatchException;
	public default void subsUserTypes(Map<String, Tipo> globalTypes) {
		try {
			this.getBasicType(globalTypes);
		} catch (TypeMissmatchException e) {
			e.print();
		}
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
				return (((Array) tipo1).size == ((Array) tipo2).size) && 
						equals(((Array) tipo1).getTipo(), ((Array) tipo2).getTipo());
			case NULL_POINTER, ARRAY_VACIO, DELIM:
				return true;
			default:
				return false;
				
			}
		}
	}
	
	public abstract String printT();

}
