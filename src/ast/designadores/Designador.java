package ast.designadores;

import ast.expresiones.E;
import ast.expresiones.KindE;

public abstract class Designador extends E {
	public KindE kind() {return KindE.DESIGNADOR;}
	public abstract KindDes kindDes();
}
