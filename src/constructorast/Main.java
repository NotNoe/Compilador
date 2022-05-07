package constructorast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;
import ast.externos.Programa;



public class Main {
	public static boolean error = false;
	public static boolean syntax_error = false;
   public static void main(String[] args) throws Exception {
	 String fuente = ".\\codigo_fuente\\"+args[0];
     Reader input = new InputStreamReader(new FileInputStream(fuente));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 AnalizadorSintactico constructorast = new AnalizadorSintactico(alex);
	 Programa aux = (Programa) constructorast.parse().value;
	 if(Main.syntax_error)
		 System.exit(1);
	 String code = aux.compilar();
	 String maquina = args[0].substring(0, args[0].length() - 3);
	 maquina += "wat";
	 @SuppressWarnings("unused")
	File archivo = new File(maquina);
	 FileWriter out = new FileWriter(maquina);
	 out.write(code);
	 out.flush();
	 out.close();
	 Runtime rt = Runtime.getRuntime();
	 rt.exec(".\\Recursos\\wasm\\wat2wasm.exe " + maquina);
	 
 }
}   
   
