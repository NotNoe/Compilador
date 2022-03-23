package ast.tipo;

public class Pointer implements Tipo {
	
	private Tipo p;
	
	public Pointer(Tipo p) {
		this.p = p;
	}
	
	public String toString() {
		return "pointer(" + p.toString() + ")";
	}

	@Override
	public KindType kindType() {
		return KindType.POINTER;
	}

}
