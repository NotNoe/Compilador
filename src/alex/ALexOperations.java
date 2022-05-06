package alex;

import constructorast.ClaseLexica;
import java_cup.runtime.Symbol;

public class ALexOperations {
  private AnalizadorLexicoExp alex;
  public ALexOperations(AnalizadorLexicoExp alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema()); 
  } 
  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SUMA,"+"); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AST,"*"); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PAP,"("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRE,")"); 
  } 
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF,"<EOF>"); 
  }
public UnidadLexica unidadNegacion() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEG,"!"); 
}
public Symbol unidadContinue() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CONTINUE,"continue");
}
public Symbol unidadDefault() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DEFAULT,"default");
}
public Symbol unidadSwitch() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SWITCH,"switch");
}
public Symbol unidadStruct() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STRUCT,"struct");
}
public Symbol unidadReturn() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RETURN,"return");
}
public Symbol unidadDelete() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DELETE,"delete");
}
public Symbol unidadModulo() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MOD,"%");
}
public Symbol unidadReferencia() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.REF,"&");
}
public Symbol unidadAst() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AST,"*");
}
public Symbol unidadComa() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA,",");
}
public Symbol unidadResta() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RESTA,"-");
}
public Symbol unidadPunto() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTO,".");
}
public Symbol unidadDiv() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV,"/");
}
public Symbol unidadPuntoComa() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTOCOMA,";");
}
public Symbol unidadMenor() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR,"<");
}
public Symbol unidadAsignacion() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASIG,"=");
}
public Symbol unidadMayor() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR,">");
}
public Symbol unidadIdenti() { //Identificadorers, no igualdades
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN,alex.lexema());
}
public Symbol unidadCAP() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CAP,"[");
}
public Symbol unidadCCierre() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CCIERRE,"]");
}
public Symbol unidadLAP() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LAP,"{");
}
public Symbol unidadLCierre() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LCIERRE,"}");
}
public Symbol unidadDesigual() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DESIG,"!=");
}
public Symbol unidadAnd() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AND,"&&");
}
public Symbol unidadMenorIgual() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORIG,"<=");
}
public Symbol unidadIgual() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL,"==");
}
public Symbol unidadMayorIgual() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORIG,">=");
}
public Symbol unidadIf() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IF,"if");
}
public Symbol unidadOr() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OR,"or");
}
public Symbol unidadFor() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FOR,"for");
}
public Symbol unidadNew() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEW,"new");
}
public Symbol unidadInt() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.INT,"int");
}
public Symbol unidadBool() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOL,"bool");
}
public Symbol unidadCase() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CASE,"case");
}
public Symbol unidadChar() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CHAR,"char");
}
public Symbol unidadThis() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.THIS,"this");
}
public Symbol unidadTrue() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TRUE,"true");
}
public Symbol unidadVoid() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VOID,"void");
}
public Symbol unidadBreak() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BREAK,"break");
}
public Symbol unidadClass() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CLASS,"class");
}
public Symbol unidadConst() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CONST,"const");
}
public Symbol unidadFalse() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FALSE,"false");
}
public Symbol unidadWhile() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.WHILE,"while");
}

public Symbol unidadNull() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NULL,"while");
}

public Symbol unidadPointer() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.POINTER,"while");
}

public Symbol unidadCaracter() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CARACTER,String.valueOf(alex.lexema().charAt(1)));
}
public Symbol unidadTypedef() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TYPEDEF,"typedef");
}
public Symbol unidadElse() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSE,"else");
}

public Symbol unidadPrint() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PRINT,"print");
}

public Symbol unidadRead() {
	return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.READ,"read");
}



}
