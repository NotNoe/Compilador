package ast.Expresiones;

public class Character extends E {

	 private String v;
	  public Character(String v) {
	   this.v = String.valueOf(v.charAt(1));  
	  }
	  public String val() {return v;}
	  public KindE kind() {return KindE.CHAR;}   
	  public String toString() {return v;} 

}
