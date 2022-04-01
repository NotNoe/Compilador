package ast.externos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Identificador;
import ast.externos.util.KindExt;
import ast.externos.util.Parametros;
import ast.instrucciones.BloqueInstrucciones;
import ast.tipo.Tipo;

public class DefFuncion implements Externo {

	private Tipo opnd1;
	private Identificador opnd2;
	private Parametros opnd3;
	private BloqueInstrucciones opnd4;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		pila.peek().put(this.opnd2.getIden(), this);
		pila.push(new HashMap<String, ASTNode>());
		this.opnd3.bind(pila);
		this.opnd4.bind(pila);
		pila.pop();
	}
	
	public DefFuncion(Tipo opnd1, Identificador opnd2, Parametros opnd3, BloqueInstrucciones opnd4) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	

	public Tipo getOpnd1() {
		return opnd1;
	} 



	public Identificador getOpnd2() {
		return opnd2;
	}



	public Parametros getOpnd3() {
		return opnd3;
	}



	public BloqueInstrucciones getOpnd4() {
		return opnd4;
	}



	public KindExt kindExt() {
		return KindExt.DEF_FUNCION;
	}
	
	public String toString() {
		return "defFuncion(" + this.opnd1.toString() + "," + this.opnd2.toString() + "," +
				this.opnd3.toString() + "," + this.opnd4.toString() + ")";
	}

}
