package ast.Designadores;

import ast.Expresiones.E;
import ast.Expresiones.KindE;

public abstract class Designador extends E {
	public KindE kind() {return KindE.DESIGNADOR;}
	public abstract KindDes kindDes();
}
