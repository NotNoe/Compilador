package ast.tipo;

public class Bool extends Tipo {
	
	public String toString() {return "Bool";}

	@Override
	public KindType kindType() {
		return KindType.BOOL;
	}

}
