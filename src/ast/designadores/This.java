package ast.designadores;

public class This extends Designador {
	
	public String toString() {
		return "this()";
	}

	@Override
	public KindDes kindDes() {
		return KindDes.THIS;
	}

}
