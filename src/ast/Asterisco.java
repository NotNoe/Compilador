package ast;

public class Asterisco extends Designador {
	
	private Designador opnd1;
	
	public Asterisco(Designador op1) {
		this.opnd1 = op1;
	}
	
	public E opnd1() {return this.opnd1;} 

	@Override
	public KindDes kindDes() {
		return KindDes.ASTERISCO;
	}
	
	public String toString() {
		return "acceso("+this.opnd1.toString()+")";
	}

}
