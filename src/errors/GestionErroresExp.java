package errors;

import alex.UnidadLexica;
import constructorast.Main;

public class GestionErroresExp {
   public void errorLexico(int fila, int columna, String lexema) {
     System.err.println("ERROR fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
     //System.exit(1);
     Main.syntax_error = true;
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     if (unidadLexica.lexema() != null) {
       System.err.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.err.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado");
     }
     //System.exit(1);
     Main.syntax_error = true;
   }
}
