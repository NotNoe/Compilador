package ast.instrucciones;

public class Break extends Instruccion {

	public Break() {}
	
	public KindI kindI() {
		return KindI.BREAK;
	}

	public String toString() {
		return "break()";
	}
}
