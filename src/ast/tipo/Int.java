package ast.tipo;

public class Int extends Tipo{
	
	  public String toString() {return "int";}  

	@Override
	public KindType kindType() {
		return KindType.INT;
	}

}
