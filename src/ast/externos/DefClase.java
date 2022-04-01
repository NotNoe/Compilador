package ast.externos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Identificador;
import ast.externos.util.CuerpoClase;
import ast.externos.util.KindExt;

public class DefClase implements Externo {

	private CuerpoClase opnd1;
	private Identificador opnd2;
	private Map<String, ASTNode> ambito;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		pila.push(this.ambito);
		this.opnd1.bind(pila);
		pila.pop();
	}
	
	public DefClase(CuerpoClase opnd1, Identificador opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.ambito = new HashMap<String, ASTNode>();
	}
	
	



	public CuerpoClase getOpnd1() {
		return opnd1;
	}





	public Identificador getOpnd2() {
		return opnd2;
	}

	public String toString() {
		return "defClase(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}



	public KindExt kindExt() {
		return KindExt.DEF_CLASE;
	}

}
