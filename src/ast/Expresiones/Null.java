package ast.Expresiones;

public class Null extends E {
	
	public String toString() {
		return "null()";
	}

	@Override
	public KindE kind() {
		return KindE.NULL;
	}

}
