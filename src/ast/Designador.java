package ast;

public abstract class Designador extends E {
	public KindE kind() {return KindE.DESIGNADOR;}
	public abstract KindDes kindDes();
}
