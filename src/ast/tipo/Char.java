package ast.tipo;

public class Char extends Tipo {
	
	public String toString() {return "Char";}

	@Override
	public KindType kindType() {
		return KindType.CHAR;
	}

}
