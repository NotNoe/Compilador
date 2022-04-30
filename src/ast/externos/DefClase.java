package ast.externos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.designadores.Identificador;
import ast.externos.util.CuerpoClase;
import ast.externos.util.KindExt;
import ast.tipo.KindType;
import ast.tipo.Tipo;

public class DefClase implements Externo, Tipo {

	private CuerpoClase opnd1;
	private Identificador opnd2;
	private Map<String, ASTNode> ambito;
	public int fila, columna;
	private int size = 0;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.preBinding(this.ambito);
		pila.push(this.ambito);
		this.opnd1.bind(pila);
		pila.pop();
	}
	
	public DefClase(CuerpoClase opnd1, Identificador opnd2, int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
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

	@Override
	public KindType kindType() {
		return KindType.CLASE;
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.PROGRAMA;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		return this;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		opnd1.type(null, null, this, continuable, breakeable);
	}

	public Map<String, ASTNode> getAmbito() {
		return ambito;
	}

	@Override
	public String printT() {
		return this.opnd2.getIden();
	}

	public int precalcular(int delta) {
		this.size = this.opnd1.precalcular(0);
		return delta;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public String generateCode(String code, int delta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
