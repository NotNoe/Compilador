package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;
import ast.externos.Programa;



public class Main {
	public static boolean error = false;
	public static boolean syntax_error = false;
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 AnalizadorSintactico constructorast = new AnalizadorSintactico(alex);
	 Programa aux = (Programa) constructorast.parse().value;
	 if(Main.syntax_error)
		 System.exit(1);
	 aux.compilar();
	 
 }
}   
   
