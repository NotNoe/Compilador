package alex;

import errors.GestionErroresExp;

%%
%cup
%column
%line
%class AnalizadorLexicoExp
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}


int = int
char = char
bool = bool
class = class
struct = struct
for = for
while = while
if = if
switch = switch
break = break
continue = continue
const = const
void = void
default = default
case = case
new = new
return = return
true = true
false = false
this = this
delete = delete
build = build



digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
letra = ([A-Z]|[a-z]|"_")
identificador = {letra}({letra}|{digito})
numeroEntero = {digitoPositivo}{digito}*
separador = [ \t\r\b\n]
comentario = "\\"[^\n]* 
operadorSuma = \+
operadorAsterisco = \*
operadorResta = "-"
operadorDiv = "/"
operadorAnd = "&&"
operadorOr = "||"
parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
llaveApertura = \{
llaveCierre = \}
mayorIgual = ">="
mayor = ">"
menor = "<"
menorIgual = "<="
igualdad = "=="
desigual = "!="
negacion = "!"
asignacion = "="
referencia = "&"
modulo = "%"
punto = "."
puntoComa = ";"
coma = ","



%%

{separador}               {}
{comentario}              {}

{int}			  {return ops.unidadInt();}
{char}			  {return ops.unidadChar();}
{bool}			  {return ops.unidadBool();}
{class}			  {return ops.unidadClass();}
{struct}		  {return ops.unidadStruct();}
{for}			  {return ops.unidadFor();}
{while}			  {return ops.unidadWhile();}
{if}			  {return ops.unidadIf();}
{switch}		  {return ops.unidadSwitch();}
{break}			  {return ops.unidadBreak();}
{continue}		  {return ops.unidadContinue();}
{const}			  {return ops.unidadConst();}
{void}			  {return ops.unidadVoid();}
{default}		  {return ops.unidadDefault();}
{case}			  {return ops.unidadCase();}
{new}			  {return ops.unidadNew();}
{return}		  {return ops.unidadReturn();}
{true}			  {return ops.unidadTrue();}
{false}			  {return ops.unidadFalse();}
{this}			  {return ops.unidadThis();}		  
{delete}		  {return ops.unidadDelete();}
{build}			  {return ops.unidadBuild();}


{identificador}		  {return ops.unidadIdenti();}
{numeroEntero}            {return ops.unidadEnt();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorAsterisco}	  {return ops.unidadAst();}
{operadorResta}		  {return ops.unidadResta();}
{operadorDiv}		  {return ops.unidadDiv();}
{operadorAnd}		  {return ops.unidadAnd();}
{operadorOr}		  {return ops.unidadOr();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();}
{corcheteApertura}	  {return ops.unidadCAP();}
{corcheteCierre}	  {return ops.unidadCCierre();}
{llaveApertura}		  {return ops.unidadLAP();}
{llaveCierre}		  {return ops.unidadLCierre();}
{mayorIgual}		  {return ops.unidadMayorIgual();}
{mayor}			  {return ops.unidadMayor();}
{menorIgual}		  {return ops.unidadMenorIgual();}
{menor}			  {return ops.unidadMenor();}
{igualdad}		  {return ops.unidadIgual();}
{desigual}		  {return ops.unidadDesigual();}
{negacion}		  {return ops.unidadNegacion();}
{asignacion}		  {return ops.unidadAsignacion();}
{referencia}		  {return ops.unidadReferencia();}
{modulo}		  {return ops.unidadModulo();}
{punto}			  {return ops.unidadPunto();}
{puntoComa}		  {return ops.unidadPuntoComa();}
{coma}			  {returno ops.unidadComa();}	  


[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
