package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;

import alex.UnidadLexica;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 //ConstructorASTExp constructorast = new ConstructorASTExp(alex);
	 //System.out.println(constructorast.lexema());
	 UnidadLexica unidad;
	 do {
		 unidad = (UnidadLexica) alex.next_token();
		 System.out.println(unidad);
	 }while(unidad.clase() != ClaseLexica.EOF);
 }
}   
   
