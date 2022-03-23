package ast.instrucciones;

public class Continue extends Instruccion {
	
	public Continue() {}

	public KindI kindI() {
		return KindI.CONTINUE;
	}

	public String toString() {
		return "continue()";
	}

}
