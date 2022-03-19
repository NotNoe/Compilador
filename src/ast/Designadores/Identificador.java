package ast.Designadores;

public class Identificador extends Designador {
	
	private String iden;
	public Identificador(String iden) {
		this.iden = iden;
	}
	
	public String toString() {
		return "identificador(" + this.iden + ")";
	}
	public KindDes kindDes() {
		return KindDes.IDENFITICADOR;
	}

}
