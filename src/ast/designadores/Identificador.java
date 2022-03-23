package ast.designadores;

import ast.tipo.KindType;
import ast.tipo.Tipo;

public class Identificador extends Designador implements Tipo {
	
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

	@Override
	public KindType kindType() {
		return KindType.IDENTIFICADOR;
	}

}
