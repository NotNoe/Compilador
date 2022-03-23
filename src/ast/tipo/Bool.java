package ast.tipo;

public class Bool implements Tipo {
	
	public String toString() {return "bool()";}

	@Override
	public KindType kindType() {
		return KindType.BOOL;
	}

}
