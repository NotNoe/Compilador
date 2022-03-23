package ast.tipo;

public class Int implements Tipo{
	
	 public String toString() {return "int()";}  
	 
	 public Int(){}
	 
	@Override
	public KindType kindType() {
		return KindType.INT;
	}

}
