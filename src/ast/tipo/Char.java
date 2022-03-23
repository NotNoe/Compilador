package ast.tipo;

public class Char implements Tipo {
	
	public String toString() {return "char()";}

	@Override
	public KindType kindType() {
		return KindType.CHAR;
	}

}
