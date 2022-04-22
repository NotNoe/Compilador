package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;
import ast.externos.Programa;


public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 AnalizadorSintactico constructorast = new AnalizadorSintactico(alex);
//	 System.out.println(constructorast.parse().value);
	 Programa aux = (Programa) constructorast.parse().value;
	 aux.compilar();
 }
}   
   
