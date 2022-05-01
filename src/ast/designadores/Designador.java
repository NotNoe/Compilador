package ast.designadores;

import java.util.Map;

import ast.expresiones.E;
import ast.expresiones.KindE;
import ast.tipo.Tipo;

public abstract class Designador extends E {
	
	public Designador(int fila, int columna) {
		super(fila, columna);
	}
	
	public KindE kind() {return KindE.DESIGNADOR;}
	public abstract KindDes kindDes();
	public void subsUserTypes(Map<String, Tipo> globalTypes) {}

	public abstract String getDir(int delta);

	public abstract int getDelta();
}
